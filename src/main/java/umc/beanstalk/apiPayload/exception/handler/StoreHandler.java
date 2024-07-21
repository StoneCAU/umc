package umc.beanstalk.apiPayload.exception.handler;

import umc.beanstalk.apiPayload.code.BaseErrorCode;
import umc.beanstalk.apiPayload.exception.GeneralException;

public class StoreHandler extends GeneralException {
    public StoreHandler(BaseErrorCode errorCode) {super(errorCode);}
}
