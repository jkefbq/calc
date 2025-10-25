package myPacket.controllers;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myPacket.repos.EntityOneRepository;
import myPacket.dto.RequestDTO;
import myPacket.service.CalculateService;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class RestController1 {

    private final EntityOneRepository repo1;
    private final CalculateService calcService;

    @PostMapping("/processingRequest")
    public void calculateAndCreateOrUpdate(@RequestBody RequestDTO requestDTO) {
        int a = requestDTO.getA();
        int b = requestDTO.getB();
        String sym = requestDTO.getSymbol();
        String res = calcService.calculateResult(sym, a, b);

        if ((repo1.symbolCount(sym) > 0) && (!res.equals("error"))) {
            calcService.updateRecord(res, sym, a, b);
        } else if (!res.equals("error")){
            calcService.createRecord(res, sym, a, b);
        }
        System.out.println();
    }
}