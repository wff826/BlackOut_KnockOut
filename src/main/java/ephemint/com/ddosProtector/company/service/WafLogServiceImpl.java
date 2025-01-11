package ephemint.com.ddosProtector.company.service;


import ephemint.com.ddosProtector.company.dto.WafLogDto;
import ephemint.com.ddosProtector.company.repository.ServerIpRepository;
import ephemint.com.ddosProtector.company.repository.WafRepository;
import ephemint.com.ddosProtector.domain.WafLog;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class WafLogServiceImpl implements WafLogService {

    private final WafRepository wafRepository;
    private final ServerIpRepository serverIpRepository;

    //조회(ip로 WafLog)
    public WafLog findByIP(String ip){
        return wafRepository.getWafLogByIP(ip);
    }

    //조회(serverIp로 현재 proxyIp)
    public String findProxyIpOfServer(String serverIp){
        return serverIpRepository.findCurrentProxyIpbyServerIp(serverIp);
    }

    //저장
    @Transactional
    public String save(WafLogDto wafLogDto){
        WafLog wafLog = WafLog.createWafLog(wafLogDto);
        return wafRepository.saveWafLog(wafLog);

    }

    @Transactional
    public Long createServerIp(String org, String proxy){
        return serverIpRepository.saveProxyIpOfServer(org, proxy);
    }


}
