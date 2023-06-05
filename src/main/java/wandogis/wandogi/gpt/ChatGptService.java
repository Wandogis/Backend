package wandogis.wandogi.gpt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wandogis.wandogi.repository.ChatGptResponseRepository;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class ChatGptService {
    private static RestTemplate restTemplate = new RestTemplate();

    private final ChatGptResponseRepository responseRepository;

    public ChatGptService(ChatGptResponseRepository responseRepository) {
        this.responseRepository = responseRepository;
    }

    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);

        return responseEntity.getBody();
    }

    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
        ChatGptRequestDto chatGptRequestDto = new ChatGptRequestDto(ChatGptConfig.MODEL, requestDto.getMessages());

        ChatGptResponseDto response = this.getResponse(
                this.buildHttpEntity(chatGptRequestDto)
        );

        String content = response.getChoices().get(0).getMessage().getContent();

        // Using a JSON parsing library like Jackson
        ObjectMapper mapper = new ObjectMapper();
        try {
            JsonNode jsonNode = mapper.readTree(content);
            String bookTitle = jsonNode.get("title").asText();
            String isbn = jsonNode.get("isbn").asText();

            saveResponse(response, bookTitle, isbn);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return response;
    }

    private String extractValueFromContent(String content, String key) {
        int keyIndex = content.indexOf(key);
        if (keyIndex != -1) {
            int startQuoteIndex = content.indexOf("\"", keyIndex + key.length() + 2);
            int endQuoteIndex = content.indexOf("\"", startQuoteIndex + 1);
            return content.substring(startQuoteIndex + 1, endQuoteIndex);
        }
        return null;
    }

    public void saveResponse(ChatGptResponseDto response, String bookTitle, String isbn) {
        response.setBookTitle(bookTitle);   // Set book title
        response.setIsbn(isbn);             // Set ISBN
        responseRepository.save(response);  // Save the response to MongoDB
    }
}
