package wandogis.wandogi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.domain.Users;

public interface UsersMongoDBRepository extends MongoRepository<Users, String> {
    public Users findByName(String name);
}


