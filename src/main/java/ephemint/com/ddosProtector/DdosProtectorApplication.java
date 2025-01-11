package ephemint.com.ddosProtector;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.client.ClientHttpRequestInterceptor;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@SpringBootApplication
@EnableScheduling
public class DdosProtectorApplication {

	public static void main(String[] args) {

		SpringApplication.run(DdosProtectorApplication.class, args);
	}

//	@Bean
//	public RestTemplate restTemplate() {
//		// 필요시 커스터마이징 로직 추가
//		return new RestTemplate();
//	}


	@Value("${nodit.api.key}") // application.properties에서 API Key 로드
	private String noditApiKey;

	@Bean
	public RestTemplate restTemplate() {
		RestTemplate restTemplate = new RestTemplate();

		// 요청 헤더에 API Key 추가
		ClientHttpRequestInterceptor interceptor = (request, body, execution) -> {
			request.getHeaders().add("Authorization", "Bearer " + noditApiKey);
			return execution.execute(request, body);
		};

		restTemplate.setInterceptors(Collections.singletonList(interceptor));
		return restTemplate;
	}

}
