package wandogis.wandogi.authentication.application;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import wandogis.wandogi.authentication.domain.AuthTokens;
import wandogis.wandogi.authentication.domain.AuthTokensGenerator;
import wandogis.wandogi.authentication.domain.oauth.OAuthInfoResponse;
import wandogis.wandogi.authentication.domain.oauth.OAuthLoginParams;
import wandogis.wandogi.authentication.domain.oauth.RequestOAuthInfoService;
import wandogis.wandogi.domain.Users;
import wandogis.wandogi.repository.UsersRepository;

@Service
@RequiredArgsConstructor
public class OAuthLoginService {
    private final UsersRepository usersRepository;
    private final AuthTokensGenerator authTokensGenerator;
    private final RequestOAuthInfoService requestOAuthInfoService;

    public AuthTokens login(OAuthLoginParams params) {
        OAuthInfoResponse oAuthInfoResponse = requestOAuthInfoService.request(params);
        ObjectId userId = findOrCreateMember(oAuthInfoResponse);
        return authTokensGenerator.generate(userId);
    }

    private ObjectId findOrCreateMember(OAuthInfoResponse oAuthInfoResponse) {
        return usersRepository.findByEmail(oAuthInfoResponse.getEmail())
                .map(Users::getId)
                .orElseGet(() -> newMember(oAuthInfoResponse));
    }

    private ObjectId newMember(OAuthInfoResponse oAuthInfoResponse) {
        Users user = Users.builder()
                .email(oAuthInfoResponse.getEmail())
                .nickname(oAuthInfoResponse.getNickname())
                .photo(oAuthInfoResponse.getPhoto())
                .oAuthProvider(oAuthInfoResponse.getOAuthProvider())
                .build();

        return usersRepository.save(user).getId();
    }
}
