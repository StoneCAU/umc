package umc.beanstalk.domain.mapping;

import jakarta.persistence.*;
import lombok.*;
import umc.beanstalk.domain.Review;
import umc.beanstalk.domain.Store;
import umc.beanstalk.domain.common.BaseEntity;

@Entity
@Builder
@Getter
@AllArgsConstructor(access = AccessLevel.PROTECTED)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReviewImage extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String pictureUrl;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "review_id")
    private Review review;
}
