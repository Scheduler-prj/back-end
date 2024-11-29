package PlanQ.PlanQ.Member;

import PlanQ.PlanQ.security.oauth2.CustomOAuth2UserService;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder
public class MemberService {

    private final MemberRepository memberRepository;


    public void register(String email, String provider, String nickname, String profile_image_url) {

        Member member = new Member(email,nickname,provider,profile_image_url);
        memberRepository.save(member);
    }

    public Optional<Member> findByEmail(String email) {
        return memberRepository.findByEmail(email);
    }
}
