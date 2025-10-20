package myPacket.controllers;

import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myPacket.repos.FirstRepository;
import myPacket.dto.RequestDTO;
import myPacket.dto.ResponseDTO;
import myPacket.service.CalculateService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class FirstController {

    private final FirstRepository repo1;
    private final CalculateService calcService;
    private final ResponseDTO response;

    @PostMapping("/processingRequest")
    public ResponseDTO calculateAndCreateOrUpdate(@RequestBody RequestDTO request) {
        System.out.println("//////");
        String res = calcService.calculateResult(request);

        if ((repo1.symbolCount(request.getSymbol()) > 0) && (!res.equals("error"))) {
            return calcService.updateRecord(request);
        } else if (!res.equals("error")){
            return calcService.createRecord(request);
        }
        return response;
    }
}