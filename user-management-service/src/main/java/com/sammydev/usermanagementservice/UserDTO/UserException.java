package com.sammydev.usermanagementservice.UserDTO;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@SuppressWarnings("unused")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserException {
    private String responseMessage;
    private int responseCode;
}
