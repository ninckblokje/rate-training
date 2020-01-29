package ninckblokje.bnb.ratetraining.controller;

import ninckblokje.bnb.ratetraining.entity.Rating;
import ninckblokje.bnb.ratetraining.entity.Training;
import ninckblokje.bnb.ratetraining.repository.TrainingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/api/rating")
@Transactional
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

        if (rating.getRating() < 6) {
            throw new IllegalArgumentException(String.format("%d is really too low!", rating.getRating()));
        }

        return new ResponseEntity<>(HttpStatus.ACCEPTED);
    }
}
