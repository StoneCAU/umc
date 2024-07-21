package umc.beanstalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.beanstalk.domain.Region;

public interface RegionRepository extends JpaRepository<Region, Long> {
}
