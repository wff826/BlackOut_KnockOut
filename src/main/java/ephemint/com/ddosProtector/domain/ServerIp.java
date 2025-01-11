package ephemint.com.ddosProtector.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "server_ip")
@Getter
public class ServerIp {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String serverIp;

    @Setter
    private String currentProxyIp;

    protected ServerIp() {
    }

    public ServerIp(String serverIp, String currentProxyIp) {
        this.serverIp = serverIp;
        this.currentProxyIp = currentProxyIp;
    }
}
