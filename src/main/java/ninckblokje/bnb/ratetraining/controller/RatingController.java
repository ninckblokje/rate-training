package ninckblokje.bnb.ratetraining.controller;

import ninckblokje.bnb.ratetraining.entity.Rating;
import ninckblokje.bnb.ratetraining.entity.Training;
import ninckblokje.bnb.ratetraining.repository.TrainingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/rating")
public class RatingController {

    private TrainingRepository trainingRepository;

    public RatingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @PostMapping("/{name}")
    public ResponseEntity<Void> rate(@PathVariable("name") String name, @Valid @RequestBody Rating rating) {
        Optional<Training> t = trainingRepository.getByName(name);
        if (t.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        Training training = t.get();
        if (training.getRatings() == null) {
            training.setRatings(new ArrayList<>());
        }

        training.getRatings().add(rating);

        trainingRepository.save(training);

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
