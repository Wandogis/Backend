package wandogis.wandogi.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import wandogis.wandogi.gpt.ChatGptResponseDto;

public interface ChatGptResponseRepository extends MongoRepository<ChatGptResponseDto, String> {
}
