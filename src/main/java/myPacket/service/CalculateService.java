package myPacket.service;

import myPacket.dto.RequestDTO;
import myPacket.dto.ResponseDTO;
import myPacket.entity.EntityOne;
import myPacket.entity.EntityTwo;
import myPacket.repos.FirstRepository;
import myPacket.repos.SecondRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

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

         res = switch (request.getSymbol()) {
            case "-" -> Integer.toString(a - b);
            case "+" -> Integer.toString(a + b);
            case "/" -> Integer.toString(a / b);
            case "*" -> Integer.toString(a * b);
            default -> "error";
         };
         return res;
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
