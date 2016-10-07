package service;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import model.Department;
import repositories.DepartmentRepository;
import repositories.EmployeeRepository;

@Service
public class CacklerService {

	private DepartmentRepository departmentRepository;
	private EmployeeRepository employeeRepository;

	@Autowired
	public CacklerService(DepartmentRepository departmentRepository, EmployeeRepository employeeRepository) {
		this.departmentRepository = departmentRepository;
		this.employeeRepository = employeeRepository;
	}

	@Transactional(readOnly = true)
	public Collection<Department> findAllDepartments() {
		return this.departmentRepository.findAllDepartments();
	}

	@Transactional
	public Department getDeptById(int i) {
		Department department = this.departmentRepository.findById(i);
		return department;
	}

	@Transactional
	public void saveDepartment(Department dept) {
		this.departmentRepository.saveOrUpdate(dept);

	}

}
