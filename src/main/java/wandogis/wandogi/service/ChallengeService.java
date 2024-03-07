package wandogis.wandogi.service;

import org.bson.types.ObjectId;
import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.springframework.stereotype.Service;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.domain.Progress;
import wandogis.wandogi.dto.ChallengeCreateDto;
import wandogis.wandogi.repository.ChallengeRepository;
import wandogis.wandogi.repository.ProgressRepository;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Service
public class ChallengeService {
    private final ChallengeRepository challengeRepository;
    private final ProgressRepository progressRepository;
    private DetailBookService detailBookService;

    public ChallengeService(ChallengeRepository challengeRepository, ProgressRepository progressRepository, DetailBookService detailBookService) {
        this.challengeRepository = challengeRepository;
        this.progressRepository = progressRepository;
        this.detailBookService = detailBookService;
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
     * 챌린지 목록 인기순 - 참여인원 순으로
     */
    public List<Challenges> getChallengeListByPeople(List<Challenges> list) {
        if (list == null) return null;
        Collections.sort(list, new ChallengePeopleComparator(progressRepository));    // view 수로 정렬
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

    /**
     * 챌린지 생성
     * isbn으로 해당 책에 대한 정보를 받아온 뒤, challenges 테이블에 해당 정보를 추가해서 함께 저장
     */
    public ObjectId saveChallenge(ChallengeCreateDto challengeCreateDto, String isbn) throws ParseException {
        JSONObject book = detailBookService.getBookDetailInfoFromAladin(isbn);
        challengeCreateDto.setIsbn((String) book.get("isbn"));
        challengeCreateDto.setTitle((String) book.get("title"));
        challengeCreateDto.setAuthor((String) book.get("author"));
        challengeCreateDto.setPhoto((String) book.get("img"));
        challengeCreateDto.setDescription((String) book.get("description"));
        challengeCreateDto.setCategory((String) book.get("category"));
        challengeCreateDto.setPage(Integer.parseInt(String.valueOf(book.get("page"))));
        Challenges challenge = challengeCreateDto.toEntity();
        challengeRepository.save(challenge);
        return challenge.getId();
    }
}

class ChallengePeopleComparator implements Comparator<Challenges> {
    private final ProgressRepository progressRepository;

    public ChallengePeopleComparator(ProgressRepository progressRepository) {
        this.progressRepository = progressRepository;
    }

    @Override
    public int compare(Challenges o1, Challenges o2) {
        List<Progress> challengesList1 = progressRepository.findAllByChallenge(o1);
        List<Progress> challengesList2 = progressRepository.findAllByChallenge(o2);

        if (challengesList1.size() < challengesList2.size()) return 1;
        else if (challengesList1.size() > challengesList2.size()) return -1;
        else return 0;
    }
}

class ChallengDateComparator implements Comparator<Challenges> {
    @Override
    public int compare(Challenges o1, Challenges o2) {
        return (o1.getStartDate().compareTo(o2.getStartDate()));
    }
}
