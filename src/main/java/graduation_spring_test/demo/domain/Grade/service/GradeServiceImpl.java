package graduation_spring_test.demo.domain.Grade.service;

import graduation_spring_test.demo.DAO.GradeDAO;
import graduation_spring_test.demo.domain.Grade.Grade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GradeServiceImpl implements GradeService{

    @Autowired
    private GradeDAO gradeDao;


    @Override
    public void inputGrade(Grade grade) {
        // 이미 해당 data가 존재하면 삭제후 삽입
        if(gradeDao.isExistGrade(grade)>0) {
            gradeDao.deleteGrade(grade);
        }
        gradeDao.addGrade(grade);
    }

    @Override
    public String getGradeByLec(String cNum) {
        return gradeDao.getGrade(cNum);
    }

    @Override
    public void deleteGradeByMember(String memberId) {
        //gradeDao.deleteGrade(memberId);
    }

    @Override
    public float getAllScoreByMember(String memberId) {
        float sum = gradeDao.getSumOfAllScore(memberId);
        int num = gradeDao.getCredit(memberId);
        return sum/num;
    }

    @Override
    public float getMajorScoreByMember(String memberId) {
        float sum = gradeDao.getSumOfMajorScore(memberId);
        int num = gradeDao.getCredit(memberId);
        return sum/num;
    }

    @Override
    public float getEntireAllScore() {
        return 0;
    }

    @Override
    public float getEntireMajorScore() {
        return 0;
    }
}