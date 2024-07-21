package umc.beanstalk.web.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.beanstalk.apiPayload.ApiResponse;
import umc.beanstalk.converter.MissionConverter;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.service.Mission.MissionCommandService;
import umc.beanstalk.web.dto.Mission.MissionRequestDTO;
import umc.beanstalk.web.dto.Mission.MissionResponseDTO;


@RestController
@RequiredArgsConstructor
@RequestMapping("/missions")
public class MissionRestController {

    private final MissionCommandService missionCommandService;

    @PostMapping("/")
    public ApiResponse<MissionResponseDTO.MissionJoinResultDTO> join(@RequestBody @Valid MissionRequestDTO.MissionJoinDto request) {
        Mission mission = missionCommandService.joinMission(request);
        return ApiResponse.onSuccess(MissionConverter.toMissionJoinResultDTO(mission));
    }


}
