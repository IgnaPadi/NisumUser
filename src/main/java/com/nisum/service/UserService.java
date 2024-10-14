package com.nisum.service;

import com.nisum.common.EncodeDecode;
import com.nisum.common.TokenGenerator;
import com.nisum.entity.User;
import com.nisum.repository.UserRepository;
import com.nisum.response.ResponseApi;
import com.nisum.response.ResponseApiMessage;
import com.nisum.response.ResponseApiMessageEnum;
import com.nisum.validation.UserValidation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserService {
    @Autowired UserRepository userRepository;

    /**
     * Se validan los valores de entrada y se crea registro de usuario
     * @param user
     * @return ResponseEntity<Object>
     */
    public ResponseEntity<Object> register(User user) {
        try {
            List<ResponseApiMessage> responseApiMessageList = new ArrayList<>();

            //validación de formato
            if(Optional.ofNullable(user.getEmail()).isEmpty() || Optional.ofNullable(user.getPassword()).isEmpty()){

                responseApiMessageList.add(new ResponseApiMessage(new Timestamp(System.currentTimeMillis()),
                        ResponseApiMessageEnum.ERROR_NOT_NULL.getCode(), ResponseApiMessageEnum.ERROR_NOT_NULL.getMessage()));

                return new ResponseEntity<>(new ResponseApi(responseApiMessageList), HttpStatus.BAD_REQUEST);
            }

            //validación de registro unico
            if(userRepository.findByEmail(user.getEmail()).isPresent()){

                responseApiMessageList.add(new ResponseApiMessage(new Timestamp(System.currentTimeMillis()),
                        ResponseApiMessageEnum.ERROR_USER_EXISTS.getCode(), ResponseApiMessageEnum.ERROR_USER_EXISTS.getMessage()));

                return new ResponseEntity<>(new ResponseApi(responseApiMessageList), HttpStatus.BAD_REQUEST);
            }

            //validación de negocio
            List<ResponseApiMessage> responseApiBusinessMessageList = UserValidation.businessvalidation(user);
            if(!responseApiBusinessMessageList.isEmpty()){
                return new ResponseEntity<>(new ResponseApi(responseApiBusinessMessageList), HttpStatus.BAD_REQUEST);
            }

            //Se crea y asigna token
            String token = TokenGenerator.getToken(user.getEmail());
            user.setToken(token);
            //Se usa encode Base64 para contraseña
            user.setPassword(EncodeDecode.passwordEncode(user.getPassword()));
            //se asigna usuario a entidad phone
            user.getPhones().forEach(i-> i.setUser(user));

            User userResponse = userRepository.save(user);
            userResponse.setPassword(EncodeDecode.passwordDecode(userResponse.getPassword())); //se decodifica password para retornar la contraseña en respuesta

            return ResponseEntity.ok(userResponse);

        }catch (Exception e){
            log.error("Error en register");
            e.printStackTrace();
            List<ResponseApiMessage> responseApiMessageList = new ArrayList<>();
            responseApiMessageList.add(new ResponseApiMessage(new Timestamp(System.currentTimeMillis()),
                    HttpStatus.BAD_REQUEST.value(), ResponseApiMessageEnum.ERROR.getMessage()));

            return new ResponseEntity<>(new ResponseApi(responseApiMessageList), HttpStatus.BAD_REQUEST);


        }

    }

}
