package ephemint.com.ddosProtector.company.repository;

import ephemint.com.ddosProtector.domain.WafLog;

public interface WafRepository {

    //조회
    public WafLog getWafLogByIP(String ip);

    //저장
    public String saveWafLog(WafLog wafLog);
}
