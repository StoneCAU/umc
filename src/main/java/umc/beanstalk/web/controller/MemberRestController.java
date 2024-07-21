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
import umc.beanstalk.converter.MemberConverter;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.mapping.MemberMission;
import umc.beanstalk.service.Member.MemberCommandService;
import umc.beanstalk.service.Member.MemberQueryService;
import umc.beanstalk.validation.annotation.CheckPage;
import umc.beanstalk.validation.annotation.ExistMembers;
import umc.beanstalk.web.dto.Member.MemberRequestDTO;
import umc.beanstalk.web.dto.Member.MemberResponseDTO;


import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/members")
public class MemberRestController {

    private final MemberCommandService memberCommandService;
    private final MemberQueryService memberQueryService;

    @PostMapping("/")
    public ApiResponse<MemberResponseDTO.JoinResultDTO> join(@RequestBody @Valid MemberRequestDTO.JoinDto request){
        Member member = memberCommandService.joinMember(request);
        return ApiResponse.onSuccess(MemberConverter.toJoinResultDTO(member));
    }

    @GetMapping("/{memberId}/reviews")
    @Operation(summary = "특정 멤버의 리뷰 목록 조회 API",description = "특정 멤버의 리뷰들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
    })
    public ApiResponse<MemberResponseDTO.ReviewPreViewListDTO> getReviewList(@ExistMembers @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page){
        Page<Review> reviewList = memberQueryService.getReviewList(memberId, page - 1);
        return ApiResponse.onSuccess(MemberConverter.reviewPreViewListDTO(reviewList));
    }

    @GetMapping("/{memberId}/challenging-missions")
    @Operation(summary = "특정 멤버의 도전중인 미션 목록 조회 API",description = "특정 멤버의 도전중인 미션들의 목록을 조회하는 API이며, 페이징을 포함합니다. query String 으로 page 번호를 주세요")
    @ApiResponses({
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "COMMON200",description = "OK, 성공"),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH003", description = "access 토큰을 주세요!",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH004", description = "access 토큰 만료",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "AUTH006", description = "access 토큰 모양이 이상함",content = @Content(schema = @Schema(implementation = ApiResponse.class))),
    })
    @Parameters({
            @Parameter(name = "memberId", description = "멤버의 아이디, path variable 입니다!"),
    })
    public ApiResponse<MemberResponseDTO.MemberMissionListDTO> getMemberMissionList(@ExistMembers @PathVariable(name = "memberId") Long memberId, @CheckPage @RequestParam(name = "page") Integer page){
        Page<MemberMission> memberMissionList = memberQueryService.getMemberMissionList(memberId, page-1);
        return ApiResponse.onSuccess(MemberConverter.memberMissionListDTO(memberMissionList));
    }
}