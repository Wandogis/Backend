package wandogis.wandogi.service;

import org.springframework.stereotype.Service;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.repository.ChallengeRepository;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;

    public ChallengeService(ChallengeRepository challengeRepository) {
        this.challengeRepository = challengeRepository;
    }

    /**
     * 메인페이지 - 진행 중인 챌린지 목록 조회
     * 1. 인기순
     * 2. 최신순
     */
    public List<Challenges> getChallengeListsByView() {
        List<Challenges> result = challengeRepository.findAll();
        Collections.sort(result, new ChallengeViewComparator());    // view 수로 정렬
        return result.subList(0, 1);    // 정렬된 리스트 중 앞 5개만 뽑음
    }

    public List<Challenges> getChallengeListsByDate() {
        List<Challenges> result = challengeRepository.findAll();
        Collections.sort(result, new ChallengDateComparator());    // startDate 빠른 순으로 정렬
        return result.subList(0, 1);    // 정렬된 리스트 중 앞 5개만 뽑음
    }
}

class ChallengeViewComparator implements Comparator<Challenges> {
    @Override
    public int compare(Challenges o1, Challenges o2) {
        if (o1.getView() >= o2.getView()) return 1;
        else return 0;
    }
}

class ChallengDateComparator implements Comparator<Challenges> {
    @Override
    public int compare(Challenges o1, Challenges o2) {
        if (o1.getStartDate().before(o2.getStartDate())) return 1;
        else return 0;
    }
}
