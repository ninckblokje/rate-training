package ninckblokje.bnb.ratetraining.repository;

import ninckblokje.bnb.ratetraining.entity.Training;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepository extends MongoRepository<Training,String> {

    Optional<Training> getByName(String name);
}
