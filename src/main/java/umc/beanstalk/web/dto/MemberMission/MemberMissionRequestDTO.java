package umc.beanstalk.web.dto.MemberMission;

import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import umc.beanstalk.validation.annotation.isChallenging;


public class MemberMissionRequestDTO {

    @Getter
    @isChallenging
    public static class MemberMissionJoinDto{
        Long memberId;
        Long missionId;
    }

}