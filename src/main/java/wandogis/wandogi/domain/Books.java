package wandogis.wandogi.domain;//테이블정보

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Data
@Document(collection = "books")
public class Books {
    @Id
    private String id;
    private String isbn;
    private String title;
    private String genre;
    private String author;
    private String user;
    private String photo;
    private String score;
}
