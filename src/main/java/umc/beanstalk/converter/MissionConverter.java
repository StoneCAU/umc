package umc.beanstalk.converter;


import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.Store;
import umc.beanstalk.web.dto.Mission.MissionRequestDTO;
import umc.beanstalk.web.dto.Mission.MissionResponseDTO;

import java.time.LocalDateTime;

public class MissionConverter {

    public static MissionResponseDTO.MissionJoinResultDTO toMissionJoinResultDTO(Mission mission) {
        return MissionResponseDTO.MissionJoinResultDTO.builder()
                .missionId(mission.getId())
                .createdAt(LocalDateTime.now())
                .build();
    }

    public static Mission toMission(MissionRequestDTO.MissionJoinDto request, Store store) {
        return Mission.builder()
                .missionSpec(request.getMissionSpec())
                .reward(request.getReward())
                .deadline(request.getDeadline())
                .store(store)
                .build();

    }

}
