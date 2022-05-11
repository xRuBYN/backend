package rub.demo.backend.domain.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class RegisterRequest {
    @NotNull
    private String name;

    @NotNull
    private String email;

    @NotNull
    private String password;

}
