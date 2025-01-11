package ephemint.com.ddosProtector.company.repository;

import ephemint.com.ddosProtector.domain.WafLog;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class WafLogMySqlRepository implements WafRepository {

    @PersistenceContext
    private EntityManager em;


    @Override
    public WafLog getWafLogByIP(String ip) {
        List<WafLog> resultList =
                em.createQuery("select w from WafLog w where w.ip = :ip", WafLog.class)
                        .setParameter("ip", ip)
                        .getResultList();

        return resultList.isEmpty() ? null : resultList.getFirst();
    }

    @Override
    public String saveWafLog(WafLog wafLog) {
        em.persist(wafLog);
        return wafLog.getIp();
    }
}
