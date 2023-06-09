package graduationProject.graduation_judge.domain.Lecture.Service;

import graduationProject.graduation_judge.DAO.DesignLecture;
import graduationProject.graduation_judge.DTO.Lecture.DesignLectureDTO;
import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoIncludeSemesterDTO;
import graduationProject.graduation_judge.DTO.Lecture.GetLectureInfo.GetLectureInfoListDTO;
import graduationProject.graduation_judge.domain.Lecture.repository.DesignLectureRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DesignLectureServiceImpl implements DesignLectureService{
    @Autowired
    private DesignLectureRepository designLectureRepository;
    //infolecture에 넣은 후에 entirelecture에 넣은 후에 Designlecture에 넣어야함
    //(학기), (학수번호), (설계학점) 넣기

    @Override //DesignLecture table에 추가
    public void inputDesignLecture(GetLectureInfoIncludeSemesterDTO getLectureDTO) {
        //(학기), (학수번호), (설계학점) 넣기
        List<GetLectureInfoListDTO> lectureInfoList = getLectureDTO.getLectureDataList();

        String termNumber = getLectureDTO.getYear()+"_"+ getLectureDTO.getSemester();
        for (GetLectureInfoListDTO lectureData : lectureInfoList){
            if (Float.parseFloat(lectureData.getDesignCredit()) != 0.0){
                DesignLectureDTO designLectureDTO = new DesignLectureDTO(
                        termNumber,
                        lectureData.getClassNumber(),
                        Float.parseFloat(lectureData.getDesignCredit())
                        );
                designLectureRepository.save(designLectureDTO.toEntity());
            }
        }
    }

    @Override //DesignLecture (학기, 학수번호)에 해당하는 tuple table에서 제거
    public void deleteDesignLectureTuple(String termNumber, String classNumber) {
        designLectureRepository.deleteAllByTermNumAndClassNum(termNumber, classNumber);
    }

    @Override //DesignLecture table 수정 ? 필요한가 ?
    public void modifyDesignLectureTuple(DesignLectureDTO designLectureDTO) {
        designLectureRepository.save(designLectureDTO.toEntity());
    }

    @Override //전체 테이블 삭제
    public void deleteDesignLectureTable() {
        designLectureRepository.deleteAll();
    }

}
