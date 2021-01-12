package ro.nexttech.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ro.nexttech.internship.domain.Firm;
import ro.nexttech.internship.domain.Income;

@Repository
public interface FirmRepository extends JpaRepository<Firm,Integer>, JpaSpecificationExecutor<Firm> {
}
