package PlanQ.PlanQ.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RestController;

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
    public ResponseEntity<?> test(@RequestHeader(value = "Authorization") final String accessToken) {
        Member findmember = memberService.findByAccessToken(accessToken);
        return ResponseEntity.ok().body(findmember.getEmail());
    }

}
