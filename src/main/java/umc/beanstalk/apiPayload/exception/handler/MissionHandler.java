package umc.beanstalk.apiPayload.exception.handler;


import umc.beanstalk.apiPayload.code.BaseErrorCode;
import umc.beanstalk.apiPayload.exception.GeneralException;

public class MissionHandler extends GeneralException {
    public MissionHandler(BaseErrorCode errorCode) {super(errorCode);}
}
