package ninckblokje.bnb.ratetraining.entity;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
public class Rating {

    @NotNull
    private String byWho;
    @NotNull
    @Min(1)
    @Max(10)
    private Integer rating;
}
