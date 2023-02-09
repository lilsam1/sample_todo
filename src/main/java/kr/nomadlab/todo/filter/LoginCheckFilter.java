package kr.nomadlab.todo.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kr.nomadlab.todo.dto.MemberDTO;
import kr.nomadlab.todo.service.MemberService;
import lombok.extern.log4j.Log4j2;


@WebFilter(urlPatterns = {"/todo/*"})
@Log4j2
public class LoginCheckFilter implements Filter {
       

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		log.info("Login check filter...");
		
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		
		HttpSession session = req.getSession();
		
		if (session.getAttribute("loginInfo") != null) {
			chain.doFilter(req, resp);
			return;
		}
		
		// session에 loginInfo 값이 없다면 쿠키를 체크
		Cookie cookie = findCookie(req.getCookies(), "remember-me");
		
		// 세션에도 없고 쿠키도 없다면 그냥 로그인으로
		if (cookie == null) {
			resp.sendRedirect("/login");
			return;
		}
		
		// 쿠키가 존재하는 상황이라면
		log.info("cookie는 존재하는 상황");
		
		// uuid 값
		String uuid = cookie.getValue();
		
		try {
			// 데이터베이스 확인
			MemberDTO memberDTO = MemberService.INSTANCE.getByUUID(uuid);
			
			log.info("쿠키의 값으로 조회한 사용자 정보 : " + memberDTO);
			if (memberDTO == null) {
				throw new Exception("Cookie value is not valid");
			}
			// 회원 정보를 세션에 추가
			session.setAttribute("loginInfo", memberDTO);
			chain.doFilter(request, response);
		} catch(Exception e) {
			e.printStackTrace();
			resp.sendRedirect("/login");
		}
		
		// pass the request along the filter chain
	}
	
	private Cookie findCookie(Cookie[] cookies, String cookieName) {
		Cookie targetCookie = null;
		
		if (cookies != null && cookies.length > 0) {
			for (Cookie cookie: cookies) {
				if (cookie.getName().equals(cookieName)) {
					targetCookie = cookie;
					break;
				}
			}
		}
		
		if (targetCookie == null) {
			targetCookie = new Cookie(cookieName, "");
			targetCookie.setPath("/");
			targetCookie.setMaxAge(60 * 60 * 24);
		}
		
		return targetCookie;
	}

}
