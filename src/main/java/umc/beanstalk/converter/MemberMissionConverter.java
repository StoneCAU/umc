package umc.beanstalk.converter;


import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.mapping.MemberMission;
import umc.beanstalk.web.dto.MemberMission.MemberMissionRequestDTO;
import umc.beanstalk.web.dto.MemberMission.MemberMissionResponseDTO;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class MemberMissionConverter {

    public static MemberMissionResponseDTO.MemberMissionJoinResultDTO toJoinResultDTO(MemberMission memberMission){
        return MemberMissionResponseDTO.MemberMissionJoinResultDTO.builder()
                .memberMissionId(memberMission.getId())
                .createdAt(LocalDateTime.now())
                .status(memberMission.getStatus())
                .build();
    }

    public static MemberMission toMemberMission(MemberMissionRequestDTO.MemberMissionJoinDto request, Member member, Mission mission){

        return MemberMission.builder()
                .member(member)
                .mission(mission)
                .build();
    }
}