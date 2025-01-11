package ephemint.com.ddosProtector.ephemint.repository;

import ephemint.com.ddosProtector.domain.NftMint;

public interface NftRepository {

    public Long save(NftMint nftMint);
}
