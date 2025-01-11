package ephemint.com.ddosProtector.company.repository;

import ephemint.com.ddosProtector.domain.ServerIp;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class ServerIpMySqlRepository implements ServerIpRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long saveProxyIpOfServer(String serverIp, String proxyIp) {
        // 1. 서버 IP로 기존 데이터 조회
        Optional<ServerIp> existingServerIp = findServerIpByServerIp(serverIp);

        if (existingServerIp.isPresent()) {
            // 2. 존재하면 Proxy IP를 업데이트
            ServerIp sIp = existingServerIp.get();
            sIp.setCurrentProxyIp(proxyIp);
            return sIp.getId();
        } else {
            // 3. 존재하지 않으면 새로 생성 후 저장
            ServerIp newServerIp = new ServerIp(serverIp, proxyIp);
            em.persist(newServerIp);
            return newServerIp.getId();
        }
    }

    @Override
    public String findCurrentProxyIpbyServerIp(String original) {
        return findServerIpByServerIp(original)
                .map(ServerIp::getCurrentProxyIp)
                .orElse(null);
    }

    // 공통 메서드로 분리: ServerIp 조회
    private Optional<ServerIp> findServerIpByServerIp(String serverIp) {
        try {
            return Optional.of(em.createQuery("SELECT s FROM ServerIp s WHERE s.serverIp = :serverIp", ServerIp.class)
                    .setParameter("serverIp", serverIp)
                    .getSingleResult());
        } catch (jakarta.persistence.NoResultException e) {
            return Optional.empty();
        }
    }
}
