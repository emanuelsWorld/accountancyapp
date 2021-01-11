package ro.nexttech.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ro.nexttech.internship.domain.Firm;

@Repository
public interface FirmRepository extends JpaRepository<Firm,Integer> {
}
