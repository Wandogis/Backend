package wandogis.wandogi.gpt;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptResponseDto implements Serializable {
    private String id;
    private String object;
    private String created;  // Change this line
    private String model;
    private List<Choice> choices;

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
}

