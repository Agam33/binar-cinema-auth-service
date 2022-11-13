package com.ra.bioskop.authservice.dto.response;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ValidateTokenResponse {
    private String token;
    private String authority;
}
