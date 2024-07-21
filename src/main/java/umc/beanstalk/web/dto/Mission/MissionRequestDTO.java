package umc.beanstalk.web.dto.Mission;

import lombok.Getter;
import umc.beanstalk.validation.annotation.ExistStores;

import java.time.LocalDate;

public class MissionRequestDTO {

    @Getter
    public static class MissionJoinDto {
        Integer reward;
        LocalDate deadline;
        String missionSpec;
        @ExistStores
        Long storeId;
    }
}
