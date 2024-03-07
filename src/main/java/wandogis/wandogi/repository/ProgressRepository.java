package wandogis.wandogi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.domain.Progress;

import java.util.List;

public interface ProgressRepository extends MongoRepository<Progress, ObjectId> {
    public List<Progress> findAllByChallenge(Challenges challenge);
}
