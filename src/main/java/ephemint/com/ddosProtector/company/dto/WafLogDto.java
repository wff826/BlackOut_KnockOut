package ephemint.com.ddosProtector.company.dto;

import ephemint.com.ddosProtector.domain.NftMint;
import lombok.Getter;
import lombok.Setter;
@Getter
@Setter
public class WafLogDto {
    private String ip;

    private String attackType;

    private String timeStamp;

    private String details;


}
