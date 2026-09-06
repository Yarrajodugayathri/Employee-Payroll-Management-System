package employee_management.service;

import org.springframework.stereotype.Service;
import java.util.List;
import employee_management.entity.Employee;
import employee_management.repository.EmployeeRepository;

@Service
public class EmployeeService {

	    private final EmployeeRepository employeeRepository;

	    public EmployeeService(EmployeeRepository employeeRepository) {
	        this.employeeRepository = employeeRepository;
	    }

	    // Create employee
	    public Employee addEmployee(Employee employee) {
	        return employeeRepository.save(employee);
	    }

	    // Get all employees
	    public List<Employee> getAllEmployees() {
	        return employeeRepository.findAll();
	    }

	    // Get employee by ID
	    public Employee getEmployeeById(Long id) {
	        return employeeRepository.findById(id).orElse(null);
	    }

	    // Update employee
	    public Employee updateEmployee(Long id, Employee employee) {

	        Employee existingEmployee = employeeRepository.findById(id).orElse(null);

	        if (existingEmployee != null) {

	            existingEmployee.setFirstName(employee.getFirstName());
	            existingEmployee.setLastName(employee.getLastName());
	            existingEmployee.setEmail(employee.getEmail());
	            existingEmployee.setDepartment(employee.getDepartment());
	            existingEmployee.setDesignation(employee.getDesignation());
	            existingEmployee.setSalary(employee.getSalary());

	            return employeeRepository.save(existingEmployee);
	        }

	        return null;
	    }

	    // Delete employee
	    public void deleteEmployee(Long id) {
	        employeeRepository.deleteById(id);
	    }
	}
