package umc.beanstalk.service.Store;


import org.springframework.web.multipart.MultipartFile;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;
import umc.beanstalk.web.dto.Store.StoreRequestDTO;

public interface StoreCommandService {
    Store joinStore(StoreRequestDTO.StoreJoinDto request);
    Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request, MultipartFile photo);
}
