package graduationProject.graduation_judge.domain.Stats.repository;

import graduationProject.graduation_judge.DAO.ScoreStat;
import graduationProject.graduation_judge.DAO.identifier.ScoreStatPK;
import graduationProject.graduation_judge.DTO.Stats.ScoreStatDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ScoreStatRepository extends JpaRepository<ScoreStat, ScoreStatPK> {
    List<ScoreStat> findAllByMemberId(String memberId);
    void deleteAllByMemberId(String memberId);
    ScoreStat findBySemesterAndMemberIdAndTypeId(int semester, String memberId, String typeId);

    List<ScoreStat> findAllBySemesterAndTypeId(int semester, String typeId);

    List<ScoreStatDTO> findByMemberId(String memberId);


}
