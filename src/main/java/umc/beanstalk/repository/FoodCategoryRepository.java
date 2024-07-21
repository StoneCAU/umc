package umc.beanstalk.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import umc.beanstalk.domain.FoodCategory;

public interface FoodCategoryRepository extends JpaRepository<FoodCategory, Long> {
}