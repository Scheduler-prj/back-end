package PlanQ.PlanQ.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
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
        System.out.println("hi");
        return ResponseEntity.ok().body("ok");
    }

    //test
    @GetMapping("/test")
    public ResponseEntity<?> test() {
        return ResponseEntity.ok().body("ok");
    }

//    // 로그인
//    @GetMapping("/")
//    public ResponseEntity<?> login() {
//        return ResponseEntity.ok().body("ok");
//    }
}
