package myPacket.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myPacket.FirstRepository;
import myPacket.dto.CalculationRequestDTO;
import myPacket.dto.CalculationResponseDTO;
import myPacket.kafka.KafkaProducer;
import myPacket.request.Request;
import myPacket.service.FirstService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class FirstController {

    private final FirstRepository repo1;
    private final FirstService service1;
    private final Request request1;
    private final CalculationResponseDTO response;
    private final KafkaProducer kafkaProducer;

    @PostMapping("/sendResponse")
    public void sendResponse(CalculationResponseDTO response) throws JsonProcessingException {
        kafkaProducer.sendMessage(response);
    }

    @PostMapping("/processingRequest")
    public void calculateResultAndCreateOrUpdate(CalculationRequestDTO request) {
        String res = service1.calculateResult(request);
        String sym = request.getSymbol();

        if ((repo1.hasSymbol(sym) > 0) && (!res.equals("error"))) {
            repo1.updateRequest(res, sym);
            log.info("UPDATE");
        } else if (!res.equals("error")){
            request1.setResult(res);
            request1.setSymbol(sym);
            repo1.save(request1);
            log.info("CREATE");
        }
        response.setResult(res);
    }
}