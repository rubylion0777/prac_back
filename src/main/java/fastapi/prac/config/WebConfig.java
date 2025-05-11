package fastapi.prac.config;// WebConfig.java

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig {

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**")  // 모든 경로에 대해 적용
                        .allowedOrigins("http://localhost:3000")  // 허용할 출처 지정
                        .allowedMethods("*")  // 허용할 HTTP 메서드
                        .allowedHeaders("*")
                        .allowCredentials(true);
            }
        };
    }
}