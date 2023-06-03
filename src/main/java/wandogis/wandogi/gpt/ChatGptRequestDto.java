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

    @Builder
    public ChatGptRequestDto(String model, List<Message> messages) {
        this.model = model;
        this.messages = messages;
    }

    @Override
    public String toString() {
        return "ChatGptRequestDto{" +
                "model='" + model + '\'' +
                ", messages=" + messages +
                '}';
    }
}

