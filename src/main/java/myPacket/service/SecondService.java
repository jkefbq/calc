package myPacket.service;

import lombok.AllArgsConstructor;
import lombok.Getter;
import myPacket.controllers.FirstController;
import myPacket.dto.CalculationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
@AllArgsConstructor
public class SecondService {
    private final FirstController firstController;

    public void callCalculateResultAndCreateOrUpdate(CalculationRequestDTO request) {
        firstController.calculateResultAndCreateOrUpdate(request);
    }
}
