package ninckblokje.bnb.ratetraining.runner;

import lombok.extern.slf4j.Slf4j;
import ninckblokje.bnb.ratetraining.entity.Training;
import ninckblokje.bnb.ratetraining.repository.TrainingRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Optional;

@Component
@Slf4j
public class TrainingRunner implements CommandLineRunner {

    private static final String training = "k8s bits n bytes";

    private TrainingRepository trainingRepository;

    public TrainingRunner(TrainingRepository trainingRepository) {
        this.trainingRepository = trainingRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        Optional<Training> t = trainingRepository.getByName(training);
        if(t.isEmpty()) {
            log.info("Adding training {}", training);
            trainingRepository.save(new Training(null, training, new ArrayList<>()));
        }
    }
}
