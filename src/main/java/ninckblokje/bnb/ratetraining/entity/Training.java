package ninckblokje.bnb.ratetraining.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "training")
public class Training {

    @Id
    @JsonIgnore
    private String id;

    @NotNull
    private String name;
    private List<Rating> ratings;
}
