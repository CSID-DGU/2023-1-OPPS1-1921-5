package graduation_spring_test.demo.domain.Member.service;

import graduation_spring_test.demo.DAO.MemberDao;
import graduation_spring_test.demo.domain.Member.Member;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
@Component
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberDao memberDao;

    @Override
    public void register(Member member) {
        // 회원 가입 로직 구현
        //이메일 중복 확인 하기
        memberDao.addMember(member);
    }

    @Override
    public Member getMemberById(String memberId) {
        // 회원 조회 로직 구현
        return memberDao.getMemberById(memberId);
    }

    @Override
    public void updateMember() {
        //회원 수정
    }

    @Override
    public void findPassword() {
        //이메일보내서 보안코드 확인

    }

    // ...
}
