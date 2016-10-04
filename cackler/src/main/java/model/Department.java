package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotEmpty;

// »ћ≈≈“ «ј¬»—»ћќ—“№ ќ“ Hibernate
@Entity
@Table(name = "Department")//можно не использовать если имена класса и таблицы совпадают
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
