package rub.demo.backend.domain.dto;

import com.sun.istack.NotNull;
import lombok.Builder;
import lombok.Data;
import org.springframework.stereotype.Component;

@Data
@Builder

public class AuthRequest {
    @NotNull
    String email;

    @NotNull
    String password;
}
