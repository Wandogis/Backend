package wandogis.wandogi.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import wandogis.wandogi.domain.Challenges;
import wandogis.wandogi.service.ChallengeService;

import java.util.List;

@Controller
@AllArgsConstructor
@RequestMapping("challenges")
public class ChallengeController {
    private ChallengeService challengeService;

    /**
     * 진행 예정인 챌린지 목록 인기순
     */
    @GetMapping("/list/expected-view")
    public String expectedViewList(Model model) {
        List<Challenges> expectedList = challengeService.getChallengeListsByStartDate();
        List<Challenges> viewList = challengeService.getChallengeListsByView(expectedList);
        model.addAttribute("viewList", viewList);
        return "challenges/expectedViewList";
    }

    /**
     * 진행 예정인 챌린지 목록 최신순
     */
    @GetMapping("/list/expected-latest")
    public String expectedLatestList(Model model) {
        List<Challenges> expectedList = challengeService.getChallengeListsByStartDate();
        List<Challenges> latestList = challengeService.getChallengeListsByDate(expectedList);
        model.addAttribute("latestList", latestList);
        return "challenges/expectedLatestList";
    }

    /**
     * 진행 중인 챌린지 인기순
     */
    @GetMapping("/list/going-view")
    public String goingViewList(Model model) {
        List<Challenges> goingList = challengeService.getChallengeListsByEndDateAndStartDate();
        List<Challenges> viewList = challengeService.getChallengeListsByView(goingList);
        model.addAttribute("viewList", viewList);
        return "challenges/goingViewList";
    }

    /**
     * 진행 중인 챌린지 최신순
     */
    @GetMapping("/list/going-latest")
    public String goingLatestList(Model model) {
        List<Challenges> goingList = challengeService.getChallengeListsByEndDateAndStartDate();
        List<Challenges> latestList = challengeService.getChallengeListsByDate(goingList);
        model.addAttribute("latestList", latestList);
        return "challenges/goingLatestList";
    }
}
