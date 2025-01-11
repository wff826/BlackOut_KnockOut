package ephemint.com.ddosProtector.domain;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.Instant;

@Entity
@Getter
@Table(name = "nft_mint")
public class NftMint {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "nft_mint_id")
    private Long id;

    private Long tokenId;
//    private String transactionHash;
    private String metadata;
    private Instant mintTime;


    @Setter
    @OneToOne(mappedBy = "nftMint")
    private WafLog wafLog;


    //생성자
    protected NftMint(){}

    public NftMint(Long tokenId, String metadata, Instant mintTime) {
        this.tokenId = tokenId;
        this.metadata = metadata;
        this.mintTime = mintTime;
    }



    //    public NftMint(String transactionHash, String metadata, Long tokenId, LocalDateTime mintTime, WafLog wafLog) {
//        this.transactionHash = transactionHash;
//        this.metadata = metadata;
//        this.tokenId = tokenId;
//        this.mintTime = mintTime;
//        this.wafLog = wafLog;
//    }

}
