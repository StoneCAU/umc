package umc.beanstalk.apiPayload.exception.handler;


import umc.beanstalk.apiPayload.code.BaseErrorCode;
import umc.beanstalk.apiPayload.exception.GeneralException;

public class FoodCategoryHandler extends GeneralException {
    public FoodCategoryHandler(BaseErrorCode errorCode) {super(errorCode);}
}
