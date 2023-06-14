package wandogis.wandogi.domain;

import lombok.*;
import org.bson.types.ObjectId;
import org.hibernate.annotations.ColumnDefault;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "progress")
@Data
public class Progress {
    @Id
    private ObjectId id;
    @ManyToOne
    private Users user;
    @OneToOne
    private Challenges challenge;
    @ColumnDefault("0.0")
    private Double progress;

    @Builder
    public Progress(ObjectId id, Users user, Challenges challenge, Double progress) {
        this.id = id;
        this.user = user;
        this.challenge = challenge;
        this.progress = progress;
    }
}
