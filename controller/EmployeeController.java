package employee_management.controller;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import employee_management.entity.Employee;
import employee_management.service.EmployeeService;

@Controller
@RequestMapping("/employees")
public class EmployeeController {
	    private final EmployeeService employeeService;

	    public EmployeeController(EmployeeService employeeService) {
	        this.employeeService = employeeService;
	    }

	    // Display all employees
	    @GetMapping
	    public String listEmployees(Model model) {
	        model.addAttribute("employees", employeeService.getAllEmployees());
	        return "employees";
	    }

	    // Show Add Employee page
	    @GetMapping("/new")
	    public String showAddEmployeeForm(Model model) {
	        model.addAttribute("employee", new Employee());
	        return "add-employee";
	    }

	    // Save Employee
	    @PostMapping("/save")
	    public String saveEmployee(@ModelAttribute("employee") Employee employee) {
	        employeeService.addEmployee(employee);
	        return "redirect:/employees";
	    }

	    // Show Edit Employee page
	    @GetMapping("/edit/{id}")
	    public String showEditEmployeeForm(@PathVariable Long id, Model model) {

	        Employee employee = employeeService.getEmployeeById(id);

	        model.addAttribute("employee", employee);

	        return "edit-employee";
	    }

	    // Update Employee
	    @PostMapping("/update/{id}")
	    public String updateEmployee(
	            @PathVariable Long id,
	            @ModelAttribute("employee") Employee employee) {

	        employeeService.updateEmployee(id, employee);

	        return "redirect:/employees";
	    }

	    // Delete Employee
	    @GetMapping("/delete/{id}")
	    public String deleteEmployee(@PathVariable Long id) {

	        employeeService.deleteEmployee(id);

	        return "redirect:/employees";
	    }
	}
