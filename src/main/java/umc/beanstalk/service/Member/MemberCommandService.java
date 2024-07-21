package umc.beanstalk.service.Member;


import umc.beanstalk.domain.Member;
import umc.beanstalk.web.dto.Member.MemberRequestDTO;

public interface MemberCommandService {
    Member joinMember(MemberRequestDTO.JoinDto request);
}
