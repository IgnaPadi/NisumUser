package com.nisum.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.sql.Timestamp;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApiMessage {

    Timestamp timestamp;
    int codigo;
    String detail;

}
