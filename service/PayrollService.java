package employee_management.service;

import java.util.List;

import org.springframework.stereotype.Service;

import employee_management.entity.Payroll;
import employee_management.repository.PayrollRepository;

@Service
public class PayrollService {

    private final PayrollRepository payrollRepository;

    public PayrollService(PayrollRepository payrollRepository) {
        this.payrollRepository = payrollRepository;
    }

    // Add Payroll
    public Payroll addPayroll(Payroll payroll) {

        double grossSalary =
                payroll.getBasicSalary() + payroll.getAllowance();

        double netSalary =
                grossSalary - payroll.getDeduction();

        payroll.setGrossSalary(grossSalary);
        payroll.setNetSalary(netSalary);

        return payrollRepository.save(payroll);
    }

    // Get all Payroll records
    public List<Payroll> getAllPayrolls() {
        return payrollRepository.findAll();
    }

    // Get Payroll by ID
    public Payroll getPayrollById(Long id) {
        return payrollRepository.findById(id).orElse(null);
    }

    // Delete Payroll
    public void deletePayroll(Long id) {
        payrollRepository.deleteById(id);
    }
}