package umc.beanstalk.service.Member;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.enums.MissionStatus;
import umc.beanstalk.domain.mapping.MemberMission;
import umc.beanstalk.repository.MemberMissionRepository;
import umc.beanstalk.repository.MemberRepository;
import umc.beanstalk.repository.MissionRepository;
import umc.beanstalk.repository.ReviewRepository;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberQueryServiceImpl implements MemberQueryService {

    private final MemberRepository memberRepository;

    private final ReviewRepository reviewRepository;

    private final MissionRepository missionRepository;

    private final MemberMissionRepository memberMissionRepository;

    @Override
    public Optional<Member> findMember(Long id) {
        return memberRepository.findById(id);
    }

    @Override
    public Page<Review> getReviewList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId).get();

        Page<Review> MemberPage = reviewRepository.findAllByMember(member, PageRequest.of(page, 10));
        return MemberPage;
    }

    @Override
    public Page<MemberMission> getMemberMissionList(Long memberId, Integer page) {

        Member member = memberRepository.findById(memberId).get();

        Page<MemberMission> MissionPage = memberMissionRepository.findAllByMemberAndStatus(
                member, MissionStatus.CHALLENGING, PageRequest.of(page, 10));

        return MissionPage;
    }

}
