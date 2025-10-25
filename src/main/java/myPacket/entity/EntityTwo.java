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

    @OneToOne//(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "symbol_id")
    private EntityOne entityOne;

    @Column(name = "num_1")
    private int num1;
    @Column(name = "num_2")
    private int num2;
    private String result;

    public EntityTwo(int num1, int num2, String result, EntityOne entityOne) {
        this.num2 = num2;
        this.num1 = num1;
        this.result = result;
        this.entityOne = entityOne;
    }

    public void updateEntityTwo(String result, int num1, int num2) {
        this.result = result;
        this.num1 = num1;
        this.num2 = num2;
    }
}
