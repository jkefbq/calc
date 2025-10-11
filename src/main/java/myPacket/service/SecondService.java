package myPacket.service;

import lombok.Getter;
import myPacket.controllers.FirstController;
import myPacket.dto.CalculationRequestDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Getter
@Component
public class SecondService {
    private final FirstController firstController;

    @Autowired
    public SecondService(FirstController firstController) {
        this.firstController = firstController;
    }

    public void callCalculateResultAndCreateOrUpdate(CalculationRequestDTO request) {
        firstController.calculateResultAndCreateOrUpdate(request);
    }
}
