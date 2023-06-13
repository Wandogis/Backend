package wandogis.wandogi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.domain.Books;

public interface BookRepository extends MongoRepository<Books, String> {
}
