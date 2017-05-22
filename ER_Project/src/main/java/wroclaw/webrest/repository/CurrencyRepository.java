package wroclaw.webrest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wroclaw.webrest.entity.CurrencyEntity;

@Repository
public interface CurrencyRepository extends CrudRepository<CurrencyEntity, Integer> {
}
