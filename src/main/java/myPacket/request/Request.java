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
@NoArgsConstructor
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Setter
    private String symbol;
    @Setter
    private String result;

    public Request(String symbol, String result) {
        this.symbol = symbol;
        this.result = result;
    }

    @Override
    public String toString() {
        return "Request{" +
                "id=" + id +
                ", symbol='" + symbol + '\'' +
                ", result='" + result + '\'' +
                '}';
    }
}
