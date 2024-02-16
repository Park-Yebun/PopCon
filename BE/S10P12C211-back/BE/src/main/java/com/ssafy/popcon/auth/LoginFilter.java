package com.ssafy.popcon.auth;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ssafy.popcon.user.dto.UserDto;
import com.ssafy.popcon.util.JWTUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.FilterChain;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Collection;
import java.util.Iterator;


@RequiredArgsConstructor
public class LoginFilter extends UsernamePasswordAuthenticationFilter {

    private final AuthenticationManager authenticationManager;
    private final JWTUtil jwtUtil;

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException  {
        ObjectMapper om=new ObjectMapper();

        // 클라이언트 요청에서 userId, userPassword 추출
        UserDto dto= null;

        try {
            dto = om.readValue(request.getInputStream(), UserDto.class);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        // 스프링 시큐리티에서 userid과 password를 검증하기 위해서는 token에 담아야 함
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(dto.getUserId(), dto.getUserPassword(), null);
        // token에 담은 검증을 위한 AuthenticationManager로 전달되어 검증됨
        return authenticationManager.authenticate(authToken);
    }

    // 로그인 성공시 실행하는 메소드 (여기서 JWT를 발급)
    @Override
    protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain, Authentication authentication) throws IOException {
        System.out.println("success");

        CustomUserDetails customUserDetails= (CustomUserDetails) authentication.getPrincipal();
        String userId= customUserDetails.getUsername();

        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        Iterator<? extends GrantedAuthority> iterator = authorities.iterator();
        GrantedAuthority auth = iterator.next();

        String userType=auth.getAuthority();

        String token= jwtUtil.createJwt(userId,userType,60*60*1000L*24*7);   // 7 days

        response.addHeader("Authorization","Bearer " + token);
        // 문자열의 인코딩을 UTF-8로 설정
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/plain; charset=UTF-8");

        response.getWriter().write("로그인이 완료되었습니다.");
        response.getWriter().flush();
    }

    // 로그인 실패시 실행하는 메소드
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException {
        System.out.println("fail");

        // 문자열의 인코딩을 UTF-8로 설정
        response.setCharacterEncoding(StandardCharsets.UTF_8.name());
        response.setContentType("text/plain; charset=UTF-8");

        response.setStatus(401);
        response.getWriter().write("아이디 혹은 패스워드를 다시 확인하세요.");
        response.getWriter().flush();
    }
}
