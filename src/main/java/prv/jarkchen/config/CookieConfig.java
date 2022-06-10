package prv.jarkchen.config;
import org.apache.tomcat.util.http.LegacyCookieProcessor;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/*
 * cookie 版本 0 的遗留问题，导致 一些 cookie 域名无法设置： /login  a.localhost.com 等等
 * cookie 版本 0 的遗留问题 是 spring boot 嵌入式 tomcat 默认不支持, 必须配置 LegacyCookieProcessor
 * 
 * 	使用 这种格式设置， 是为了 子域名也能读到 cookie
 */

@Configuration
public class CookieConfig {

	@Bean
	public WebServerFactoryCustomizer<TomcatServletWebServerFactory> cookieProcessorCustomizer(){
		return (factory) ->factory.addContextCustomizers(
					(context) -> context.setCookieProcessor(new LegacyCookieProcessor())
				);
	}
}
