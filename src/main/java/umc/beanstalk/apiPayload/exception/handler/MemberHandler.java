package umc.beanstalk.apiPayload.exception.handler;

import umc.beanstalk.apiPayload.code.BaseErrorCode;
import umc.beanstalk.apiPayload.exception.GeneralException;

public class MemberHandler extends GeneralException {
    public MemberHandler(BaseErrorCode errorCode) {super(errorCode);}
}
