package umc.beanstalk.service.MemberMission;

import org.springframework.data.domain.Page;


public interface MemberMissionQueryService {
    boolean checkMemberMission(Long memberId, Long missionId);
}
