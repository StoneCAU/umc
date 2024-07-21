package umc.beanstalk.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import umc.beanstalk.domain.Member;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;

import java.util.List;

public interface ReviewRepository extends JpaRepository<Review, Long> {
    List<Review> findByStore(Store store);

    Page<Review> findAllByStore(Store store, PageRequest pageRequest);

    Page<Review> findAllByMember(Member member, PageRequest pageRequest);

}
