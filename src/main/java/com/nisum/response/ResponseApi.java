package com.nisum.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseApi {

    @JsonProperty("mensaje")
    List<ResponseApiMessage> responseApiMessageList = new ArrayList<>();

}
