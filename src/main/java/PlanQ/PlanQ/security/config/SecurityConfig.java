package PlanQ.PlanQ.security.config;

import PlanQ.PlanQ.security.config.Handler.MyAuthenticationFailureHandler;
import PlanQ.PlanQ.security.config.Handler.MyAuthenticationSuccessHandler;
import PlanQ.PlanQ.security.Jwt.JwtAuthFilter;
import PlanQ.PlanQ.security.Jwt.JwtExceptionFilter;
import PlanQ.PlanQ.security.oauth2.CustomOAuth2UserService;
import jakarta.servlet.DispatcherType;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity(debug = false)
@RequiredArgsConstructor
public class SecurityConfig {
    private final MyAuthenticationSuccessHandler oAuth2LoginSuccessHandler;
    private final CustomOAuth2UserService customOauth2UserService;
    private final JwtAuthFilter jwtAuthFilter;
    private final MyAuthenticationFailureHandler oAuth2LoginFailureHandler;
    private final JwtExceptionFilter jwtExceptionFilter;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .httpBasic(AbstractHttpConfigurer::disable) //http 기본 인증 비활성화
                .cors(AbstractHttpConfigurer::disable)
                .csrf(AbstractHttpConfigurer::disable) //csrf 보호 기능 비활성화
                .sessionManagement(c ->
                        c.sessionCreationPolicy((SessionCreationPolicy.STATELESS))) // 세션관리 정책을 STATELESS(세션이 있으면 쓰지도 않고, 없으면 만들지도 않는다)
                .formLogin(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(request ->
                        request
                                .requestMatchers( "/**").permitAll()                           // 토큰 발급을 위한 경로는 모두 허용
                                .anyRequest().authenticated() // 그외 모든 요청은 인증 필요
                )

//                .formLogin(auth -> auth
//                        .loginPage("/login")
//                        .permitAll()
//                )
                .oauth2Login(oauth ->
                        oauth.userInfoEndpoint(c -> c.userService(customOauth2UserService)) // OAuth2 로그인시 사용자 정보를 가져오는 앤드포인트와 사용자 서비스를 설정
                        .failureHandler(oAuth2LoginFailureHandler)  // 로그인 실패시 처리할 핸들러 설정
                        .successHandler(oAuth2LoginSuccessHandler) // 로그인 성공시 처리할 핸들러 설정
                );



        // JWT 인증필터를 UsernamePasswordAuthenticationFilter 앞에 추가한다.
        return http
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .addFilterBefore(jwtExceptionFilter, JwtAuthFilter.class)
                .build();
    }

}
