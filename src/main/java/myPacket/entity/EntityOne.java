package myPacket.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
@Entity
@Table(name = "one")
@Getter
@Setter
@NoArgsConstructor
public class EntityOne {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String symbol;

    public EntityOne(String symbol) {
        this.symbol = symbol;
    }
}
