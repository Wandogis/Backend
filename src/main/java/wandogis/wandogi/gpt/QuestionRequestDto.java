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

//QuestionRequestDto 클래스: 질문 요청을 나타내는 데이터 전송 객체(dto)
//messages: 메시지 목록을 나타내는 필드로, 챗봇과의 대화에서 전달되는 메시지들 담음
//QuestionRequestDto 클래스의 생성자: messages를 인자로 받아 객체를 생성