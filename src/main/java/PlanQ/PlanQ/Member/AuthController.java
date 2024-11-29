package PlanQ.PlanQ.Member;

import PlanQ.PlanQ.security.Jwt.JwtUtil;
import PlanQ.PlanQ.security.Dto.StatusResponseDto;
import PlanQ.PlanQ.security.Jwt.TokenResponseStatus;
import PlanQ.PlanQ.security.Redis.RefreshToken;
import PlanQ.PlanQ.security.Redis.RefreshTokenRepository;
import PlanQ.PlanQ.security.Redis.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Slf4j
@RestController
@RequiredArgsConstructor
public class AuthController {

    private final RefreshTokenRepository tokenRepository;
    private final RefreshTokenService tokenService;
    private final JwtUtil jwtUtil;

    @PostMapping("/token/logout")
    public ResponseEntity<StatusResponseDto> logout(@RequestHeader("Authorization") final String accessToken) {

        // 엑세스 토큰으로 현재 Redis 정보 삭제
        tokenService.removeRefreshToken(accessToken);
        return ResponseEntity.ok(StatusResponseDto.addStatus(200));
    }

    @PostMapping("/token/refresh")
    public ResponseEntity<TokenResponseStatus> refresh(@RequestHeader("Authorization") final String accessToken) {
        // 액세스 토큰으로 RefreshToken 조회
        Optional<RefreshToken> refreshToken = tokenRepository.findByAccessToken(accessToken);

        // RefreshToken이 존재하고 유효하다면 실행
        if (refreshToken.isPresent() && jwtUtil.verifyToken(refreshToken.get().getRefreshToken())) {
            // RefreshToken 객체를 꺼내온다.
            RefreshToken resultToken = refreshToken.get();

            // 권한과 아이디를 추출해 새로운 액세스 토큰을 만든다.
            String newAccessToken = jwtUtil.generateAccessToken(resultToken.getId(), jwtUtil.getRole(resultToken.getRefreshToken()));
            // 엑세스 토큰 값 수정
            resultToken.updateAccessToken(newAccessToken);
            tokenRepository.save(resultToken);
            // 새로운 액세스 토큰 반환
            return ResponseEntity.ok(TokenResponseStatus.addStatus(200,newAccessToken));
        }
        return ResponseEntity.badRequest().body(TokenResponseStatus.addStatus(400,null));
    }


}
