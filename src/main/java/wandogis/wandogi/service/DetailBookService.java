package wandogis.wandogi.service;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.nio.charset.StandardCharsets;

@Service
public class DetailBookService {
    /**
     * 도서 상세 정보
     * @param isbn isbn
     * @return isbn, title, cover, author, publisher, pubDate, description, itemPage 포함된 json
     */
    public JSONObject getBookDetailInfoFromAladin(String isbn) throws ParseException {
        JSONObject result = new JSONObject();

        String detailUrl = "https://www.aladin.co.kr/ttb/api/ItemLookUp.aspx?ttbkey=ttbkeum45151359001&output=js&Version=20131101&";

        String isbnType;    // isbn이 13자리일 경우와 10자리일 경우 나눠야 함
        if (isbn.length() < 13) isbnType = "ISBN";
        else isbnType = "ISBN13";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = URI.create(UriComponentsBuilder
                .fromUriString(detailUrl)
                .queryParam("ItemId", isbn)
                .queryParam("itemIdType", isbnType)
                .encode(StandardCharsets.UTF_8)
                .toUriString());
        JSONObject list = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, JSONObject.class).getBody();

        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(list.toString());
        JSONArray items = (JSONArray) object.get("item");
        JSONObject data = (JSONObject) items.get(0);

        result.put("isbn", isbn);
        result.put("title", data.get("title"));
        result.put("img", data.get("cover"));
        result.put("author", data.get("author"));
        result.put("description", data.get("description"));
        result.put("pubDate", data.get("pubDate"));
        result.put("publisher", data.get("publisher"));
        JSONObject subInfo = (JSONObject) data.get("subInfo");
        result.put("page", subInfo.get("itemPage"));
        return result;
    }
}
