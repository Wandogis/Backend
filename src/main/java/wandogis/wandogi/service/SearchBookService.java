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
public class SearchBookService {
    /**
     * 도서 검색
     * @param query 검색어
     * @return isbn13, title, cover 포함한 json
     * isbn13이 존재하지 않는 경우, isbn을 보냄. isbn은 10자리
     */
    public JSONObject getBookListFromAladinAPI(String query) throws ParseException {
        JSONObject result = new JSONObject();

        String searchUrl = "https://www.aladin.co.kr/ttb/api/ItemSearch.aspx?QueryType=Title&start=1&SearchTarget=Book&output=js&Version=20131101&ttbkey=ttbkeum45151359001&";

        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpEntity<String> httpEntity = new HttpEntity<>(httpHeaders);
        URI targetUrl = URI.create(UriComponentsBuilder
                .fromUriString(searchUrl)
                .queryParam("Query", query)
                .encode(StandardCharsets.UTF_8)
                .toUriString());
        JSONObject list = restTemplate.exchange(targetUrl, HttpMethod.GET, httpEntity, JSONObject.class).getBody();

        JSONParser parser = new JSONParser();
        JSONObject object = (JSONObject) parser.parse(list.toString());
        JSONArray items = (JSONArray) object.get("item");

        JSONArray item = new JSONArray();
        for (int i = 0; i < items.size(); i++) {
            JSONObject obj = (JSONObject) items.get(i);
            String isbn = (String) obj.get("isbn13");
            if (isbn.length() < 13) isbn = (String) obj.get("isbn");
            JSONObject data = new JSONObject();
            data.put("isbn", isbn);
            data.put("title", (String) obj.get("title"));
            data.put("img", (String) obj.get("cover"));
            item.add(data);
        }
        result.put("item", item);
        return result;
    }
}
