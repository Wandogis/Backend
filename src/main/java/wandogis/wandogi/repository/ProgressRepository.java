package wandogis.wandogi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.domain.Progress;

public interface ProgressRepository extends MongoRepository<Progress, ObjectId> {
}
