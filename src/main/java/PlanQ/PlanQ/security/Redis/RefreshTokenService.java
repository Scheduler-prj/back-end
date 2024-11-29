package PlanQ.PlanQ.security.Redis;


import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Slf4j
public class RefreshTokenService {

    private final RefreshTokenRepository repository;

    @Transactional
    public void saveTokenInfo(String email, String refreshToken, String accessToken) {
        repository.save(new RefreshToken(email, accessToken, refreshToken));
    }

    @Transactional
    public void removeRefreshToken(String accessToken) {
        RefreshToken token = repository.findByAccessToken(accessToken)
                .orElseThrow(IllegalArgumentException::new);

        repository.delete(token);
    }
}
