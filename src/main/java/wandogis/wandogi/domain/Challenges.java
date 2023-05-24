package wandogis.wandogi.domain;

import lombok.Data;

import java.sql.Array;
import java.sql.Date;

@Data
public class Challenges {
    private String isbn;
    private String title;
    private String author;
    private String photo;
    private Date startDate;
    private Date endDate;
    private Array people;   // 챌린지 참여한 사람들 Users 배열
    private Boolean success;    // 챌린지 성공 여부
    private Array progress;    // 진도율(people 배열과 동일한 index를 가진 값이 그 유저의 진도율)
}
