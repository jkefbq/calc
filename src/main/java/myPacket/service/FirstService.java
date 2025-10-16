package myPacket.service;

import myPacket.dto.RequestDTO;
import org.springframework.stereotype.Service;

@Service
public class FirstService {

    public String calculateResult(RequestDTO request) {
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
