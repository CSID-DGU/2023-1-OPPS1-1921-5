/*
package Member;

import graduationProject.graduation_judge.DTO.Member.UserInfoDTO;
import graduationProject.graduation_judge.domain.Member.repository.MailRepository;
import graduationProject.graduation_judge.domain.Member.repository.MemberRepository;
import graduationProject.graduation_judge.domain.Member.service.EmailService;
import graduationProject.graduation_judge.domain.Member.service.MemberService;
import graduationProject.graduation_judge.domain.Member.service.MemberServiceImpl;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;


public class MemberServiceTest {

    MemberService memberService;
    @Mock
    MemberRepository memberRepository;
    EmailService emailService;
    MailRepository mailRepository;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Mockito 초기화
        memberService = new MemberServiceImpl(memberRepository, emailService, mailRepository);
    }

    @Test
    void join() {
        //given
        UserInfoDTO userInfoDTO = new UserInfoDTO("user20171@gmail.com","user201712",
                1,2017, Major_curriculum.ADVANCED, 115, English_level.S3);
        //when
        //doReturn(member).when(memberRepository).findById(member.getId());
        memberService.register(userInfoDTO);
        UserInfoDTO findMember = memberRepository.findById(userInfoDTO.getId());
        //then
        //Assertions.assertThat(findMember).isEqualTo(member);
        System.out.println("findMember = " + findMember.getId());
        System.out.println("member = " + userInfoDTO.getId());
    }
}
*/
