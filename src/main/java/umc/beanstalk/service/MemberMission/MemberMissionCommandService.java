package umc.beanstalk.service.MemberMission;


import umc.beanstalk.domain.mapping.MemberMission;
import umc.beanstalk.web.dto.MemberMission.MemberMissionRequestDTO;

public interface MemberMissionCommandService {
    MemberMission joinMemberMission(MemberMissionRequestDTO.MemberMissionJoinDto request);
}
