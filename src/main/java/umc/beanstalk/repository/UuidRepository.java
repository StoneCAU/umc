package umc.beanstalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.beanstalk.domain.Uuid;

public interface UuidRepository extends JpaRepository<Uuid, Long> {
}
