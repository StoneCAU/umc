package umc.beanstalk.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.beanstalk.apiPayload.ApiResponse;
import umc.beanstalk.converter.ReviewConverter;
import umc.beanstalk.domain.Review;
import umc.beanstalk.service.Review.ReviewCommandService;
import umc.beanstalk.web.dto.Review.ReviewRequestDTO;
import umc.beanstalk.web.dto.Review.ReviewResponseDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/reviews")
public class ReviewRestController {

    private final ReviewCommandService reviewCommandService;

    @PostMapping("/")
    public ApiResponse<ReviewResponseDTO.ReviewJoinResultDTO> join(@RequestBody @Valid ReviewRequestDTO.ReviewJoinDto request) {
        Review review = reviewCommandService.joinReview(request);
        return ApiResponse.onSuccess(ReviewConverter.toJoinResultDTO(review));
    }


}
