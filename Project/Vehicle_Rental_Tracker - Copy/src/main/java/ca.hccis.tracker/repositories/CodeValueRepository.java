package ca.hccis.tracker.repositories;

import java.util.List;

import ca.hccis.tracker.entity.CodeValue;
import ca.hccis.tracker.entity.CodeValueId;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CodeValueRepository extends CrudRepository<CodeValue, CodeValueId> {
}