package wandogis.wandogi.gpt;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.Serializable;
import java.util.List;

@Getter
@NoArgsConstructor
public class QuestionRequestDto implements Serializable {
    private List<Message> messages;  // Change this line

    @Builder
    public QuestionRequestDto(List<Message> messages) {  // And this line
        this.messages = messages;
    }
}
