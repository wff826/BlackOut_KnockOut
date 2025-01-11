package ephemint.com.ddosProtector.ephemint.repository;

import ephemint.com.ddosProtector.domain.NftMint;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;

@Repository
public class NftMysqlRepository implements NftRepository{

    @PersistenceContext
    private EntityManager em;

    @Override
    public Long save(NftMint nftMint) {
        em.persist(nftMint);
        return nftMint.getId();
    }
}
