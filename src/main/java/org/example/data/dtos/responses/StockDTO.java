package org.example.data.dtos.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
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
