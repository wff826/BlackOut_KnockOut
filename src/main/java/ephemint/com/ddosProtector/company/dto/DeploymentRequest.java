package ephemint.com.ddosProtector.company.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeploymentRequest {
    private String network;
    private String bytecode;
    private String[] constructorArgs;


}