package graduationProject.graduation_judge.domain.Member.controller;

import graduationProject.graduation_judge.DTO.MailDTO;
import graduationProject.graduation_judge.DTO.UserInfoDTO;
import graduationProject.graduation_judge.domain.Member.service.MemberService;

import graduationProject.graduation_judge.domain.Grade.service.GradeService;
import graduationProject.graduation_judge.domain.Member.service.EmailService;
import graduationProject.graduation_judge.domain.Stats.service.StatsService;


import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

@RestController
//@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberService memberService;

    @Autowired
    private EmailService emailService;
    @Autowired
    private GradeService gradeService;
    @Autowired
    private StatsService statsService;

    @PostMapping("/emailcheck")
    public HashMap<String, Integer> emailCheck(@RequestBody Map<String ,String > request){
        String email = request.get("email");
        HashMap<String, Integer> map = new HashMap<>();
        map.put("result", memberService.emailCheck(email));
        return map;
    }

    //회원 가입
    @PostMapping("/signup")
    public ResponseEntity<?> signup(@RequestBody  Map<String, String > request) {
        try {
            UserInfoDTO userInfoDTO = new UserInfoDTO(request.get("email"),
                    request.get("pw"), Integer.parseInt(request.get("semester")),
                    Integer.parseInt(request.get("year")),
                    Major_curriculum.valueOf(request.get("course")),
                    Integer.parseInt(request.get("score")),
                    English_level.valueOf(request.get("english")));
            memberService.register(userInfoDTO);
            HashMap<String, Object> map = new HashMap<>();
            map.put("email", request.get("email"));
            map.put("pw", request.get("pw"));
            map.put("semester",request.get("semester"));
            map.put("year",request.get("year"));
            map.put("course", request.get("course"));
            map.put("score", request.get("score"));
            map.put("english", request.get("english"));
            return ResponseEntity.ok(userInfoDTO);
            //userInfoDTO객체를 JSON형태로 반환
        } catch (Exception e) {
            HashMap<String, Object> map = new HashMap<>();
            map.put("error", "error");
            return ResponseEntity.badRequest().body(e.getMessage()); //예외 메시지 반환
        }
    }

    //로그인
    @PostMapping("/signin")
    public HashMap<String, Object> signin(@RequestBody Map<String, String > request) {
        try {
            String email = request.get("email");
            String pw = request.get("pw");
            String id = memberService.login(email, pw);
            HashMap<String, Object> map = new HashMap<>();
            map.put("id", id);
            return map; //id를 JSON형태로 반환

        }catch (Exception e){
            HashMap<String, Object> map = new HashMap<>();
            map.put("error", "error");
            return map;
        }
    }

    //회원 정보 조회
    @PostMapping("/mypage")
    public ResponseEntity<?> getUserById(@RequestBody String id) {
        try {
            UserInfoDTO userInfoDTO = memberService.getMemberById(id);
            return ResponseEntity.ok(userInfoDTO); //userInfo객체를 JSON형태로 반환
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage()); //예외 메시지 반환
        }
    }

    //회원 정보 수정
    @PostMapping("/updateuserinfo")
    public ResponseEntity<?> updateUser(@RequestBody Map<String, String > request) {
        String id = request.get("email");
        int studentNumber = Integer.parseInt(request.get("year"));
        int semester = Integer.parseInt(request.get("register"));
        Major_curriculum course = Major_curriculum.valueOf(request.get("course"));
        English_level englishGrade = English_level.valueOf(request.get("english"));
        int toeicScore = Integer.parseInt(request.get("score"));
        try {
            memberService.updateMember(id, studentNumber, semester, course,
                    toeicScore, englishGrade); //id, pincode빼고 수정
            UserInfoDTO userInfoDTO = memberService.getMemberById(id);
            return ResponseEntity.ok(userInfoDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }


    //회원 정보 삭제
    @PostMapping("/deleteuser")//userinfo, scorestat, securitycodeofusermail, userselectlist다삭제해야함
    public ResponseEntity<?> deleteUser(@RequestBody String id) {
        try {
            UserInfoDTO userInfoDTO = memberService.getMemberById(id);
            MailDTO mailDTO = emailService.getMailMemberById(id);

            memberService.deleteMember(userInfoDTO); //userinfo삭제 --> id로 삭제
            emailService.deleteMailDTO(mailDTO); //securitycodeofusermail삭제
            gradeService.deleteGradeByMember(id); //userselectlist삭제
            statsService.deleteStatByMemberId(id); //scorestat삭제

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }




    //비밀번호 찾기
    @PostMapping("/changepw")
    public ResponseEntity<String> findPassword(@RequestBody Map<String, String > request) {
        String email = request.get("email");
        String inputSecurityCode = request.get("inputSecurityCode");
        String newPassword = request.get("newPassword");
        try {
            memberService.findPassword(email, inputSecurityCode, newPassword);
            //보안코드 확인 후 비밀번호 변경
            return ResponseEntity.ok("비밀번호 변경이 완료되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/sendemail")
    public ResponseEntity<String> sendSecurityCode(@RequestBody String id) {

        try {
            memberService.sendSecurityCodeToEmail(id);
            return ResponseEntity.ok("이메일로 보안 코드가 전송되었습니다.");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    /**
     * -
     *     - 회원가입 (등록)
     *     - 비밀번호 찾기 (수정) (수정 )
     *     - 회원 정보 조회(마이페이지 조회) (조회)
     *     - 회원 정보 수정 (수정)
     *
     *     - 로그인
     *     - ~~(회원 탈퇴)~~
     */
    /*@PostMapping("/test")
    public String test(){
        UserInfoDTO userInfoDTO = new UserInfoDTO("user20171@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, 115, English_level.S3);
        this.memberService.register(userInfoDTO);
        return "input test";
    }

    @PostMapping("/emailTest")
    public String emailTest() {
        UserInfoDTO userInfoDTO = new UserInfoDTO("dabin6469@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, 115, English_level.S3);
        memberService.register(userInfoDTO);
        memberService.sendSecurityCodeToEmail(userInfoDTO.getId());
        return "email test";
    }*/
}
