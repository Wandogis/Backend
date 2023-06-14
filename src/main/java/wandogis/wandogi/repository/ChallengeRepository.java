package wandogis.wandogi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.domain.Challenges;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface ChallengeRepository extends MongoRepository<Challenges, ObjectId> {
    public Optional<Challenges> findById(ObjectId id);

    public List<Challenges> findAllByStartDateAfter(LocalDateTime today);  // 진행 예정 챌린지 목록
    public List<Challenges> findAllByEndDateAfterAndStartDateBefore(LocalDateTime today, LocalDateTime now);   // 진행 중인 챌린지 목록

    public List<Challenges> findAllByIsbnEqualsAndStartDateAfter(String isbn, LocalDateTime today);  // isbn이 같은 책 중 진행 예정인 챌린지 몰골
}
