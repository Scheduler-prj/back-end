package PlanQ.PlanQ.Member;

import PlanQ.PlanQ.security.oauth2.CustomOAuth2UserService;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.time.LocalDateTime;

@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@ToString
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private String email;

    @Column
    private String nickname;

    @Column
    private String userRole;

    @Column
    private String provider;

    @Column
    private String profile;

    @Column
    private LocalDateTime regDate;

    @Column
    private String tier;

    public Member(String email, String nickname, String provider, String profile) {
        this.email = email;
        this.nickname = nickname;
        this.provider = provider;
        this.profile = profile;
        this.regDate = LocalDateTime.now();
        this.tier = "N";
        this.userRole = "USER";
    }
}
