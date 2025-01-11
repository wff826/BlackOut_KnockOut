package ephemint.com.ddosProtector.company.service;

import ephemint.com.ddosProtector.company.dto.WafLogDto;
import ephemint.com.ddosProtector.domain.WafLog;

public interface WafLogService {
    public WafLog findByIP(String ip);
    public String save(WafLogDto wafLogDto);
    public Long createServerIp(String org, String proxy);
    public String findProxyIpOfServer(String serverIp);
}
