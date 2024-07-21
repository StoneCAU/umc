package umc.beanstalk.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import umc.beanstalk.domain.Member;

public interface MemberRepository extends JpaRepository<Member, Long> {
}