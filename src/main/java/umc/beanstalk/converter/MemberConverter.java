package umc.beanstalk.converter;



import org.springframework.data.domain.Page;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.enums.Gender;
import umc.beanstalk.domain.mapping.MemberMission;
import umc.beanstalk.web.dto.Member.MemberRequestDTO;
import umc.beanstalk.web.dto.Member.MemberResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {

    public static MemberResponseDTO.JoinResultDTO toJoinResultDTO(Member member){
        return MemberResponseDTO.JoinResultDTO.builder()
                .memberId(member.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Member toMember(MemberRequestDTO.JoinDto request){

        Gender gender = null;

        switch (request.getGender()){
            case 1:
                gender = Gender.MALE;
                break;
            case 2:
                gender = Gender.FEMALE;
                break;
            case 3:
                gender = Gender.NONE;
                break;
        }

        return Member.builder()
                .address(request.getAddress())
                .specAddress(request.getSpecAddress())
                .gender(gender)
                .name(request.getName())
                .memberPreferList(new ArrayList<>())
                .build();
    }

    public static MemberMission toMemberMission(MemberRequestDTO.MemberMissionDTO request) {
        return MemberMission.builder()
                .status(request.getStatus())
                .build();
    }

    public static MemberResponseDTO.CreateMemberMissionResultDTO toCreateMemberMissionResultDTO(MemberMission memberMission){
        return MemberResponseDTO.CreateMemberMissionResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(memberMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.MemberMissionDTO memberMissionDTO(MemberMission memberMission){
        return MemberResponseDTO.MemberMissionDTO.builder()
                .status(memberMission.getStatus())
                .createdAt(memberMission.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.MemberMissionListDTO memberMissionListDTO(Page<MemberMission> memberMissionList) {

        List<MemberResponseDTO.MemberMissionDTO> memberMissionDTOList = memberMissionList.stream()
                .map(MemberConverter::memberMissionDTO).collect(Collectors.toList());

        return MemberResponseDTO.MemberMissionListDTO.builder()
                .isLast(memberMissionList.isLast())
                .isFirst(memberMissionList.isFirst())
                .totalPage(memberMissionList.getTotalPages())
                .totalElements(memberMissionList.getTotalElements())
                .listSize(memberMissionDTOList.size())
                .memberMissionList(memberMissionDTOList)
                .build();
    }

    public static Review toReview(MemberRequestDTO.ReviewDTO request) {
        return Review.builder()
                .title(request.getTitle())
                .score(request.getScore())
                .body(request.getBody())
                .build();
    }

    public static MemberResponseDTO.CreateReviewResultDTO toCreateReviewResultDTO(Review review){
        return MemberResponseDTO.CreateReviewResultDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt().toLocalDate())
                .build();
    }

    public static MemberResponseDTO.ReviewPreviewDTO reviewPreviewDTO(Review review){
        return MemberResponseDTO.ReviewPreviewDTO.builder()
                .ownerNickname(review.getMember().getName())
                .score(review.getScore())
                .createdAt(review.getCreatedAt().toLocalDate())
                .body(review.getBody())
                .build();
    }

    public static MemberResponseDTO.ReviewPreViewListDTO reviewPreViewListDTO(Page<Review> reviewList) {

        List<MemberResponseDTO.ReviewPreviewDTO> reviewPreviewDTOList = reviewList.stream()
                .map(MemberConverter::reviewPreviewDTO).collect(Collectors.toList());

        return MemberResponseDTO.ReviewPreViewListDTO.builder()
                .isLast(reviewList.isLast())
                .isFirst(reviewList.isFirst())
                .totalPage(reviewList.getTotalPages())
                .totalElements(reviewList.getTotalElements())
                .listSize(reviewPreviewDTOList.size())
                .reviewList(reviewPreviewDTOList)
                .build();
    }
}