package prv.jarkchen.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class CorsConfig implements WebMvcConfigurer {

	private final String headerString = "*";
	private final Long maxAgeLong = 3600L;
	
	private CorsConfiguration buildConfig() {
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.setAllowCredentials(true);	// 允许 接受 cookie 
		corsConfiguration.setMaxAge(maxAgeLong);
		corsConfiguration.addAllowedOriginPattern("http://localhost:8080");		// 允许域名 http://localhost:8080
		corsConfiguration.addAllowedOriginPattern("http://localhost:8082");		// 允许域名 http://localhost:8082
		corsConfiguration.addAllowedHeader(headerString);		// 允许 任何头
		corsConfiguration.addAllowedMethod("POST");		// 允许方法 post
		corsConfiguration.addAllowedMethod("GET");
		
		return corsConfiguration;
	}
	
	@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", buildConfig());			// 4
		
		return new CorsFilter(source);
	}
}
