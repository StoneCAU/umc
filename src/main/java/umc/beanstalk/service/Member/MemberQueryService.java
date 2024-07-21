package umc.beanstalk.service.Member;

import org.springframework.data.domain.Page;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.mapping.MemberMission;

import java.util.Optional;

public interface MemberQueryService {
    Optional<Member> findMember(Long id);

    Page<Review> getReviewList(Long memberId, Integer page);

    Page<MemberMission> getMemberMissionList(Long memberMissionId, Integer page);

}
