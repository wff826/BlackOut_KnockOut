package ephemint.com.ddosProtector.company.proxy;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Service
public class ProxyService {

    private static final Logger logger = LoggerFactory.getLogger(ProxyService.class);

    private final RestTemplate restTemplate;
    private final String serverUrl;

    public ProxyService(
            @Value("${external.server.url}") String serverUrl
    ) {
        this.restTemplate = new RestTemplate();
        this.serverUrl = serverUrl;

    }

    /**
     * 외부 서버에 Proxy IP 변경 요청
     */
    public String requestProxyChange() {
        try {
            // HTTP 헤더 설정
            HttpHeaders headers = new HttpHeaders();

            // 요청 생성
            HttpEntity<Void> requestEntity = new HttpEntity<>(headers);

            // GET 요청 전송 및 응답 받기
            ResponseEntity<String> response = restTemplate.exchange(
                    serverUrl,
                    HttpMethod.GET,
                    requestEntity,
                    String.class
            );

            if (response.getStatusCode() == HttpStatus.OK) {
                String newProxyIp = response.getBody(); // 응답 Body에서 새 Proxy IP 가져오기
                logger.info("Successfully changed proxy IP: {}", newProxyIp);
                return newProxyIp;
            } else {
                logger.error("Failed to change proxy IP. Status: {}", response.getStatusCode());
                return null;
            }

        } catch (Exception e) {
            logger.error("Error occurred while changing proxy IP: {}", e.getMessage(), e);
            return null;
        }
    }
}
