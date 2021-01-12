package ro.nexttech.internship.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import ro.nexttech.internship.domain.Income;

@Repository
public interface IncomeRepository extends JpaRepository<Income,Integer>, JpaSpecificationExecutor<Income> {
}
