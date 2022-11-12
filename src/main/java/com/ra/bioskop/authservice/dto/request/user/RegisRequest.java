package com.ra.bioskop.authservice.dto.request.user;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.ra.bioskop.authservice.util.Constants;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RegisRequest {
    @NotNull
    @Pattern(regexp = Constants.EMAIL_PATTERN)
    // @Schema(example = "example@gmail.com", type = "string")
    private String email;

    @NotNull
    @Size(min = 5, max = 20)
    private String username;

    @NotNull
    @Size(min = 7, max = 20)
    private String password;

    @NotNull
    @Schema(example = "user", type = "string")
    private String roleName;
}
