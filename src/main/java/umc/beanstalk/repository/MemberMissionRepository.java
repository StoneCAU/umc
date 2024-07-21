package umc.beanstalk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.enums.MissionStatus;
import umc.beanstalk.domain.mapping.MemberMission;


public interface MemberMissionRepository extends JpaRepository<MemberMission, Long> {
    MemberMission findByMemberAndMission(Member member, Mission mission);
    boolean existsByMissionAndMember(Mission mission, Member member);
    Page<MemberMission> findAllByMemberAndStatus(Member member, MissionStatus missionStatus, PageRequest pageRequest);

}