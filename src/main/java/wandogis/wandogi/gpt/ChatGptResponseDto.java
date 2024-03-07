package wandogis.wandogi.gpt;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Getter
@NoArgsConstructor
@Data
@Document(collection = "book_test")
public class ChatGptResponseDto implements Serializable {
    @Id  // Move this annotation back here
    private String id;
    private String object;
    private String created;
    private String model;
    private List<Choice> choices;

    // Remove @Id annotation from this field
    private String title;
    private String isbn;

    @Builder
    public ChatGptResponseDto(String id, String object,
                              String created, String model,  // Change this line
                              List<Choice> choices) {
        this.id = id;
        this.object = object;
        this.created = created;  // And this line
        this.model = model;
        this.choices = choices;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

}

