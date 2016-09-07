package com.senzo.qettal.checkout.security;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.senzo.qettal.checkout.users.User;
import com.senzo.qettal.checkout.users.Users;

@Component
public class AuthInterceptor extends HandlerInterceptorAdapter{

	@Autowired
	private LoggedUser loggedUser;
	@Autowired
	private Users users;
	@Value("${url.authUsers}")
	private String authUsersUrl;
	
	@Override
	public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
		try{
			if(handler instanceof HandlerMethod){
				AllowUnloggedUsers bypassAnnotation = ((HandlerMethod) handler).getMethodAnnotation(AllowUnloggedUsers.class);
				if(bypassAnnotation != null){
					return super.preHandle(req, res, handler);
				}
			}
			
			RestTemplate restTemplate = new RestTemplate();
			HttpHeaders headers = new HttpHeaders();
			String headerName = "Cookie";
			headers.set(headerName, req.getHeader(headerName));
			HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
			ResponseEntity<UserDTO> response = restTemplate.exchange(authUsersUrl, HttpMethod.GET, entity, UserDTO.class);
			UserDTO body = response.getBody();
			loggedUser.setUser(
					users.findByAuthId(body.getEmail(), body.getAuthId())
					.orElseGet(() -> users.save(new User(body.getName(), body.getEmail(), body.getAuthId())))
					);
			return super.preHandle(req, res, handler);
		} catch (HttpClientErrorException e){
			res.sendError(HttpServletResponse.SC_UNAUTHORIZED, "Unauthorized");
			return false;
		}
		
	}
	
}
