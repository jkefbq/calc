package myPacket.controllers;

import lombok.AllArgsConstructor;
import myPacket.repos.EntityOneRepository;
import myPacket.service.CalculateService;
import myPacket.service.DbService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class RestController1 {

    private final EntityOneRepository repo1;
    private final CalculateService calcService;
    private final DbService dbService;

    @PostMapping("/processingRequest")
    public void calculateAndCreateOrUpdate(int a, int b, String sym) {
        String res = calcService.calculateResult(sym, a, b);

        if ((repo1.symbolCount(sym) > 0) && (!res.equals("error"))) {
            dbService.updateRecord(res, sym, a, b);
        } else if (!res.equals("error")){
            dbService.createRecord(res, sym, a, b);
        }
        System.out.println();
    }
}