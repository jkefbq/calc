package myPacket.service;
import org.springframework.stereotype.Service;

@Service
public class CalculateService {

    public String calculateResult(String sym, int a, int b) {
        if (!filterNumbers(a, b, sym)) {
            return "error";
        }
        return switch (sym) {
            case "-" -> Integer.toString(a - b);
            case "+" -> Integer.toString(a + b);
            case "/" -> Integer.toString(a / b);
            case "*" -> Integer.toString(a * b);
            default -> "error";
        };
    }

    public boolean filterNumbers(int a, int b, String sym) {
        return sym.equals("*") && (a >= 0 && b >= 0) && Integer.MAX_VALUE / b >= a || sym.equals("*") && (a <= 0 && b >= 0) && Integer.MIN_VALUE / b <= a ||
                sym.equals("*") && (a >= 0 && b <= 0) && Integer.MIN_VALUE / b >= a || sym.equals("*") && (a <= 0 && b <= 0) && (a != Integer.MIN_VALUE && b != Integer.MIN_VALUE) && Integer.MIN_VALUE / b >= Math.abs(a) ||
                sym.equals("+") && (a >= 0 && b >= 0) && Integer.MAX_VALUE - b >= a || sym.equals("+") && (a <= 0 && b >= 0) && Integer.MAX_VALUE > b ||
                sym.equals("+") && (a <= 0 && b <= 0) && Integer.MAX_VALUE - Math.abs(b) >= Math.abs(a) || sym.equals("+") && (a >= 0 && b <= 0) && Integer.MAX_VALUE > a ||
                sym.equals("-") && (a <= 0 && b >= 0) && Integer.MIN_VALUE + b <= a || sym.equals("-") && (a >= 0 && b <= 0) && Integer.MAX_VALUE - a > Math.abs(b) ||
                sym.equals("-") && (a >= 0 && b >= 0) || sym.equals("-") && (a <= 0 && b <= 0) || sym.equals("/");
    }
}