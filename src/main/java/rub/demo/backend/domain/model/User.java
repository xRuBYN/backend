package rub.demo.backend.domain.model;


import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.context.annotation.Role;

import javax.persistence.*;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "t_user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "name")
    private String name;

    @NotNull
    @Column(name = "email")
    private String email;


    @NotNull
    @Column(name = "password")
    private String password;


    @Column(name = "age")
    private String age;

    @NotNull
    @Column(name = "role_id")

    private String role;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    Bin bin;
}
