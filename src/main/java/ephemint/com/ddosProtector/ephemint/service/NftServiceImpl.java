package ephemint.com.ddosProtector.ephemint.service;

import ephemint.com.ddosProtector.domain.NftMint;
import ephemint.com.ddosProtector.ephemint.repository.NftRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;

import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class NftServiceImpl implements NftService{

    private final NftRepository nftRepository;

    @Override
    public NftMint mintNft(String ip) {
        //nft 생성
        NftMint newNft = new NftMint(1L, generateMetadata(ip), Instant.now());


        //nft 저장
        nftRepository.save(newNft);

        return newNft;
    }

    // NFT 민팅을 위한 메타데이터 생성
    public static String generateMetadata(String ipAddress) {
        return String.format("{ \"ip\": \"%s\", \"timestamp\": \"%s\" }",
                ipAddress, Instant.now().toString());

    }



}
