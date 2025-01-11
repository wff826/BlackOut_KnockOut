package ephemint.com.ddosProtector.company.service; // 기존 경로 확인 후 필요시 수정
import ephemint.com.ddosProtector.company.dto.DeploymentRequest;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import java.util.Map;

@Service
public class SmartContractService {

    private final RestTemplate restTemplate = new RestTemplate();

    // Nodit API URL 정의
    private static final String DEPLOY_URL = "https://developer.nodit.io/api/contracts";
    private static final String EXECUTE_URL = "https://developer.nodit.io/api/transactions/execute";

    // 스마트 컨트랙트 배포
    public String deployContract(DeploymentRequest request) {
        // Nodit API 호출 (필요한 경우 DTO 매핑 검토)
        return restTemplate.postForObject(DEPLOY_URL, request, String.class);
    }

    // 트랜잭션 실행
    public String executeTransaction(String transactionId) {
        // Nodit API 호출 (transactionId 입력값 검증 필요)
        return restTemplate.postForObject(EXECUTE_URL,
                Map.of("transactionId", transactionId), // 필요시 Map 형식 변경
                String.class);
    }
}
