package wandogis.wandogi.domain;//테이블정보

import lombok.Data;

@Data
public class Userbooks {
    private String isbn;
    private String title;
    private String genre;
    private String user;
}