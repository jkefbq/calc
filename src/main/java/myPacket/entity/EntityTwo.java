package myPacket.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "two")
@Getter
@Setter
@NoArgsConstructor
public class EntityTwo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String symbol_id;
    private String result;
    private int num_1;
    private int num_2;

    public EntityTwo(int num_2, int num_1, String result, String symbol_id) {
        this.num_2 = num_2;
        this.num_1 = num_1;
        this.result = result;
        this.symbol_id = symbol_id;
    }
}
