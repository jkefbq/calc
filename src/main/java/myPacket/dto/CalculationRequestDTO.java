package myPacket.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class CalculationRequestDTO {
    private int a;
    private int b;
    private String symbol;
}
