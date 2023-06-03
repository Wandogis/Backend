package wandogis.wandogi.controller;

import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wandogis.wandogi.service.DetailBookService;

@RestController
@AllArgsConstructor
@RequestMapping("detail")
public class DetailBookController {
    private DetailBookService detailBookService;

    /**
     * 도서 상세 페이지 - 알라딘 API 이용
     */
    @GetMapping("")
    public Object getBookDetailInfo(@RequestParam String isbn) throws ParseException {
        return detailBookService.getBookDetailInfoFromAladin(isbn);
    }
}
