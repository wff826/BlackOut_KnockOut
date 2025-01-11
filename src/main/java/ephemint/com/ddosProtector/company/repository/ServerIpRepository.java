package ephemint.com.ddosProtector.company.repository;

public interface ServerIpRepository {
    public String findCurrentProxyIpbyServerIp(String original);

    public Long saveProxyIpOfServer(String serverIp, String proxyIp);
}
