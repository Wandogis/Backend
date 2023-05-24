package wandogis.wandogi.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import javax.persistence.Convert;
import java.time.LocalDateTime;

@Data
public class Posts {
    private String title;
    private String content;
    private String user;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime createDate;
}
