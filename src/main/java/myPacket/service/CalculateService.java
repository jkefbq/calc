package myPacket.service;

import jakarta.transaction.Transactional;
import lombok.Getter;
import myPacket.dto.RequestDTO;
import myPacket.entity.EntityOne;
import myPacket.entity.EntityTwo;
import myPacket.repos.EntityOneRepository;
import myPacket.repos.EntityTwoRepository;
import org.springframework.stereotype.Service;

@Getter
@Service
public class CalculateService {

    private final EntityOneRepository repo1;
    private final EntityTwoRepository repo2;
    private String res;
    private String sym;
    private int a;
    private int b;

    public CalculateService(EntityOneRepository repo1, EntityTwoRepository repo2) {
        this.repo1 = repo1;
        this.repo2 = repo2;
    }

    public String calculateResult(RequestDTO request) {
        a = request.getA();
        b = request.getB();
        sym = request.getSymbol();

        if (!filterNumbers(request)) {
            return res = "error";
        }
        return res = switch (sym) {
            case "-" -> Integer.toString(a - b);
            case "+" -> Integer.toString(a + b);
            case "/" -> Integer.toString(a / b);
            case "*" -> Integer.toString(a * b);
            default -> "error";
        };
    }

    public boolean filterNumbers(RequestDTO request) {
        String sym = request.getSymbol(); //для теста
        int a = request.getA();
        int b = request.getB();

        return sym.equals("*") && (a >= 0 && b >= 0) && Integer.MAX_VALUE / b >= a || sym.equals("*") && (a <= 0 && b >= 0) && Integer.MIN_VALUE / b <= a ||
                sym.equals("*") && (a >= 0 && b <= 0) && Integer.MIN_VALUE / b >= a || sym.equals("*") && (a <= 0 && b <= 0) && (a != Integer.MIN_VALUE && b != Integer.MIN_VALUE) && Integer.MIN_VALUE / b >= Math.abs(a) ||
                sym.equals("+") && (a >= 0 && b >= 0) && Integer.MAX_VALUE - b >= a || sym.equals("+") && (a <= 0 && b >= 0) && Integer.MAX_VALUE > b ||
                sym.equals("+") && (a <= 0 && b <= 0) && Integer.MAX_VALUE - Math.abs(b) >= Math.abs(a) || sym.equals("+") && (a >= 0 && b <= 0) && Integer.MAX_VALUE > a ||
                sym.equals("-") && (a <= 0 && b >= 0) && Integer.MIN_VALUE + b <= a || sym.equals("-") && (a >= 0 && b <= 0) && Integer.MAX_VALUE - a > Math.abs(b) ||
                sym.equals("-") && (a >= 0 && b >= 0) || sym.equals("-") && (a <= 0 && b <= 0) || sym.equals("/");
    }

    @Transactional
    public void createRecord() {
        new EntityOne(sym);
        new EntityTwo(a, b, res, sym);
        System.out.println("CREATE");
    }

    @Transactional
    public void updateRecord() {
        EntityTwo.updateEntityTwo(repo2.getEntityTwoBySymbolId(sym), res, a, b);
        System.out.println("UPDATE");
    }
}