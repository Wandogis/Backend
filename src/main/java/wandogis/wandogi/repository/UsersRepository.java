package wandogis.wandogi.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.domain.Users;

import java.util.Optional;

public interface UsersRepository extends MongoRepository<Users, ObjectId> {
    Optional<Users> findByEmail(String email);
}
