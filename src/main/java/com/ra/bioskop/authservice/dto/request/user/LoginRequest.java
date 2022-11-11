package com.ra.bioskop.authservice.dto.request.user;


import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import com.ra.bioskop.authservice.util.Constants;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
public class LoginRequest {
    @NotNull
    @Pattern(regexp = Constants.EMAIL_PATTERN)
    private String email;
    @NotNull
    private String password;
}
