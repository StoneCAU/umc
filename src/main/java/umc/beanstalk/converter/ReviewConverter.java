package umc.beanstalk.converter;


import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;
import umc.beanstalk.domain.mapping.ReviewImage;
import umc.beanstalk.web.dto.Review.ReviewRequestDTO;
import umc.beanstalk.web.dto.Review.ReviewResponseDTO;
import umc.beanstalk.web.dto.Store.StoreRequestDTO;

import java.time.LocalDateTime;

public class ReviewConverter {

    public static ReviewResponseDTO.ReviewJoinResultDTO toJoinResultDTO(Review review) {
        return ReviewResponseDTO.ReviewJoinResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Review toReview(ReviewRequestDTO.ReviewJoinDto request, Member member, Store store) {
        return Review.builder()
                .body(request.getBody())
                .score(request.getScore())
                .member(member)
                .store(store)
                .build();

    }

    public static ReviewImage toReviewImage(String pictureUrl, Review review) {
        return ReviewImage.builder()
                .pictureUrl(pictureUrl)
                .review(review)
                .build();
    }
}
