package ninckblokje.bnb.ratetraining.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TrainingDTO {
    @NotNull
    private String name;
    private List<RatingDTO> ratings;
}
