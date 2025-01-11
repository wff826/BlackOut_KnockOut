package RIOT.ddosProtector;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

@RestController
@RequestMapping("/api/proxy")
public class ProxyController {

    /**
     * Proxy IP 변경 요청 처리 - GET 요청
     * @return 새로운 Proxy IP
     */
    @GetMapping("/change")
    public ResponseEntity<String> changeProxyGet() {
        String newProxyIp = generateRandomIp();
        return ResponseEntity.ok(newProxyIp);
    }

    /**
     * Proxy IP 변경 요청 처리 - POST 요청
     * @return 새로운 Proxy IP
     */
    @PostMapping("/change")
    public ResponseEntity<String> changeProxyPost() {
        String newProxyIp = generateRandomIp();
        return ResponseEntity.ok(newProxyIp);
    }

    /**
     * 임의의 Proxy IP 주소 생성
     * @return 생성된 IP 주소 (형식: xxx.xxx.xxx.xxx)
     */
    private String generateRandomIp() {
        Random random = new Random();
        return String.format(
                "%d.%d.%d.%d",
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256),
                random.nextInt(256)
        );
    }
}
