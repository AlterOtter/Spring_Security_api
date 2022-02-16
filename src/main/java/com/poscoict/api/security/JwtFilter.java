package com.poscoict.api.security;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.poscoict.api.entity.UserEntity;
import com.poscoict.api.service.FilterService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


// extends GenericFilterBean
@Component
public class JwtFilter extends OncePerRequestFilter{
    
    @Autowired
    private TokenProvider tokenProvider;
    
    @Autowired
    private FilterService filterService;

    //#region
    // @Override
    // public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
    //         throws IOException, ServletException {
    //         HttpServletResponse res = (HttpServletResponse)response;
    //         final HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    //         String authorization=httpServletRequest.getHeader("Authorization");
    //         String access_token = replaceTokenBearer(authorization);
    //         System.out.println(access_token);
        

    //         //사용자 토큰 인증
    //         boolean validated=tokenProvider.validate(access_token);
            
    //         //인증 성공시 사용자 정보 추출
    //         if(validated){
    //             String user_sn = tokenProvider.GetTokenBody(access_token);
    //             httpServletRequest.setAttribute("mem_sn", user_sn);
    //             log.info(user_sn);
    //             chain.doFilter(request, response);
    //         }else{
    //             res.setStatus(403);
    //             PrintWriter out =res.getWriter();
    //             out.write("{error:1}");
    //             return;
    //         }
    // }
    //#endregion
    public String replaceTokenBearer(String authorization) throws ServletException{
        if(authorization==null || authorization.equalsIgnoreCase("null") || authorization.isEmpty()){
            //throw new ServletException("can't find token");
            return null;
        }

        String access_token = authorization.replaceAll("Bearer ","");
        return access_token;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
            String authorization=request.getHeader("Authorization");
            String access_token = replaceTokenBearer(authorization);
            System.out.println(access_token);
            if(access_token !=null && !access_token.equalsIgnoreCase("null")){
                
                //사용자 토큰 인증
                boolean validated=tokenProvider.validate(access_token);
                //인증 성공시 사용자 정보 추출
                if(validated){
                    String user_sn = tokenProvider.GetTokenBody(access_token);


                    UserEntity user=(UserEntity)filterService.loadUserByUsername(user_sn);

                    AbstractAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user, null,user.getAuthorities());

                    authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    
                    // 1. Create Empty SecurityContext
                    SecurityContext securityContext = SecurityContextHolder.createEmptyContext();

                    // 2. Authentication 된 토큰을 SecurityContext에 저장
                    securityContext.setAuthentication(authenticationToken);

                    // 3. SecurityContextHolder에 SecurityContext 저장 
                    // @AuthenticationPrincipal로 토큰에 저장한값 가져올 수 있게함
                    // filter를 통가 할 수 있게 권한 인증

                    SecurityContextHolder.setContext(securityContext);
                }

            }

            filterChain.doFilter(request, response);
    }
}
