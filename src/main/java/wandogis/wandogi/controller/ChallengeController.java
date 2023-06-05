package wandogis.wandogi.controller;

import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
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
        List<Challenges> expectedList = challengeService.getChallengeListsByStartDate();
        List<Challenges> viewList = challengeService.getChallengeListsByView(expectedList);
        return viewList;
    }

    /**
     * 진행 예정인 챌린지 목록 최신순
     */
    @GetMapping("/list/expected-latest")
    public Object expectedLatestList() {
        List<Challenges> expectedList = challengeService.getChallengeListsByStartDate();
        List<Challenges> latestList = challengeService.getChallengeListsByDate(expectedList);
        return latestList;
    }

    /**
     * 진행 중인 챌린지 인기순
     */
    @GetMapping("/list/going-view")
    public Object goingViewList() {
        List<Challenges> goingList = challengeService.getChallengeListsByEndDateAndStartDate();
        List<Challenges> viewList = challengeService.getChallengeListsByView(goingList);
        return viewList;
    }

    /**
     * 진행 중인 챌린지 최신순
     */
    @GetMapping("/list/going-latest")
    public Object goingLatestList() {
        List<Challenges> goingList = challengeService.getChallengeListsByEndDateAndStartDate();
        List<Challenges> latestList = challengeService.getChallengeListsByDate(goingList);
        return latestList;
    }
}
