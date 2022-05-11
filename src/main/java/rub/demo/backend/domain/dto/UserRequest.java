package rub.demo.backend.domain.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserRequest {

    String name;
    String age;
    String email;
    String password;
}
