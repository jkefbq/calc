package myPacket.service;

import lombok.Getter;
import lombok.Setter;
import myPacket.dto.CalculationRequestDTO;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class FirstService {

    public String calculateResult(CalculationRequestDTO request) {
        int a = request.getA();
        int b = request.getB();

        return switch (request.getSymbol()) {
            case "-" -> Integer.toString(a - b);
            case "+" -> Integer.toString(a + b);
            case "/" -> Integer.toString(a / b);
            case "*" -> Integer.toString(a * b);
            default -> "error";
        };
    }
}
