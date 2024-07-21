package umc.beanstalk.service.MemberMission;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.beanstalk.apiPayload.code.status.ErrorStatus;
import umc.beanstalk.apiPayload.exception.handler.MemberHandler;
import umc.beanstalk.apiPayload.exception.handler.MissionHandler;
import umc.beanstalk.converter.MemberMissionConverter;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.mapping.MemberMission;
import umc.beanstalk.repository.MemberMissionRepository;
import umc.beanstalk.repository.MemberRepository;
import umc.beanstalk.repository.MissionRepository;
import umc.beanstalk.web.dto.MemberMission.MemberMissionRequestDTO;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionCommandServiceImpl implements MemberMissionCommandService {

    private final MemberMissionRepository memberMissionRepository;

    private final MissionRepository missionRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public MemberMission joinMemberMission(MemberMissionRequestDTO.MemberMissionJoinDto request) {

        Member newMember = memberRepository.findById(request.getMemberId()).orElseThrow(
                () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        Mission newMission = missionRepository.findById(request.getMissionId()).orElseThrow(
                () -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND)
        );

        MemberMission newMemberMission = MemberMissionConverter.toMemberMission(request, newMember, newMission);

        return memberMissionRepository.save(newMemberMission);

    }
}
