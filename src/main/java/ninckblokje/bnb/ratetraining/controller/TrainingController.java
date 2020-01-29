package ninckblokje.bnb.ratetraining.controller;

import ninckblokje.bnb.ratetraining.entity.Training;
import ninckblokje.bnb.ratetraining.repository.TrainingRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

@RestController
@RequestMapping("/api/training")
public class TrainingController {

    private TrainingRepository trainingRepository;

    public TrainingController(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @GetMapping(value = "/{name}", produces = "application/json")
    public ResponseEntity<Training> getTraining(@PathVariable("name") String name) {
        Optional<Training> t = trainingRepository.getByName(name);
        if (t.isPresent()) {
            return new ResponseEntity<>(t.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(produces = "application/json")
    public Iterable<Training> getAllTrainings() {
        return trainingRepository.findAll();
    }
}
