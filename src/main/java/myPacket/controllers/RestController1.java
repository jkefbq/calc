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
    public void calculateAndCreateOrUpdate(@RequestBody RequestDTO request) {
        System.out.println("----------------");
        String res = calcService.calculateResult(request);

        if ((repo1.symbolCount(request.getSymbol()) > 0) && (!res.equals("error"))) {
            calcService.updateRecord();
        } else if (!res.equals("error")){
            calcService.createRecord();
        }
    }
}