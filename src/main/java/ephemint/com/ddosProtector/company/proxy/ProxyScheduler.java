package ephemint.com.ddosProtector.company.proxy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.NoSuchElementException;

@Component
public class ProxyScheduler {

    private final ProxyService proxyService;
    private Instant lastExecutionTime;

    @Autowired
    public ProxyScheduler(ProxyService proxyService) {
        this.proxyService = proxyService;
        this.lastExecutionTime = Instant.now(); // 초기화
    }

    /**
     * 5분마다 Proxy 변경 요청 후 새로운 ip 주소 반환
     */
    @Scheduled(fixedRate = 300000) // 5분 = 300,000ms
    public void scheduleProxyChange() {
        lastExecutionTime = Instant.now(); // 마지막 실행 시간 갱신
        String newProxyIp = proxyService.requestProxyChange();
        if (newProxyIp == null) {
            throw new NoSuchElementException("NO NEW PROXY IP ADDRESS");
        }


    }

    /**
     * 남은 대기 시간 계산
     * @return 남은 대기 시간 (밀리초)
     */
    public long getTimeUntilNextExecution() {
        long elapsedTime = Instant.now().toEpochMilli() - lastExecutionTime.toEpochMilli();
        long interval = 300000; // 5분
        return Math.max(0, interval - elapsedTime);
    }
}
