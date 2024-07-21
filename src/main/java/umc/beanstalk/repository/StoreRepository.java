package umc.beanstalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.beanstalk.domain.Store;

public interface StoreRepository extends JpaRepository<Store, Long> {
}
