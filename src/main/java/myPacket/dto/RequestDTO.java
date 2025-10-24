package myPacket.dto;

import lombok.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RequestDTO {
    private int a;
    private int b;
    private String symbol;
}
