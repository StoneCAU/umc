package umc.beanstalk.service.Store;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.beanstalk.apiPayload.code.status.ErrorStatus;
import umc.beanstalk.apiPayload.exception.handler.RegionHandler;
import umc.beanstalk.aws.s3.AmazonS3Manager;
import umc.beanstalk.converter.ReviewConverter;
import umc.beanstalk.converter.StoreConverter;
import umc.beanstalk.domain.Region;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;
import umc.beanstalk.domain.Uuid;
import umc.beanstalk.repository.*;
import umc.beanstalk.web.dto.Store.StoreRequestDTO;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Transactional
public class StoreCommandServiceImpl implements StoreCommandService {

    private final StoreRepository storeRepository;

    private final RegionRepository regionRepository;

    private final ReviewRepository reviewRepository;

    private final MemberRepository memberRepository;

    private final AmazonS3Manager s3Manager;

    private final UuidRepository uuidRepository;

    private final ReviewImageRepository reviewImageRepository;


    @Override
    @Transactional
    public Store joinStore(StoreRequestDTO.StoreJoinDto request) {

        Region newRegion = regionRepository.findById(request.getRegionId()).orElseThrow(
                () -> new RegionHandler(ErrorStatus.REGION_NOT_FOUND)
        );

        Store newStore = StoreConverter.toStore(request, newRegion);

        return storeRepository.save(newStore);
    }

    @Override
    public Review createReview(Long memberId, Long storeId, StoreRequestDTO.ReviewDTO request) {

        Review review = StoreConverter.toReview(request);

        String uuid = UUID.randomUUID().toString();
        Uuid savedUuid = uuidRepository.save(Uuid.builder()
                .uuid(uuid).build());

        String pictureUrl = s3Manager.uploadFile(s3Manager.generateReviewKeyName(savedUuid), request.getReviewPicture());

        review.setMember(memberRepository.findById(memberId).get());
        review.setStore(storeRepository.findById(storeId).get());

        reviewImageRepository.save(ReviewConverter.toReviewImage(pictureUrl, review));
        return reviewRepository.save(review);
    }

}
