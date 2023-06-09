package graduationProject.graduation_judge.DTO.Grade;

import graduationProject.graduation_judge.DAO.UserSelectList;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@AllArgsConstructor
public class GradeDTO {

    private String memberId;
    private String termNum;
    private String classNum;
    private String score;

    public UserSelectList toEntity(){
        return UserSelectList.builder()
                .memberId(memberId)
                .termNum(termNum)
                .classNum(classNum)
                .score(score)
                .build();
    }
}
