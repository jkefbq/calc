package myPacket.service;

import lombok.Getter;
import myPacket.dto.RequestDTO;
import myPacket.dto.ResponseDTO;
import myPacket.entity.EntityOne;
import myPacket.entity.EntityTwo;
import myPacket.repos.FirstRepository;
import myPacket.repos.SecondRepository;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.FluentQuery;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

@Getter
@Service
public class CalculateService {

    private final FirstRepository repo1;
    private final SecondRepository repo2;
    private final ResponseDTO response;
    private String res;
    private String sym;
    private int a;
    private int b;

    public CalculateService(FirstRepository repo1, SecondRepository repo2, ResponseDTO response) {
        this.repo1 = repo1;
        this.repo2 = repo2;
        this.response = response;
    }

    public String calculateResult(RequestDTO request) {
        a = request.getA();
        b = request.getB();
        sym = request.getSymbol();

        if (!filterNumbers(request)) {
            response.setResult("error");
            return res = "error";
        }

        res = switch (sym) {
            case "-" -> Integer.toString(a - b);
            case "+" -> Integer.toString(a + b);
            case "/" -> Integer.toString(a / b);
            case "*" -> Integer.toString(a * b);
            default -> "error";
        };
        response.setResult(res);
        return res;
    }

    public boolean filterNumbers(RequestDTO request) {
        String sym = request.getSymbol();
        int a = request.getA();
        int b = request.getB();

        return sym.equals("*") && (a >= 0 && b >= 0) && Integer.MAX_VALUE / b >= a || sym.equals("*") && (a <= 0 && b >= 0) && Integer.MIN_VALUE / b <= a ||
                sym.equals("*") && (a >= 0 && b <= 0) && Integer.MIN_VALUE / b >= a || sym.equals("*") && (a <= 0 && b <= 0) && (a != Integer.MIN_VALUE && b != Integer.MIN_VALUE) && Integer.MIN_VALUE / b >= Math.abs(a) ||
                sym.equals("+") && (a >= 0 && b >= 0) && Integer.MAX_VALUE - b >= a || sym.equals("+") && (a <= 0 && b >= 0) && Integer.MAX_VALUE > b ||
                sym.equals("+") && (a <= 0 && b <= 0) && Integer.MAX_VALUE - Math.abs(b) >= Math.abs(a) || sym.equals("+") && (a >= 0 && b <= 0) && Integer.MAX_VALUE > a ||
                sym.equals("-") && (a <= 0 && b >= 0) && Integer.MIN_VALUE + b <= a || sym.equals("-") && (a >= 0 && b <= 0) && Integer.MAX_VALUE - a > Math.abs(b) ||
                sym.equals("-") && (a >= 0 && b >= 0) || sym.equals("-") && (a <= 0 && b <= 0) || sym.equals("/");
    }

    public ResponseDTO createRecord(@RequestBody RequestDTO request) {
        EntityOne entityOne = new EntityOne(sym);
        EntityTwo entityTwo = new EntityTwo(a, b, res, sym);
        repo1.save(entityOne);
        repo2.save(entityTwo);

        System.out.println("CREATE");
        response.setResult(res);
        return response;
    }

    public ResponseDTO updateRecord(@RequestBody RequestDTO request) {
        EntityTwo n = repo2.getEntityTwoBySymbolId(sym); // существующая запись
        n.setResult(res);
        n.setNum1(a);
        n.setNum2(b);
        repo2.save(n);

        System.out.println("UPDATE");
        response.setResult(res);
        return response;
    }
}