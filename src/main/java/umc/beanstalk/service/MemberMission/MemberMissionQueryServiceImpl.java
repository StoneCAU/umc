package umc.beanstalk.service.MemberMission;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.beanstalk.apiPayload.code.status.ErrorStatus;
import umc.beanstalk.apiPayload.exception.handler.MemberHandler;
import umc.beanstalk.apiPayload.exception.handler.MissionHandler;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.repository.MemberMissionRepository;
import umc.beanstalk.repository.MemberRepository;
import umc.beanstalk.repository.MissionRepository;


@Service
@RequiredArgsConstructor
@Transactional
public class MemberMissionQueryServiceImpl implements MemberMissionQueryService {

    private final MemberRepository memberRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public boolean checkMemberMission(Long memberId, Long missionId) {
        Member newMember = memberRepository.findById(memberId).orElseThrow(
                () -> new MemberHandler(ErrorStatus.MEMBER_NOT_FOUND)
        );

        Mission newMission = missionRepository.findById(missionId).orElseThrow(
                () -> new MissionHandler(ErrorStatus.MISSION_NOT_FOUND)
        );

        return memberMissionRepository.existsByMissionAndMember(newMission, newMember);
    }

}
