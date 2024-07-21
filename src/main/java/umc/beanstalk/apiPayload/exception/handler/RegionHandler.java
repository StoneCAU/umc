package umc.beanstalk.apiPayload.exception.handler;

import umc.beanstalk.apiPayload.code.BaseErrorCode;
import umc.beanstalk.apiPayload.exception.GeneralException;

public class RegionHandler extends GeneralException {
    public RegionHandler(BaseErrorCode errorcode) {
        super(errorcode);
    }
}
