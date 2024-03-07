package wandogis.wandogi.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.annotations.ColumnDefault;
import org.springframework.data.annotation.Id;

import javax.persistence.Column;
import javax.persistence.OneToMany;
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
    private String description;
    private String category;
    private int page;  // 진도율 저장을 위한 페이지 수
    @ColumnDefault("0")
    private int view;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;
    private Boolean success;    // 챌린지 성공 여부(실패: False, 성공: True)

    @Builder
    public Challenges(ObjectId id, String isbn, String title, String author, String photo, String description,
                      String category, int page, int view, Date startDate, Date endDate, Boolean success) {
        this.id = id;
        this.isbn = isbn;
        this.title = title;
        this.author = author;
        this.photo = photo;
        this.description = description;
        this.category = category;
        this.page = page;
        this.view = view;
        this.startDate = startDate;
        this.endDate = endDate;
        this.success = success;
    }
}
