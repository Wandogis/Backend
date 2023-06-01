package wandogis.wandogi;

import org.json.simple.JSONObject;
import org.json.simple.parser.ParseException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import wandogis.wandogi.service.SearchBookService;

@SpringBootTest()
public class AladinAPITest {
    @Autowired
    private SearchBookService searchBookService;

    @Test
    public void getBookListAladin() throws ParseException {
        JSONObject result = searchBookService.getBookListFromAladinAPI("삼국지");
        System.out.println(result.toJSONString());
    }
}
