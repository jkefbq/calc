package myPacket.controllers;
import lombok.AllArgsConstructor;
import myPacket.FirstRepository;
import myPacket.dto.CalculationRequestDTO;
import myPacket.dto.CalculationResponseDTO;
import myPacket.request.Request;
import myPacket.service.FirstService;
import myPacket.service.SecondService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@AllArgsConstructor
@RestController
public class FirstController {
    private final FirstRepository repo1;
    private final FirstService service1;
    private final Request request1;
    private final CalculationResponseDTO response;
    private final SecondService service2;

    @PostMapping("/run")
    public CalculationResponseDTO run(@RequestBody CalculationRequestDTO request) {
        service2.callCalculateResultAndCreateOrUpdate(request);
        return response;
    }

    @PostMapping("/create")
    public void calculateResultAndCreateOrUpdate(CalculationRequestDTO request) {
        String res = service1.calculateResult(request);

        if (repo1.hasSymbol(request.getSymbol()) > 0 && !res.equals("error")) {
            repo1.updateRequest(res, request.getSymbol());
        } else if (!res.equals("error")){
            request1.setResult(res);
            request1.setSymbol(request.getSymbol());
            repo1.save(request1);
        }
        response.setResult(res);
    }
}