package com.example.security1.config.oauth2;

import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

@Service
public class PrincipalOauth2UserService extends DefaultOAuth2UserService {
	
	// 구글로부터 받은 userRequest 데이터에 대한 후처리되는 함수
	// 회원가입을 진행시킬 건데
	// username = google_attributes의 sub코드
	// password = 암호화(null이 아닌 값 아무거나 통일)
	// email = attributes의 email코드
	// role = ROLE_USER
	// provider = google
	// providerId = attributes의 sub코드
	
	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		System.out.println("getClientRegistration : " + userRequest.getClientRegistration()); // registrationId로 어떤 OAuth로 로그인 했는지 확인 가능.
		System.out.println("getAccessToken : " + userRequest.getAccessToken().getTokenValue());
		// 구글 로그인 버튼 클릭 -> 구글 로그인 창 -> 로그인 완료 -> code를 리턴(OAuth2-Client 라이브러리가 받음) -> AccessToken 요청
		// userRequest 정보 -> loadUser함수 호출 -> 구글로부터 회원 프로필 받아준다.
		System.out.println("getAttributes : " + super.loadUser(userRequest).getAttributes());
		
		OAuth2User oAuth2User = super.loadUser(userRequest);
		// 회원가입을 강제로 진행할 예정
		return super.loadUser(userRequest);
	}
}
