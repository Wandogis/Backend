package wandogis.wandogi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "challenges")
@Data
public class Challenges {
    @Id
    private ObjectId id;
    private String isbn;
    private String title;
    private String author;
    private String photo;
    private int page;  // 진도율 저장을 위한 페이지 수
    private int view;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private ArrayList<ObjectId> people;   // 챌린지 참여한 사람들 Users 배열
    private ArrayList<Double> progress;    // 진도율(people 배열과 동일한 index를 가진 값이 그 유저의 진도율)
    private Boolean success;    // 챌린지 성공 여부(실패: False, 성공: True)

    @Builder
    public Challenges(ObjectId id, String isbn, String title, String author, String photo, int page,
                      int view, Date startDate, Date endDate, ArrayList<ObjectId> people, ArrayList<Double> progress, Boolean success) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.photo = photo;
        this.page = page;
        this.view = view;
        this.startDate = startDate;
        this.endDate = endDate;
        this.people = people;
        this.progress = progress;
        this.success = success;
    }
}
