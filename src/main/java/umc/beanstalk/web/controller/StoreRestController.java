package umc.beanstalk.web.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.beanstalk.apiPayload.ApiResponse;
import umc.beanstalk.converter.ReviewConverter;
import umc.beanstalk.converter.StoreConverter;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;
import umc.beanstalk.service.Store.StoreCommandService;
import umc.beanstalk.service.Store.StoreQueryService;
import umc.beanstalk.validation.annotation.CheckPage;
import umc.beanstalk.validation.annotation.ExistMembers;
import umc.beanstalk.validation.annotation.ExistRegions;
import umc.beanstalk.validation.annotation.ExistStores;
import umc.beanstalk.web.dto.Store.StoreRequestDTO;
import umc.beanstalk.web.dto.Store.StoreResponseDTO;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/stores")
public class StoreRestController {

    private final StoreCommandService storeCommandService;

    private final StoreQueryService storeQueryService;

    @PostMapping("/")
    public ApiResponse<StoreResponseDTO.StoreJoinResultDTO> join(@RequestBody @Valid StoreRequestDTO.StoreJoinDto request) {
        Store store = storeCommandService.joinStore(request);
        return ApiResponse.onSuccess(StoreConverter.toJoinResultDTO(store));
    }

    @GetMapping("{storeId}/reviews")
    @Operation(summary = "특정 가게의 리뷰 목록 조회 API", description = "특정 가개의 리뷰들의 목록을 조회하는 API이며, query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
    })
    public ApiResponse<StoreResponseDTO.ReviewPreviewListDTO> getReviewList(@ExistRegions @PathVariable(name = "storeId") Long storeId, @CheckPage @RequestParam(name = "page") Integer page) {
        Page<Review> reviewList= storeQueryService.getReviewList(storeId, page-1);
        return ApiResponse.onSuccess(StoreConverter.reviewPreviewListDTO(reviewList));
    }

    @GetMapping("{storeId}/missions")
    @Operation(summary = "특정 가게의 미션 목록 조회 API", description = "특정 가개의 미션들의 목록을 조회하는 API이며, query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
    })
    public ApiResponse<StoreResponseDTO.MissionListDTO> getMissionList(@ExistRegions @PathVariable(name = "storeId") Long storeId, @RequestParam(name = "page") Integer page) {
        Page<Mission> missionList= storeQueryService.getMissionList(storeId, page-1);
        return ApiResponse.onSuccess(StoreConverter.missionListDTO(missionList));
    }

    @PostMapping(value = "{storeId}/reviews", consumes = "multipart/form-data")
    @Operation(summary = "특정 가게의 리뷰 작성 API", description = "특정 가게의 리뷰를 작성합니다.")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200", description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함", content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "storeId", description = "가게의 아이디, path variable 입니다!"),
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
    })
    public ApiResponse<StoreResponseDTO.CreateReviewResultDTO> createReview(
            @ExistStores @PathVariable(name = "storeId") Long storeId,
            @ExistMembers @PathVariable(name = "memberId") Long memberId,
            StoreRequestDTO.ReviewDTO reviewDTO
            ) {
        Review review = storeCommandService.createReview(memberId, storeId, reviewDTO);
        return ApiResponse.onSuccess(StoreConverter.toCreateReviewResultDTO(review));

    }
}
