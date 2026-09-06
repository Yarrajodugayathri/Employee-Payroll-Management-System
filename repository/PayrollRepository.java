package employee_management.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import employee_management.entity.Payroll;

public interface PayrollRepository extends JpaRepository<Payroll, Long> {

}