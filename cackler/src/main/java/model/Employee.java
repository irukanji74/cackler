package model;

import javax.persistence.Column;
import javax.persistence.Entity;
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
	
	@Column(name = "visit_date")
    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @DateTimeFormat(pattern = "yyyy/MM/dd")
    private DateTime date;
	
	@Column(name="salary")
	@NotEmpty
	private Integer salary;
}
