package umc.beanstalk.converter;



import org.springframework.data.domain.Page;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.Region;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;
import umc.beanstalk.web.dto.Store.StoreRequestDTO;
import umc.beanstalk.web.dto.Store.StoreResponseDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class StoreConverter {

    public static StoreResponseDTO.StoreJoinResultDTO toJoinResultDTO(Store store) {
        return StoreResponseDTO.StoreJoinResultDTO.builder()
                .storeId(store.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Store toStore(StoreRequestDTO.StoreJoinDto request, Region region) {
        return Store.builder()
                .name(request.getName())
                .address(request.getAddress())
                .region(region)
                .build();
    }

    public static Review toReview(StoreRequestDTO.ReviewDTO request) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static StoreResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review) {
        return StoreResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(LocalDate.now())
                .build();
    }

    public static StoreResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review) {
        return StoreResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static StoreResponseDTO.ReviewPreviewListDTO reviewPreviewListDTO(Page<Review> reviewList) {
        List<StoreResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(StoreConverter::reviewPreviewDTO).collect(Collectors.toList());

        return StoreResponseDTO.ReviewPreviewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }

    public static Mission toMission(StoreRequestDTO.MissionDTO request) {
        return Mission.builder()
                .reward(request.getReward())
                .missionSpec(request.getMissionSpec())
                .deadline(request.getDeadline())
                .build();
    }

    public static StoreResponseDTO.CreateMissionResultDTO toCreateMissionResultDTO(Mission mission) {
        return StoreResponseDTO.CreateMissionResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDate.now())
                .build();
    }

    public static StoreResponseDTO.MissionDTO missionDTO(Mission mission) {
        return StoreResponseDTO.MissionDTO.builder()
                .storeName(mission.getStore().getName())
                .missionSpec(mission.getMissionSpec())
                .createdAt(mission.getCreatedAt().toLocalDate())
                .reward(mission.getReward())
                .deadline(mission.getDeadline())
                .build();
    }

    public static StoreResponseDTO.MissionListDTO missionListDTO(Page<Mission> missionList) {
        List<StoreResponseDTO.MissionDTO> missionDTOList = missionList.stream()
                .map(StoreConverter::missionDTO).collect(Collectors.toList());

        return StoreResponseDTO.MissionListDTO.builder()
                .isLast(missionList.isLast())
                .isFirst(missionList.isFirst())
                .totalPage(missionList.getTotalPages())
                .totalElements(missionList.getTotalElements())
                .listSize(missionDTOList.size())
                .missionList(missionDTOList)
                .build();
    }

}
