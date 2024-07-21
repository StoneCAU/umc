package umc.beanstalk.service.Store;



import org.springframework.data.domain.Page;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;

import java.util.Optional;

public interface StoreQueryService {

    Optional<Store> findStore(Long id);

    Page<Review> getReviewList(Long storeId, Integer page);

    Page<Mission> getMissionList(Long storeId, Integer page);
}
