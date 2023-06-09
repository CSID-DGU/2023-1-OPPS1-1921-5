package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LectureServiceImpl implements LectureService{

    @Autowired
    private InfoLectureService infoLectureService;
    @Autowired
    private EntireLectureService entireLectureService;
    @Autowired
    private EnglishLectureService englishLectureService;
    @Autowired
    private DesignLectureService designLectureService;

    @Override
    public void inputLecture(GetLectureInfoIncludeSemesterDTO getLectureDTO) {
        infoLectureService.inputInfoLecture(getLectureDTO);
        entireLectureService.inputEntireLecture(getLectureDTO);
        englishLectureService.inputEnglishLecture(getLectureDTO);
        designLectureService.inputDesignLecture(getLectureDTO);
    }
}

