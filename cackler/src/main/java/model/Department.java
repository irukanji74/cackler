package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

// ����� ����������� �� Hibernate
@Entity
@Table(name = "Department")//����� �� ������������ ���� ����� ������ � ������� ���������
public class Department extends BaseEntity {

	 @Column(name = "department_name")
	 @NotEmpty
	protected String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
