package wandogis.wandogi.service;

import org.springframework.stereotype.Service;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.repository.ChallengeRepository;

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
    public List<Challenges> getChallengeListByStartDate() {
        return challengeRepository.findAllByStartDateAfter(LocalDateTime.now());
    }

    /**
     * 진행 중인 챌린지 목록
     */
    public List<Challenges> getChallengeListByEndDateAndStartDate() {
        return challengeRepository.findAllByEndDateAfterAndStartDateBefore(LocalDateTime.now(), LocalDateTime.now());
    }

    /**
     * 챌린지 목록 인기순
     */
    public List<Challenges> getChallengeListByView(List<Challenges> list) {
        if (list == null) return null;
        Collections.sort(list, new ChallengePeopleComparator());    // view 수로 정렬
        if (list.size() >= 5) return list.subList(0, 5);  // 정렬된 list 중 앞 5개만 뽑음
        else return list;
    }

    /**
     * 챌린지 목록 최신순
     */
    public List<Challenges> getChallengeListByDate(List<Challenges> list) {
        if (list == null) return null;
        Collections.sort(list, new ChallengDateComparator());    // startDate 빠른 순으로 정렬
        if (list.size() >= 5) return list.subList(0, 5);  // 정렬된 리스트 중 앞 5개만 뽑음
        else return list;
    }

    /**
     * 도서 상세 페이지에서 해당 도서로 진행 예정인 챌린지 목록
     */
    public List<Challenges> getChallengeListByIsbnAndDate(String isbn) {
        return challengeRepository.findAllByIsbnEqualsAndStartDateAfter(isbn, LocalDateTime.now());
    }
}

class ChallengePeopleComparator implements Comparator<Challenges> {
    @Override
    public int compare(Challenges o1, Challenges o2) {
        if (o1.getPeople().size() < o2.getPeople().size()) return 1;
        else if (o1.getPeople().size() > o2.getPeople().size()) return -1;
        else return 0;
    }
}

class ChallengDateComparator implements Comparator<Challenges> {
    @Override
    public int compare(Challenges o1, Challenges o2) {
        return (o1.getStartDate().compareTo(o2.getStartDate()));
    }
}
