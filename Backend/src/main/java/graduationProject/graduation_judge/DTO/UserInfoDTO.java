package graduationProject.graduation_judge.DTO;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DAO.UserSelectList;
import graduationProject.graduation_judge.global.common_unit.English_level;
import graduationProject.graduation_judge.global.common_unit.Major_curriculum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@Data
@AllArgsConstructor
public class UserInfoDTO {
    private String id;
    private String pincode;
    private int semester;
    private int studentNumber;
    private Major_curriculum course;
    private int toeicScore;
    private English_level englishGrade;

    //이게맞냐
    private List<UserSelectList> selectLists;
    private List<ScoreStat> scoreStats;
}
