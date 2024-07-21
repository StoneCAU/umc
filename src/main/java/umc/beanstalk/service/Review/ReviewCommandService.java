package umc.beanstalk.service.Review;


import umc.beanstalk.domain.Review;
import umc.beanstalk.web.dto.Review.ReviewRequestDTO;

public interface ReviewCommandService {
    Review joinReview(ReviewRequestDTO.ReviewJoinDto request);
}
