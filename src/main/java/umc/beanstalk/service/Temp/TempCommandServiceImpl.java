package umc.beanstalk.service.Temp;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.beanstalk.apiPayload.code.status.ErrorStatus;
import umc.beanstalk.apiPayload.exception.handler.TempHandler;


@Service
@RequiredArgsConstructor
public class TempCommandServiceImpl implements TempCommandService {
    @Override
    public void CheckFlag(Integer flag) {
        if (flag == 1)
            throw new TempHandler(ErrorStatus.TEMP_EXCEPTION);
    }
}