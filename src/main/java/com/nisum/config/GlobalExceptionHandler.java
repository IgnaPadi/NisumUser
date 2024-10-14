package com.nisum.config;

import com.nisum.response.ResponseApi;
import com.nisum.response.ResponseApiMessage;
import com.nisum.response.ResponseApiMessageEnum;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * Se interceptan errores no controlados y se retorna error en formato solicitado
     * @param ex
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ResponseApi> keyNotFound(HttpMessageNotReadableException ex) {
        List<ResponseApiMessage> responseApiMessageList = new ArrayList<>();

        responseApiMessageList.add(new ResponseApiMessage(new Timestamp(System.currentTimeMillis()),
                ResponseApiMessageEnum.ERROR_GENERIC.getCode(), ex.getMessage()));

        log.info(ex.getMessage());
        return new ResponseEntity<>(new ResponseApi(responseApiMessageList), HttpStatus.BAD_REQUEST);

    }
}
