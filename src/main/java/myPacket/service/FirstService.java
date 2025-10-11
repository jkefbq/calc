package myPacket.service;

import lombok.Getter;
import lombok.Setter;
import myPacket.FirstRepository;
import myPacket.dto.CalculationRequestDTO;
import myPacket.dto.CalculationResponseDTO;
import myPacket.request.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Getter
@Setter
@Service
public class FirstService {

    private final FirstRepository repo1;
    private final FirstService service1;
    private final Request request1;
    private final CalculationResponseDTO response;

    @Autowired
    public FirstService(FirstRepository repo1, FirstService service1,
                           Request request1, CalculationResponseDTO response) {
        this.repo1 = repo1;
        this.service1 = service1;
        this.request1 = request1;
        this.response = response;
    }

    public String calculateResult(CalculationRequestDTO request) {
        String symbol = request.getSymbol();
        int a = request.getA();
        int b = request.getB();
        String result;

        result = switch (symbol) {
            case "-" -> Integer.toString(a - b);
            case "+" -> Integer.toString(a + b);
            case "/" -> Integer.toString(a / b);
            case "*" -> Integer.toString(a * b);
            default -> "error";
        };
        return result;
    }
}
