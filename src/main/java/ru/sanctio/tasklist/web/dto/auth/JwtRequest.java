package ru.sanctio.tasklist.web.dto.auth;

import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class JwtRequest {

    @NotNull(message = "Username must be not null.")
    private String username;

    @NotNull(message = "Password must be not null.")
    private String password;

}
