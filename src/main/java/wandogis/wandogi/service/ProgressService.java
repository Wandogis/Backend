package wandogis.wandogi.service;

import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.domain.Progress;
import wandogis.wandogi.dto.ProgressDto;
import wandogis.wandogi.repository.ChallengeRepository;
import wandogis.wandogi.repository.ProgressRepository;

import java.util.Optional;

@Service
public class ProgressService {
    private final ProgressRepository progressRepository;
    private final ChallengeRepository challengeRepository;


    public ProgressService(ProgressRepository progressRepository, ChallengeRepository challengeRepository) {
        this.progressRepository = progressRepository;
        this.challengeRepository = challengeRepository;
    }

    /**
     * 챌린지에 유저가 참여했을 때
     * 유저 추가
     */
    public void saveChallengeUserAndProgress(ObjectId challengeId) {
        ProgressDto progressDto = new ProgressDto();
        Challenges challenge = challengeRepository.findById(challengeId).get();
        progressDto.setChallenge(challenge);
        Progress progress = progressDto.toEntity();
        progressRepository.save(progress);
    }
}
