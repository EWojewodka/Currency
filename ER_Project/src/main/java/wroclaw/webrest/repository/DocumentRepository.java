package wroclaw.webrest.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import wroclaw.webrest.entity.DocumentEntity;

@Repository
public interface DocumentRepository extends CrudRepository<DocumentEntity, Integer> {

}
