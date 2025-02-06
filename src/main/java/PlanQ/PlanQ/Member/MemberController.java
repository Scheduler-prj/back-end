package PlanQ.PlanQ.Member;

import PlanQ.PlanQ.security.Dto.SecurityUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

import java.security.Security;

@RestController
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    //로그인 성공
    @GetMapping("/loginSuccess")
    public ResponseEntity<?> loginSuccess() {

        return ResponseEntity.ok().body("ok");

    }

    // 엑세스 토큰을 통해 멤버 정보 가져오는 API
    @GetMapping("/findOne")
    public ResponseEntity<?> findOne() {
        return ResponseEntity.ok().body(memberService.getMember());
    }

}
