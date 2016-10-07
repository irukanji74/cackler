package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;
import org.hibernate.validator.constraints.NotEmpty;
import org.joda.time.DateTime;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name="employee")
public class Employee extends BaseEntity {

	@Column(name="first_name")
	@NotEmpty
	private String firstName;
	
	@Column(name="last_name")
	@NotEmpty
	private String lastName;
	
	@Column(name = "birth_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime birth_date;
	
	@Column(name="salary")
	@NotEmpty
	private Integer salary;
	
	@OneToOne
	@JoinColumn(name="department_id")
	private Department department;

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public DateTime getBirth_date() {
		return birth_date;
	}

	public void setBirth_date(DateTime birth_date) {
		this.birth_date = birth_date;
	}

	public Integer getSalary() {
		return salary;
	}

	public void setSalary(Integer salary) {
		this.salary = salary;
	}

	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}
}
