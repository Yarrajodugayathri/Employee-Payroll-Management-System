package employee_management.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import employee_management.entity.Employee;
import employee_management.entity.Payroll;
import employee_management.service.EmployeeService;
import employee_management.service.PayrollService;

@Controller
public class PayrollController {

    private final PayrollService payrollService;
    private final EmployeeService employeeService;

    public PayrollController(PayrollService payrollService,
                             EmployeeService employeeService) {
        this.payrollService = payrollService;
        this.employeeService = employeeService;
    }

    // Show all payroll records
    @GetMapping("/payroll")
    public String payrollList(Model model) {

        List<Payroll> payrolls =
                payrollService.getAllPayrolls();

        model.addAttribute("payrolls", payrolls);

        return "payroll";
    }

    // Show Add Payroll page
    @GetMapping("/payroll/new")
    public String showPayrollForm(Model model) {

        List<Employee> employees =
                employeeService.getAllEmployees();

        model.addAttribute("payroll", new Payroll());
        model.addAttribute("employees", employees);

        return "add-payroll";
    }

    // Save Payroll
    @PostMapping("/payroll/save")
    public String savePayroll(
            @ModelAttribute("payroll") Payroll payroll,
            @RequestParam("employeeId") Long employeeId) {

        Employee employee =
                employeeService.getEmployeeById(employeeId);

        payroll.setEmployee(employee);

        payrollService.addPayroll(payroll);

        return "redirect:/payroll";
    }

    // Delete Payroll
    @GetMapping("/payroll/delete/{id}")
    public String deletePayroll(@PathVariable Long id) {

        payrollService.deletePayroll(id);

        return "redirect:/payroll";
    }
}