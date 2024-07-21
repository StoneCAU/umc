package umc.beanstalk.web.dto.Store;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;
import umc.beanstalk.validation.annotation.ExistRegions;

import java.time.LocalDate;

public class StoreRequestDTO {

    @Getter
    public static class StoreJoinDto {
        String name;
        String address;
        @ExistRegions
        Long regionId;
    }

    @Getter
    public static class ReviewDTO {
        @NotBlank
        String title;
        @NotNull
        Float score;
        @NotBlank
        String body;
    }

    @Getter
    public static class MissionDTO {
        Integer reward;
        LocalDate deadline;
        String missionSpec;
    }
}
