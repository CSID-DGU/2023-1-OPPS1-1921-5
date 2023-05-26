package graduationProject.graduation_judge.DTO;

import lombok.Data;

@Data
public class GraduationResultList {
    /**
     * Register --1)등록학기
     * EngScore --2)외국어 성적
     * TotalCredit --3)취득학점
     * TotalScore --4)성적평점
     * EngClassCount --5)영어강의
     * BSMCredit -- 이수한 bsm_수학 학점
     * BSMMathCredit -- 이수한 bsm_수학 학점
     * BSMSciCredit -- 이수한 bsm_과학 학점
     * CommonClassCredit -- 이수한 공통교양학점
     * Course --심화/일반 (UserInfo의 Course 컬럼)
     * EngLevel --영어등급 (Userinfo의 EnglishGrade 컬럼)
     * GibonSoyangCredit --이수한 기본소양학점
     * MajorCredit -- 이수한 전공 학점
     * Result --들은 과목 총 개수
     * SpecialMajorCredit -- 이수한 전공 & 전문 학점
     * StudentNumber --학번 (Userinfo의 StudentNumber 컬럼)
     */
    private int Register;
    private int EngScore;
    private int TotalCredit;
    private float TotalScore;
    private int EngClassCount;

    public GraduationResultList() {}
}