package db.project.ownpliv2.config;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@RequiredArgsConstructor
@EnableWebSecurity
public class SecurityConfig {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .csrf(csrf -> csrf.disable())
                .headers(headers -> headers.frameOptions(options -> options.disable()))
                .authorizeHttpRequests((authz) -> authz
                        .requestMatchers(
                                new AntPathRequestMatcher("/"),
                                new AntPathRequestMatcher("/css/**"),
                                new AntPathRequestMatcher("/images"),
                                new AntPathRequestMatcher("/js/**"),
                                new AntPathRequestMatcher("/h2-console/**")
                        ).permitAll()
                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                        .anyRequest().authenticated()
                )
                .logout(lo -> lo.logoutSuccessUrl("\n"))
                .oauth2Login(o2l -> o2l
                        .userInfoEndpoint(uiend -> uiend
                                .userService(customOAuth2UserService)));

        return http.build();
    }

}
