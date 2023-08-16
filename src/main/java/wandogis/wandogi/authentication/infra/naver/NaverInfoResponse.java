package wandogis.wandogi.authentication.infra.naver;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import wandogis.wandogi.authentication.domain.oauth.OAuthInfoResponse;
import wandogis.wandogi.authentication.domain.oauth.OAuthProvider;

// https://openapi.naver.com/v1/nid/me 요청 결과값
// https://developers.naver.com/docs/login/profile/profile.md 참고

@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class NaverInfoResponse implements OAuthInfoResponse {

    @JsonProperty("response")
    private Response response;

    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    static class Response {
        private String email;
        private String nickname;
        private String profile_image;
    }

    @Override
    public String getEmail() {
        return response.email;
    }

    @Override
    public String getNickname() {
        return response.nickname;
    }

    @Override
    public String getPhoto() {
        return response.profile_image;
    }

    @Override
    public OAuthProvider getOAuthProvider() {
        return OAuthProvider.NAVER;
    }
}
