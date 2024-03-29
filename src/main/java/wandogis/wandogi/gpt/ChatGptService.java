package wandogis.wandogi.gpt;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import wandogis.wandogi.repository.ChatGptResponseRepository;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import wandogis.wandogi.service.DetailBookService;

@Service
public class ChatGptService {
    private static final Logger logger = LoggerFactory.getLogger(ChatGptService.class);
    private static RestTemplate restTemplate = new RestTemplate();

    private final ChatGptResponseRepository responseRepository;

    private final DetailBookService detailBookService;

    public ChatGptService(ChatGptResponseRepository responseRepository, DetailBookService detailBookService) {
        this.responseRepository = responseRepository;
        this.detailBookService = detailBookService;
    }

    //ChatGptRequestDto 객체를 기반으로 HttpEntity를 생성
    public HttpEntity<ChatGptRequestDto> buildHttpEntity(ChatGptRequestDto requestDto) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(ChatGptConfig.MEDIA_TYPE));
        headers.add(ChatGptConfig.AUTHORIZATION, ChatGptConfig.BEARER + ChatGptConfig.API_KEY);
        return new HttpEntity<>(requestDto, headers);
    }

    //gpt로부터 응답 받아옴
    public ChatGptResponseDto getResponse(HttpEntity<ChatGptRequestDto> chatGptRequestDtoHttpEntity) {
        ResponseEntity<ChatGptResponseDto> responseEntity = restTemplate.postForEntity(
                ChatGptConfig.URL,
                chatGptRequestDtoHttpEntity,
                ChatGptResponseDto.class);

        return responseEntity.getBody();
    }

    //gpt에 질문
    public ChatGptResponseDto askQuestion(QuestionRequestDto requestDto) {
        ChatGptRequestDto chatGptRequestDto = new ChatGptRequestDto(ChatGptConfig.MODEL, requestDto.getMessages());

        ChatGptResponseDto response = this.getResponse(
                this.buildHttpEntity(chatGptRequestDto)
        );

        ObjectMapper mapper = new ObjectMapper();
        for (Choice choice : response.getChoices()) {
            String content = choice.getMessage().getContent();

            try {
                JsonNode jsonNode = mapper.readTree(content);
                String bookTitle = jsonNode.get("title").asText(); // Here
                String isbn = jsonNode.get("isbn").asText(); // And here

                saveResponse(response, bookTitle, isbn);
            } catch (IOException e) {
                logger.error("Error occurred while parsing the content: ", e);
            }
        }

        for (Choice choice : response.getChoices()) {
            String content = choice.getMessage().getContent();

            try {
                JsonNode jsonNode = mapper.readTree(content);
                String bookTitle = jsonNode.get("title").asText(); // Here
                String isbn = jsonNode.get("isbn").asText(); // And here

                saveResponse(response, bookTitle, isbn);

                // Aladin API 호출 및 도서 정보 저장
                detailBookService.getBookDetailInfoFromAladin(isbn);
            } catch (IOException e) {
                logger.error("Error occurred while parsing the content: ", e);
            } catch (ParseException e) {
                logger.error("Error occurred while getting book details: ", e);
            }
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

    public void saveResponse(ChatGptResponseDto response, String title, String isbn) {
        try {
            response.setTitle(title);   // Set book title
            response.setIsbn(isbn);             // Set ISBN
            responseRepository.save(response);  // Save the response to MongoDB
        } catch (Exception e) {
            logger.error("Error occurred while saving the response: ", e);
        }
    }
}
