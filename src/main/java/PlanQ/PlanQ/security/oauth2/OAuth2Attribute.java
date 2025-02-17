package PlanQ.PlanQ.security.oauth2;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.HashMap;
import java.util.Map;

@ToString
@Builder(access = AccessLevel.PRIVATE) // Builder 메서드를 외부에서 사용하지 않으므로 private로 설정
@Getter
public class OAuth2Attribute {
    private Map<String, Object> attributes; // 사용자 속성 정보를 담는 Map
    private String attributeKey; // 사용자 속성의 키 값
    private String email; // 이메일
    private String nickname; // 이름
    private String profile_image_url; // 프로필 사진 url
    private String provider; // 제공자 정보

    // 서비스에 따라 OAuth2Attribute 객체를 생성하는 메서드
    static OAuth2Attribute of(String provider, String attributeKey, Map<String, Object> attributes) {
        switch (provider) {
            case "google":
                return ofGoogle(provider,attributeKey,attributes);
            case "kakao":
                return ofKakao(provider,attributeKey,attributes);
            case "naver":
                return ofNaver(provider,attributeKey,attributes);
            default:
                throw new RuntimeException();
        }
    }

    // 구글로그인 메서드, 사용자 정보가 따로 Wrapping 되어있지 않아 get() 메서드로 접근 가능
    private static OAuth2Attribute ofGoogle(String provider, String attributeKey, Map<String, Object> attributes) {

        return OAuth2Attribute.builder()
                .email((String) attributes.get("email"))
                .nickname((String) attributes.get("name"))
                .profile_image_url((String) attributes.get("picture"))
                .provider(provider)
                .attributes(attributes)
                .attributeKey(attributeKey)
                .build();
    }

    // 카카오로그인 메서드
    // 필요한 사용자 정보가 kakaoAccount -> kakaoProfile 두번 감싸져 있어서, 두번 get() 메서드를 이용해 사용자 정보를 담고있는 Map을 꺼내야한다.
    private static OAuth2Attribute ofKakao(String provider, String attributeKey, Map<String, Object> attributes) {
        Map<String, Object> kakaoAccount = (Map<String,Object>) attributes.get("kakao_account");
        Map<String, Object> kakaoProfile = (Map<String,Object>) kakaoAccount.get("profile");

        return OAuth2Attribute.builder()
                .email((String) kakaoAccount.get("email"))
                .nickname((String) kakaoProfile.get("nickname"))
                .profile_image_url((String) kakaoProfile.get("profile_image_url"))
                .provider(provider)
                .attributes(kakaoAccount)
                .attributeKey(attributeKey)
                .build();
    }

    // 네이버로그인 메서드
    // 필요한 정보가 response Map에 감싸져 있어 한번 get() 메서드를 이용해 사용자 정보를 답고있는 Map을 꺼내야 한다.
    private static OAuth2Attribute ofNaver(String provider, String attributeKey, Map<String, Object> attributes) {
        Map<String,Object> response = (Map<String,Object>) attributes.get("response");
        System.out.println("response.toString() = " + response.toString());
        return OAuth2Attribute.builder()
                .email((String) response.get("email"))
                .nickname((String) response.get("nickname"))
                .profile_image_url((String) response.get("profile_image"))
                .attributes(response)
                .provider(provider)
                .attributeKey(attributeKey)
                .build();
    }

    // OAuth2User 객체에 넣어주기 위해 Map으로 값 반환
    Map<String,Object> convertToMap() {
        Map<String,Object> map = new HashMap<>();
        map.put("id", attributeKey);
        map.put("key",attributeKey);
        map.put("email",email);
        map.put("profile_image_url",profile_image_url);
        map.put("nickname",nickname);
        map.put("provider",provider);

        return map;
    }
}
