package com.nisum.validation;

import com.nisum.entity.User;
import com.nisum.response.ResponseApiMessage;
import com.nisum
        .response.ResponseApiMessageEnum;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserValidation {

    /**
     * Se hacen validaciones de negocio de la entidad User
     * @param user
     * @return List<ResponseApiMessage>
     */
    public static List<ResponseApiMessage> businessvalidation(User user) {
        List<ResponseApiMessage> responseApiMessageList = new ArrayList<>();

        if(!RegularExpressionValidator.isEmail(user.getEmail())){
            responseApiMessageList.add(new ResponseApiMessage(new Timestamp(System.currentTimeMillis()),
                    ResponseApiMessageEnum.ERROR_EMAIL.getCode(), String.format(ResponseApiMessageEnum.ERROR_EMAIL.getMessage(), user.getEmail())));
        }
        if(!RegularExpressionValidator.isPassword(user.getPassword())){
            responseApiMessageList.add(new ResponseApiMessage(new Timestamp(System.currentTimeMillis()),
                    ResponseApiMessageEnum.ERROR_PASSWORD.getCode(), ResponseApiMessageEnum.ERROR_PASSWORD.getMessage()));
        }
        return responseApiMessageList;

    }
}
