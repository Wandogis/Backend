package wandogis.wandogi.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.domain.Users;

import javax.persistence.OneToMany;
import java.util.ArrayList;
import java.util.Date;

@Getter
@Setter
public class ChallengeCreateDto {
    @Id
    private ObjectId id;
    private String isbn;
    private String title;
    private String author;
    private String photo;
    private String description;
    private String category;
    private int page;
    @Builder.Default
    private int view = 0;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    private Date endDate;

    public Challenges toEntity() {
        return Challenges.builder().id(id).title(title).author(author).photo(photo)
                .description(description).category(category).page(page).view(view)
                .startDate(startDate).endDate(endDate).build();
    }
}
