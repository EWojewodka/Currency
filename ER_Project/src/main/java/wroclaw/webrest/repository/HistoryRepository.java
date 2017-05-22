package wroclaw.webrest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wroclaw.webrest.entity.HistoryEntity;

@Repository
public interface HistoryRepository extends CrudRepository<HistoryEntity, Integer> {

}
