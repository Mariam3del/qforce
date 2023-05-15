package nl.qnh.qforce.database.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import nl.qnh.qforce.database.model.Stats;
@Repository
public interface StatsRepository extends CrudRepository<Stats, Integer>{

}
