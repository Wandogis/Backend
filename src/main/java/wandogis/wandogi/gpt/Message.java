package wandogis.wandogi.gpt;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Getter
@NoArgsConstructor
public class Message{
    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    private String role;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    private String content;

}

//Message 클래스: 메시지를 나타내는 클래스
//role: 메시지의 역할을 나타내는 필드
//content: 메시지의 내용을 나타내는 필드
//getRole(): role 필드의 값을 반환하는 메서드
//setRole(): role 필드에 값을 설정하는 메서드
//getContent(): content 필드의 값을 반환하는 메서드
//setContent(): content 필드에 값을 설정하는 메서드