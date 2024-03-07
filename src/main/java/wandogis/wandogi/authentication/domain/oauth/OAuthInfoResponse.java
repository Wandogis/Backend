package wandogis.wandogi.authentication.domain.oauth;

public interface OAuthInfoResponse {
    String getEmail();
    String getNickname();
    String getPhoto();
    OAuthProvider getOAuthProvider();
}
