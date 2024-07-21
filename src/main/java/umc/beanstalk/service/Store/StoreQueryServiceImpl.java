package umc.beanstalk.service.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;
import umc.beanstalk.repository.MissionRepository;
import umc.beanstalk.repository.ReviewRepository;
import umc.beanstalk.repository.StoreRepository;


import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StoreQueryServiceImpl implements StoreQueryService {

    private final StoreRepository storeRepository;

    private final ReviewRepository reviewRepository;

    private final MissionRepository missionRepository;

    @Override
    public Optional<Store> findStore(Long id) {
        return storeRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Review> StorePage = reviewRepository.findAllByStore(store, PageRequest.of(page, 10));
        return StorePage;
    }

    @Override
    public Page<Mission> getMissionList(Long storeId, Integer page) {

        Store store = storeRepository.findById(storeId).get();

        Page<Mission> MissionPage = missionRepository.findAllByStore(store, PageRequest.of(page, 10));
        return MissionPage;
    }

}
