package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;


// ����� ����������� �� Hibernate
@Entity
@Table(name="department")//����� �� ������������ ���� ����� ������ � ������� ���������
public class Department extends BaseEntity {

	 @Column(name="department_name")
	 //@NotEmpty
	protected String departmentName;

	public String getDepartmentName() {
		return departmentName;
	}

	public void setDepartmentName(String departmentName) {
		this.departmentName = departmentName;
	}

}
