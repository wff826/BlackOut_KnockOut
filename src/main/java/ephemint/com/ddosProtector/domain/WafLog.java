package ephemint.com.ddosProtector.domain;

import ephemint.com.ddosProtector.company.dto.WafLogDto;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.Instant;

@Entity
@Getter
@Table(name = "waf_log")
public class WafLog {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ip;

    private String attackType;

    private Instant timeStamp;

    private String details;

    @OneToOne
    @JoinColumn(name = "nft_mint_id")
    private NftMint nftMint;

    // Protected constructor for JPA
    protected WafLog() {}

    // Private constructor for internal use
    private WafLog(String ip, String attackType, Instant timeStamp, String details) {
        this.ip = ip;
        this.attackType = attackType;
        this.timeStamp = timeStamp;
        this.details = details;
    }

    // Static factory method for creating a WafLog from a DTO
    public static WafLog createWafLog(WafLogDto wafLogDto) {
        return new WafLog(
                wafLogDto.getIp(),
                wafLogDto.getAttackType(),
                convertToInstant(wafLogDto.getTimeStamp()),
                wafLogDto.getDetails()
        );
    }

    // Method to convert a timestamp string to an Instant
    public static Instant convertToInstant(String timeStamp) {
        try {
            return Instant.parse(timeStamp);
        } catch (Exception e) {
            throw new IllegalArgumentException("Invalid timestamp format: " + timeStamp, e);
        }
    }

    // Convenience method to associate an NftMint
    public void assignNftMint(NftMint nftMint) {
        this.nftMint = nftMint;
        nftMint.setWafLog(this);
    }
}
