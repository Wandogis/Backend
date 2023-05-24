package wandogis.wandogi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.domain.Posts;

public interface PostsMongoDBRepository extends MongoRepository<Posts, String> {
    public Posts findByTitle(String title);
}


