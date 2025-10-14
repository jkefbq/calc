package myPacket.request;

import jakarta.persistence.*;
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
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String symbol;
    private String result;

    public Request(String symbol, String result) {
        this.symbol = symbol;
        this.result = result;
    }
}
