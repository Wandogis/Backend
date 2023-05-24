package wandogis.wandogi.domain;

import lombok.Data;
import org.springframework.data.annotation.CreatedDate;

import javax.persistence.Column;
import java.time.LocalDateTime;

@Data
public class Comments {
    private String user;
    private String content;
    @CreatedDate
    @Column(updatable = false, nullable = false)
    private LocalDateTime creatDate;
}
