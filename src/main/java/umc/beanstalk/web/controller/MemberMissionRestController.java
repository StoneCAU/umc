package umc.beanstalk.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.beanstalk.apiPayload.ApiResponse;
import umc.beanstalk.converter.MemberMissionConverter;
import umc.beanstalk.domain.mapping.MemberMission;
import umc.beanstalk.service.MemberMission.MemberMissionCommandService;
import umc.beanstalk.validation.annotation.isChallenging;
import umc.beanstalk.web.dto.MemberMission.MemberMissionRequestDTO;
import umc.beanstalk.web.dto.MemberMission.MemberMissionResponseDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping("/member-missions")
public class MemberMissionRestController {

    private final MemberMissionCommandService memberMissionCommandService;

    @PostMapping("/")
    public ApiResponse<MemberMissionResponseDTO.MemberMissionJoinResultDTO> join(@RequestBody @Valid @isChallenging MemberMissionRequestDTO.MemberMissionJoinDto request) {
        MemberMission memberMission = memberMissionCommandService.joinMemberMission(request);
        return ApiResponse.onSuccess(MemberMissionConverter.toJoinResultDTO(memberMission));
    }


}
