package com.nisum.common;

import org.apache.tomcat.util.codec.binary.Base64;

public class EncodeDecode {
    /**
     * Se usa Base64 para encriptar valores con un hash. Para mayor seguridad se debería usar una función de cifrado que permita usar salt
     * @param valueToEncode
     * @return
     */
    public static String passwordEncode(String valueToEncode){
        byte[] bytesEncoded = Base64.encodeBase64(valueToEncode.getBytes());
        return new String(bytesEncoded);
    }

    /**
     * Se decodifica un valor
     * @param valueToDecode
     * @return
     */
    public static String passwordDecode(String valueToDecode){
        byte[] valueDecoded = Base64.decodeBase64(valueToDecode);
        return new String(valueDecoded);
    }
}
