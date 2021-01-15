package ro.nexttech.internship.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ro.nexttech.internship.domain.Income;
import ro.nexttech.internship.domain.User;
import ro.nexttech.internship.dto.IncomeDto;
import ro.nexttech.internship.repository.IncomeRepository;
import ro.nexttech.internship.service.FirmService;
import ro.nexttech.internship.service.IncomeService;
import ro.nexttech.internship.service.UserService;

import java.util.Objects;

@Service
public class IncomeServiceImpl implements IncomeService {

    private IncomeRepository incomeRepository;
    private FirmService firmService;
    private UserService userService;

    @Autowired
    public void setIncomeRepository(IncomeRepository incomeRepository) {
        this.incomeRepository = incomeRepository;
    }

    @Autowired
    public void setFirmService(FirmService firmService) {
        this.firmService = firmService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    @Override
    public Income findById(int id) {
        return incomeRepository.findById(id).orElse(null);
    }

    @Override
    public boolean deleteById(int id) {
        try {
            incomeRepository.deleteById(id);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public IncomeDto getDtoFromIncome(Income income) {

        IncomeDto incomeDto = new IncomeDto();
        incomeDto.setFirmId(income.getFirm().getFirmId());
        incomeDto.setIncomeId(income.getIncomeId());
        incomeDto.setIncomeName(income.getIncomeName());
        incomeDto.setIssueDate(income.getIssueDate());
        incomeDto.setAmmount(income.getAmmount());
        incomeDto.setType(income.getType());

        return incomeDto;
    }

    @Override
    public IncomeDto saveIncomeDto(IncomeDto incomeDto, String userName) {
        User user = userService.findByUserName(userName);
        Income income = getIncomeFromDto(incomeDto);

        if (user.getFirm() == income.getFirm()) {
            incomeRepository.save(getIncomeFromDto(incomeDto));
            return incomeDto;
        }
        return null;
    }

    @Override
    public Income getIncomeFromDto(IncomeDto incomeDto) {

        Income income = new Income();
        income.setType(incomeDto.getType());
        income.setIssueDate(incomeDto.getIssueDate());
        income.setAmmount(incomeDto.getAmmount());
        income.setIncomeId(incomeDto.getIncomeId());
        income.setIncomeName(incomeDto.getIncomeName());
        income.setFirm(firmService.findById(incomeDto.getFirmId()));

        return income;
    }


}
