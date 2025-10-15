package myPacket.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import myPacket.FirstRepository;
import myPacket.dto.CalculationRequestDTO;
import myPacket.dto.CalculationResponseDTO;
import myPacket.request.Request;
import myPacket.service.FirstService;
import org.springframework.web.bind.annotation.*;

@Slf4j
@AllArgsConstructor
@RestController
public class FirstController {

    private final FirstRepository repo1;
    private final FirstService service1;
    private final Request requestEntity;
    private final CalculationResponseDTO response;
    ObjectMapper objectMapper;

    @PostMapping("/processingRequest")
    public CalculationResponseDTO calculateAndCreateOrUpdate(@RequestBody String jsonMessage) throws JsonProcessingException {
        CalculationRequestDTO request = objectMapper.readValue(jsonMessage, CalculationRequestDTO.class);
        String res = service1.calculateResult(request);
        String sym = request.getSymbol();

        if ((repo1.hasSymbol(sym) > 0) && (!res.equals("error"))) {
            return updateRequest(request);
        } else if (!res.equals("error")){
            return createRequest(request);
        } else { //error
            response.setResult(res);
            return response;
        }
    }

    @PostMapping("/update")
    public CalculationResponseDTO updateRequest(@RequestBody CalculationRequestDTO request) {
        String res = service1.calculateResult(request);

        repo1.updateRequest(res, request.getSymbol());
        response.setResult(res);
        log.info("UPDATE");
        return response;
    }

    @PostMapping("/create")
    public CalculationResponseDTO createRequest(@RequestBody CalculationRequestDTO request) {
        String res = service1.calculateResult(request);

        requestEntity.setResult(res);
        requestEntity.setSymbol(request.getSymbol());
        repo1.save(requestEntity);
        response.setResult(res);
        log.info("CREATE");
        return response;
    }
}