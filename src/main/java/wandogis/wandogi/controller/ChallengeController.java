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

    @GetMapping("/view-list")
    public String viewList(Model model) {
        List<Challenges> viewList = challengeService.getChallengeListsByView();
        model.addAttribute("viewList", viewList);
        return "challenges/viewList";
    }
}
