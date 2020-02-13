package ninckblokje.bnb.ratetraining.controller;

import ninckblokje.bnb.ratetraining.dto.RatingDTO;
import ninckblokje.bnb.ratetraining.dto.TrainingDTO;
import ninckblokje.bnb.ratetraining.entity.Rating;
import ninckblokje.bnb.ratetraining.entity.Training;
import ninckblokje.bnb.ratetraining.repository.TrainingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private TrainingRepository trainingRepository;

    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<TrainingDTO> getTraining(@PathVariable("name") String name) {
        Optional<Training> t = trainingRepository.getByName(name);
        if (t.isPresent()) {
            return new ResponseEntity<>(this.mapToDTO(t.get()), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public Iterable<TrainingDTO> getAllTrainings() {
        return StreamSupport.stream(trainingRepository.findAll().spliterator(), false)
                .map(this::mapToDTO)
                .collect(Collectors.toList());
    }

    TrainingDTO mapToDTO(Training training) {
        return new TrainingDTO(training.getName(), training.getRatings().stream().map(this::mapToDTO).collect(Collectors.toList()));
    }

    RatingDTO mapToDTO(Rating rating) {
        return new RatingDTO(rating.getByWho(), rating.getRating());
    }
}
