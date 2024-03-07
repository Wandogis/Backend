package wandogis.wandogi.gpt;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;
@Getter
@NoArgsConstructor
public class Choice implements Serializable {
    @JsonProperty("message")
    private Message message;
    @JsonProperty("index")
    private Integer index;
    @JsonProperty("finish_reason")
    private String finishReason;

    @Builder
    public Choice(Message message, Integer index, String finishReason) {
        this.message = message;
        this.index = index;
        this.finishReason = finishReason;
    }

}


//Choice 클래스: 질문에 대한 선택지를 나타내는 클래스
//message: 선택지와 관련된 메시지 객체
//index: 선택지의 인덱스.
//finishReason: 선택지의 종료 이유
//Choice 클래스의 생성자: message, index, finishReason를 인자로 받아 객체를 생성