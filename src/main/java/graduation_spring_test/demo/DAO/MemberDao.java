package graduation_spring_test.demo.DAO;

import graduation_spring_test.demo.domain.Member.Member;

import java.util.List;

public interface MemberDao {
    void addMember(Member member);  // 회원 등록
    Member getMemberById(String memberId);  // 회원 조회

    void updateMember(Member member); //회원 수정

    void deleteMember(Member member); //회원 탈퇴
    
    List<Member> getMemberList(); // 전체 회원 조회
}
