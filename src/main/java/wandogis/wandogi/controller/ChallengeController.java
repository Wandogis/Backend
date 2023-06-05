package wandogis.wandogi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.service.ChallengeService;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("challenges")
public class ChallengeController {
    private ChallengeService challengeService;

    /**
     * 진행 예정인 챌린지 목록 인기순
     */
    @GetMapping("/list/expected-view")
    public Object expectedViewList() {
        List<Challenges> expectedList = challengeService.getChallengeListByStartDate();
        List<Challenges> viewList = challengeService.getChallengeListByView(expectedList);
        return viewList;
    }

    /**
     * 진행 예정인 챌린지 목록 최신순
     */
    @GetMapping("/list/expected-latest")
    public Object expectedLatestList() {
        List<Challenges> expectedList = challengeService.getChallengeListByStartDate();
        List<Challenges> latestList = challengeService.getChallengeListByDate(expectedList);
        return latestList;
    }

    /**
     * 진행 중인 챌린지 인기순
     */
    @GetMapping("/list/going-view")
    public Object goingViewList() {
        List<Challenges> goingList = challengeService.getChallengeListByEndDateAndStartDate();
        List<Challenges> viewList = challengeService.getChallengeListByView(goingList);
        return viewList;
    }

    /**
     * 진행 중인 챌린지 최신순
     */
    @GetMapping("/list/going-latest")
    public Object goingLatestList() {
        List<Challenges> goingList = challengeService.getChallengeListByEndDateAndStartDate();
        List<Challenges> latestList = challengeService.getChallengeListByDate(goingList);
        return latestList;
    }

    /**
     * isbn이 같은 진행 예정 챌린지
     */
    @GetMapping("/list/expected")
    public Object expectedLatestListByIsbn(@RequestParam String isbn) {
        List<Challenges> isbnList = challengeService.getChallengeListByIsbnAndDate(isbn);
        List<Challenges> isbnLatestList = challengeService.getChallengeListByDate(isbnList);
        return isbnLatestList;
    }
}
