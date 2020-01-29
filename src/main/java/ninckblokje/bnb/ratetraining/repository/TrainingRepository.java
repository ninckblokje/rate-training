package ninckblokje.bnb.ratetraining.repository;

import ninckblokje.bnb.ratetraining.entity.Training;
import org.springframework.data.elasticsearch.repository.ElasticsearchCrudRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TrainingRepository extends ElasticsearchCrudRepository<Training,String> {

    Optional<Training> getByName(String name);
}
