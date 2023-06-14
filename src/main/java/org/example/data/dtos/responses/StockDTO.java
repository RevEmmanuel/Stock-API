package org.example.data.dtos.responses;

import lombok.*;
import java.time.LocalDateTime;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO {

    private Long id;
    private String name;
    private Double currentPrice;
    private LocalDateTime createDate;
    private LocalDateTime lastUpdate;

}
