package wandogis.wandogi.gpt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class ChatGptRequestDto {
    private String model;
    private List<Message> messages;


    //챗지피티 requestdto생성자
    @Builder
    public ChatGptRequestDto(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }


    //객체 문자열 표현
    @Override
    public String toString() {
        return "ChatGptRequestDto{" +
                "model='" + model + '\'' +
                ", messages=" + messages +
                '}';
    }
}

