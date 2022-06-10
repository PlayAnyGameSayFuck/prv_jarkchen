package prv.jarkchen.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import lombok.Data;

@Configuration
@PropertySource("classpath:jdbc.properties")
@ConfigurationProperties(prefix= "spring.datasource")
@Data
public class SQLDataSource {

	private String url;
	private String DriverName;
	private String username;
	private String password;
}
