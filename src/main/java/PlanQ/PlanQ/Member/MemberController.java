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

//    // 회원가입
//    @GetMapping("/register")
//    public ResponseEntity<?> register(
//            @RequestParam("email") String email,
//            @RequestParam("provider") String provider,
//            @RequestParam("nickname") String nickname,
//            @RequestParam("profile_image_url") String profile_image_url) {
//
//        memberService.register(email, provider, nickname, profile_image_url);
//        return ResponseEntity.ok().body("ok");
//    }

    //로그인 성공
    @GetMapping("/loginSuccess")
    public ResponseEntity<?> loginSuccess() {
        return ResponseEntity.ok().body("ok");
    }

    //test
    @GetMapping("/test")
    public ResponseEntity<?> test() {



        return ResponseEntity.ok().body(memberService.getMember());
    }

}
