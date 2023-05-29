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

    @GetMapping("/list/expected-view")
    public String viewList(Model model) {
        List<Challenges> expectedList = challengeService.getChallengeListsByStartDate();
        List<Challenges> viewList = challengeService.getChallengeListsByView(expectedList);
        model.addAttribute("viewList", viewList);
        return "challenges/expectedViewList";
    }

    @GetMapping("/list/expected-latest")
    public String latestList(Model model) {
        List<Challenges> expectedList = challengeService.getChallengeListsByStartDate();
        List<Challenges> latestList = challengeService.getChallengeListsByDate(expectedList);
        model.addAttribute("latestList", latestList);
        return "challenges/expectedLatestList";
    }
}
