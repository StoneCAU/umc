package umc.beanstalk.service.Mission;


import umc.beanstalk.domain.Mission;
import umc.beanstalk.web.dto.Mission.MissionRequestDTO;

public interface MissionCommandService {
    Mission joinMission(MissionRequestDTO.MissionJoinDto request);
}
