package myPacket.dto;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Setter
@Getter
public class RequestDTO {
    private int a;
    private int b;
    private String symbol;
}
