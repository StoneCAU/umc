package umc.beanstalk.service.Review;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.beanstalk.apiPayload.code.status.ErrorStatus;
import umc.beanstalk.apiPayload.exception.handler.MemberHandler;
import umc.beanstalk.apiPayload.exception.handler.StoreHandler;
import umc.beanstalk.aws.s3.AmazonS3Manager;
import umc.beanstalk.converter.ReviewConverter;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;
import umc.beanstalk.repository.MemberRepository;
import umc.beanstalk.repository.ReviewRepository;
import umc.beanstalk.repository.StoreRepository;
import umc.beanstalk.repository.UuidRepository;
import umc.beanstalk.web.dto.Review.ReviewRequestDTO;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewCommandServiceImpl implements ReviewCommandService {

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final StoreRepository storeRepository;

    private final UuidRepository uuidRepository;

    private final AmazonS3Manager s3Manager;

    @Override
    @Transactional
    public Review joinReview(ReviewRequestDTO.ReviewJoinDto request) {

        Member newMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        Store newStore = storeRepository.findById(request.getStoreId()).orElseThrow(
                () -> new StoreHandler(ErrorStatus.STORE_NOT_FOUND)
        );

        Review newReview = ReviewConverter.toReview(request, newMember, newStore);


        List<Review> reviews = reviewRepository.findByStore(newStore);
        float sum = newReview.getScore();
        for(Review review : reviews) {
            sum += review.getScore();
        }
        float avgScore = sum / (reviews.size() + 1);

        newStore.setScore(avgScore);

        return reviewRepository.save(newReview);
    }
}
