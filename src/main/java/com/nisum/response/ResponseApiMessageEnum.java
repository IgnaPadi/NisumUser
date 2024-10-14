package com.nisum.response;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * Clase Enum donde se asignan los mensajes de error con sus codigos
 */
@AllArgsConstructor
@NoArgsConstructor
public enum ResponseApiMessageEnum {

    ERROR_GENERIC("Error al crear registro", 1),
    ERROR("Error interno, comuniquese con equipo de soporte", 2),
    ERROR_NOT_NULL("Email y/o password son valores obligatorios", 3),
    ERROR_EMAIL("Email %1$s no cumple con el formato (ejemplo aaaaaaa@dominio.cl)", 4),
    ERROR_PASSWORD("Password debe contener letras minúsculas, una letra mayúscula y dos números. El largo permitido es entre 8 y 12", 5),
    ERROR_USER_EXISTS("El correo ya registrado", 6);

    private String message;
    private int code;

    public String getMessage() {
        return message;
    }
    public int getCode() {
        return code;
    }
}