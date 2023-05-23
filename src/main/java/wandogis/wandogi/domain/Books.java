package wandogis.wandogi.domain;//테이블정보

import lombok.Data;

@Data
public class Books {
    private String isbn;
    private String title;
    private String genre;
    private String author;
    private String user;
    private String photo;
    private String score;
}
