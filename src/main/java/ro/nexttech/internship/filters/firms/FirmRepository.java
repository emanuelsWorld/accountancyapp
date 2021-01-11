package ro.nexttech.internship.filters.firms;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import ro.nexttech.internship.domain.Firm;

public interface FirmRepository extends JpaRepository<Firm, Long>, JpaSpecificationExecutor<Firm> {

}
