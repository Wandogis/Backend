package wandogis.wandogi.service;

import org.springframework.stereotype.Service;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.repository.ChallengeRepository;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
     * 진행 예정 챌린지 목록
     */
    public List<Challenges> getChallengeListsByStartDate() {
        return challengeRepository.findAllByStartDateAfter(LocalDateTime.now());
    }

    /**
     * 진행 중인 챌린지 목록
     */
    public List<Challenges> getChallengeListsByEndDateAndStartDate() {
        return challengeRepository.findAllByEndDateAfterAndStartDateBefore(LocalDateTime.now(), LocalDateTime.now());
    }

    /**
     * 챌린지 목록 인기순
     */
    public List<Challenges> getChallengeListsByView(List<Challenges> list) {
        Collections.sort(list, new ChallengeViewComparator());    // view 수로 정렬
        return list.subList(0, 3);    // 정렬된 리스트 중 앞 5개만 뽑음
    }

    /**
     * 챌린지 목록 최신순
     */
    public List<Challenges> getChallengeListsByDate(List<Challenges> list) {
        Collections.sort(list, new ChallengDateComparator());    // startDate 빠른 순으로 정렬
        return list.subList(0, 3);    // 정렬된 리스트 중 앞 5개만 뽑음
    }
}

class ChallengeViewComparator implements Comparator<Challenges> {
    @Override
    public int compare(Challenges o1, Challenges o2) {
        if (o1.getView() < o2.getView()) return 1;
        else if (o1.getView() > o2.getView()) return -1;
        else return 0;
    }
}

class ChallengDateComparator implements Comparator<Challenges> {
    @Override
    public int compare(Challenges o1, Challenges o2) {
        return (o1.getStartDate().compareTo(o2.getStartDate()));
    }
}
