package ninckblokje.bnb.ratetraining.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class RatingDTO {

    @NotNull
    private String byWho;
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;
}
