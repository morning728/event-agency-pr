package mirea.morning.eventagencypr.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;
import mirea.morning.eventagencypr.model.enums.EventType;
import org.springframework.web.bind.annotation.RequestParam;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class FilterDto {
    private String type;
    private Long minPriceForPerson;
    private Long maxPriceForPerson;
    private Long minMinPrice;
    private Long maxMinPrice;
}
