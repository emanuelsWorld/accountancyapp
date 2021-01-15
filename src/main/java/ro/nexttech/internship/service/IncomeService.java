package ro.nexttech.internship.service;

import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.dto.IncomeDto;

public interface IncomeService {

    Income findById(int id);

    boolean deleteById(int id);

    IncomeDto getDtoFromIncome(Income income);

    IncomeDto saveIncomeDto(IncomeDto incomeDto, String userName);

    Income getIncomeFromDto(IncomeDto incomeDto);

}
