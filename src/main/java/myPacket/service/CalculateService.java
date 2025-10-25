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

    public CalculateService(EntityOneRepository repo1, EntityTwoRepository repo2) {
        this.repo1 = repo1;
        this.repo2 = repo2;
    }

    public String calculateResult(String sym, int a, int b) {
        if (!filterNumbers(sym, a, b)) {
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

    public boolean filterNumbers(String sym, int a, int b) {
        return sym.equals("*") && (a >= 0 && b >= 0) && Integer.MAX_VALUE / b >= a || sym.equals("*") && (a <= 0 && b >= 0) && Integer.MIN_VALUE / b <= a ||
                sym.equals("*") && (a >= 0 && b <= 0) && Integer.MIN_VALUE / b >= a || sym.equals("*") && (a <= 0 && b <= 0) && (a != Integer.MIN_VALUE && b != Integer.MIN_VALUE) && Integer.MIN_VALUE / b >= Math.abs(a) ||
                sym.equals("+") && (a >= 0 && b >= 0) && Integer.MAX_VALUE - b >= a || sym.equals("+") && (a <= 0 && b >= 0) && Integer.MAX_VALUE > b ||
                sym.equals("+") && (a <= 0 && b <= 0) && Integer.MAX_VALUE - Math.abs(b) >= Math.abs(a) || sym.equals("+") && (a >= 0 && b <= 0) && Integer.MAX_VALUE > a ||
                sym.equals("-") && (a <= 0 && b >= 0) && Integer.MIN_VALUE + b <= a || sym.equals("-") && (a >= 0 && b <= 0) && Integer.MAX_VALUE - a > Math.abs(b) ||
                sym.equals("-") && (a >= 0 && b >= 0) || sym.equals("-") && (a <= 0 && b <= 0) || sym.equals("/");
    }

    @Transactional
    public void createRecord(String res, String sym, int a, int b) {
        repo2.save(new EntityTwo(a, b, res, repo1.save(new EntityOne(sym))));
        System.out.println("CREATE");
    }

    @Transactional
    public void updateRecord(String res, String sym, int a, int b) {
        EntityOne entityOne = repo1.getBySymbol(sym);//нашли в 'one' запись с нужным символом
        repo2.getBySymbolId(entityOne).updateEntityTwo(res, a, b);//нашли в 'two' запись с таким же entityOne => с нужным символом + обновили
        System.out.println("UPDATE");
    }
}