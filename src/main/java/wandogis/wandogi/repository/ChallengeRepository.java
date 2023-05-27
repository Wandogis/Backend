package wandogis.wandogi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.domain.Challenges;

import java.util.List;

public interface ChallengeRepository extends MongoRepository<Challenges, ObjectId> {
    public List<Challenges> findAll(); // 진행 중인 챌린지 목록
}