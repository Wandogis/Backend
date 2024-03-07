package wandogis.wandogi.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.domain.Progress;
import wandogis.wandogi.domain.Users;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Getter
@Setter
public class ProgressDto {
    @Id
    private ObjectId id;
    @ManyToOne
    private Users user;
    @OneToOne
    private Challenges challenge;

    @Builder.Default
    private Double progress = 0.0;

    public Progress toEntity() {
        return Progress.builder().id(id).user(user).challenge(challenge).progress(progress).build();
    }

    public void setChallenge(Challenges challenge) {
        this.challenge = challenge;
    }
}
