package umc.beanstalk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.beanstalk.domain.Mission;
import umc.beanstalk.domain.Store;
import umc.beanstalk.domain.mapping.ReviewImage;


public interface ReviewImageRepository extends JpaRepository<ReviewImage, Long> {
}
