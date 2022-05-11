package rub.demo.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Bin {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(mappedBy = "bin", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<Product> product;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;


    public Bin(User user) {
        this.user = user;
    }
}
