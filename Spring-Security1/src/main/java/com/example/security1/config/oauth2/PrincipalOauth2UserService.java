package com.example.security1.config.oauth2;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import com.example.security1.config.auth.PrincipalDetails;
import com.example.security1.config.auth.provider.GoogleUserInfo;
import com.example.security1.config.auth.provider.NaverUserInfo;
import com.example.security1.config.auth.provider.OAuth2UserInfo;
import com.example.security1.model.User;
import com.example.security1.repo.UserRepository;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	
	@Autowired
	@Lazy
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	@Autowired
	private UserRepository userRepository;
	
	// Authentication 객체에 리턴됨 스프링 시큐리티는 이걸 기반으로 Authentication 객체를 만듦
	// 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
	// 회원가입을 진행시킬 건데
	// username = google_attributes의 sub코드
	// password = 암호화(null이 아닌 값 아무거나 통일)
	// email = attributes의 email코드
	// role = ROLE_USER
	// provider = google
	// providerId = attributes의 sub코드
	// 함수 종료시 @AuthenticationPrincipal 어노테이션이 만들어진다.
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인 했는지 확인 가능.
		System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		// 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code를 리턴(OAuth2-Client 라이브러리가 받음) -> AccessToken 요청
		// userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필 받아준다.
		System.out.println("getAttributes : " + oAuth2User.getAttributes());
		
		// 회원가입을 강제로 진행할 예정
		OAuth2UserInfo oAuth2UserInfo = null;
		if (userRequest.getClientRegistration().getRegistrationId().equals("google")) {
			System.out.println("구글 로그인 요청");
			oAuth2UserInfo = new GoogleUserInfo(oAuth2User.getAttributes());
			
		} else if (userRequest.getClientRegistration().getRegistrationId().equals("naver")) {
			System.out.println("네이버 로그인 요청");
			oAuth2UserInfo = new NaverUserInfo((Map<String, Object>) oAuth2User.getAttributes().get("response"));
		} else {
			System.out.println("우리는 구글과 네이버만 지원합니다.");
		}
		
		String provider = oAuth2UserInfo.getProvider(); // google
		String providerId = oAuth2UserInfo.getProviderId();
		String username = provider + "_" + providerId;
		String password = bCryptPasswordEncoder.encode("whodatt1"); // 의미 없음
		String email = oAuth2UserInfo.getEmail();
		String role = "ROLE_USER";
		
		// 회원가입 여부 확인
		User userEntity = userRepository.findByUsername(username);
		
		if (userEntity == null) {
			System.out.println("OAuth 로그인이 최초입니다.");
			userEntity = User.builder()
							 .username(username)
							 .password(password)
							 .email(email)
							 .role(role)
							 .provider(provider)
							 .providerId(providerId)
							 .build();
			
			userRepository.save(userEntity);
		} else {
			System.out.println("로그인을 이미 한적이 있습니다. 당신은 자동회원가입이 되어 있습니다.");
		}
		
		// Authentication 객체 안에 들어감
		return new PrincipalDetails(userEntity, oAuth2User.getAttributes());
	}
}
