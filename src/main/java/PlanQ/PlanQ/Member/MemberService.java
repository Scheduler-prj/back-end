package PlanQ.PlanQ.Member;

import PlanQ.PlanQ.security.Dto.SecurityUserDto;
import PlanQ.PlanQ.security.Jwt.JwtUtil;
import PlanQ.PlanQ.security.oauth2.CustomOAuth2UserService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class MemberService {

    private final MemberRepository memberRepository;
    private final JwtUtil  jwtUtil;

    public void register(String email, String provider, String nickname, String profile_image_url) {

        Member member = new Member(email,nickname,provider,profile_image_url);
        memberRepository.save(member);
    }

    public Optional<Member> findByEmail(String email) {

        return memberRepository.findByEmail(email);
    }

    public Member findByAccessToken(String accessToken) {
        String email = jwtUtil.getUid(accessToken);
        return memberRepository.findByEmail(email).get();
    }

    public SecurityUserDto getMember() {
        // 사용자 정보 받아오기
        return (SecurityUserDto) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
