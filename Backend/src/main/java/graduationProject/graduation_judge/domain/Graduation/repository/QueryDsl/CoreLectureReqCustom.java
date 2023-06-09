package graduationProject.graduation_judge.domain.Graduation.repository.QueryDsl;

import graduationProject.graduation_judge.DAO.CoreLectureRequirement;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;

import java.util.List;

public interface CoreLectureReqCustom {
    List<CoreLectureRequirement> getLectureList(Integer enrollment, Major_curriculum course, String category);
    void insertCoreLecture(Integer enrollment,
                           Major_curriculum course,
                           String category,
                           String lectureName,
                           String lecutreNumber
    );
}
