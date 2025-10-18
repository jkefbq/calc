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

    @Column(name = "symbol_id")
    private String symbolId;
    private String result;
    @Column(name = "num_1")
    private int num1;
    @Column(name = "num_2")
    private int num2;

    public EntityTwo(int num1, int num2, String result, String symbol_id) {
        this.num2 = num2;
        this.num1 = num1;
        this.result = result;
        this.symbolId = symbol_id;
    }
}
