package test_service;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import model.Department;
import service.CacklerService;

@ContextConfiguration(locations = {"classpath:spring/business-config.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@ActiveProfiles("jdbc")
public class CacklersServiceTests {

	@Autowired
	public CacklerService cacklerService;
	
	/*@Test
	public void findDepartmentById(){
		Department department = cacklerService.getDeptById(3);
		System.out.println(department.getDepartmentName());
		assertThat(department.getDepartmentName()).startsWith("Accountant");
	}
	
	@Test
	public void findAllDepartments(){
		Collection<Department> departments = this.cacklerService.findAllDepartments();
		assertThat(departments.size()).isEqualTo(5);
	}
	
	@Test
	public void saveDepartment(){
		Department dept = new Department();
		dept.setId(4);
		dept.setDepartmentName("Finance Department");
		this.cacklerService.saveDepartment(dept);
	}*/
	
	@Test
	public void saveDepartmentShorterVersion(){
		Department dept = new Department();
		dept.setId(7);
		dept.setDepartmentName("CEO");
		this.cacklerService.saveDepartment(dept);
	}
	
	
	
	
	
	
	
	
	
}
