package wandogis.wandogi.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;

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
    private int view;
    private Date startDate;
    private Date endDate;
    private ArrayList<ObjectId> people;   // 챌린지 참여한 사람들 Users 배열
    private ArrayList<Double> progress;    // 진도율(people 배열과 동일한 index를 가진 값이 그 유저의 진도율)
    private int success;    // 챌린지 성공 여부(0:실패, 1:성공, 2:진행중)

    @Builder
    public Challenges(ObjectId id, String isbn, String title, String author, String photo,
                      int view, Date startDate, Date endDate, ArrayList<ObjectId> people, ArrayList<Double> progress, int success) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.photo = photo;
        this.view = view;
        this.startDate = startDate;
        this.endDate = endDate;
        this.people = people;
        this.progress = progress;
        this.success = success;
    }
}
