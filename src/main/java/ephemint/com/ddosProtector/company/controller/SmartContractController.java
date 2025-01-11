package ephemint.com.ddosProtector.company.controller; // 기존 경로 확인 후 필요시 수정

import ephemint.com.ddosProtector.company.dto.DeploymentRequest; // DTO 경로 확인
import ephemint.com.ddosProtector.company.service.SmartContractService; // Service 경로 확인
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity; // [변경점 추가: HTTP 응답 처리를 위해 ResponseEntity 추가]
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/contracts")
@RequiredArgsConstructor
public class SmartContractController {

    private final SmartContractService smartContractService;

    // 스마트 컨트랙트 배포 API
    @PostMapping("/deploy")
    public ResponseEntity<String> deployContract(@RequestBody DeploymentRequest request) {
        // [변경점: 반환 타입을 ResponseEntity로 변경하여 상태 코드와 응답 데이터를 함께 반환할 수 있도록 수정]

        // 배포 요청 처리 (필요시 입력값 검증 로직 추가)
        try {
            String contractAddress = smartContractService.deployContract(request);
            return ResponseEntity.ok(contractAddress);
            // [변경점: 성공적으로 배포된 스마트 컨트랙트 주소를 반환]
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error deploying contract: " + e.getMessage());
            // [변경점: 예외 발생 시 적절한 상태 코드와 오류 메시지를 반환]
        }
    }

    // 트랜잭션 실행 API
    @PostMapping("/execute")
    public ResponseEntity<String> executeTransaction(@RequestParam String transactionId) {
        // [변경점: 반환 타입을 ResponseEntity로 변경하여 상태 코드와 응답 데이터를 함께 반환할 수 있도록 수정]

        // 실행 요청 처리 (transactionId 검증 필요)
        try {
            String response = smartContractService.executeTransaction(transactionId);
            return ResponseEntity.ok(response);
            // [변경점: 성공적으로 실행된 트랜잭션 결과를 반환]
        } catch (Exception e) {
            return ResponseEntity.status(500).body("Error executing transaction: " + e.getMessage());
            // [변경점: 예외 발생 시 적절한 상태 코드와 오류 메시지를 반환]
        }
    }
}
