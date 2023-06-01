package wandogis.wandogi.controller;

import lombok.AllArgsConstructor;
import org.json.simple.parser.ParseException;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import wandogis.wandogi.service.SearchBookService;

@RestController
@AllArgsConstructor
@RequestMapping("search")
public class SearchBookController {
    private SearchBookService searchBookService;

    /**
     * 도서 검색 - 알라딘 API 이용
     */
    @GetMapping("/book")
    public Object getBookListAladin(@RequestParam String query) throws ParseException {
        return searchBookService.getBookListFromAladinAPI(query);
    }
}
