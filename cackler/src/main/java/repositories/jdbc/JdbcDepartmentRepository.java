package repositories.jdbc;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import model.Department;
import repositories.DepartmentRepository;

@Repository
public class JdbcDepartmentRepository implements DepartmentRepository {

	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public JdbcDepartmentRepository(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		System.out.println(namedParameterJdbcTemplate);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("offices")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public Department findById(int id) throws DataAccessException {
		Department department;
		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		System.out.println(id+"");
		department = this.namedParameterJdbcTemplate.queryForObject("SELECT department_name FROM department WHERE id= :id"
				                                                    , params
				                                                    ,BeanPropertyRowMapper.newInstance(Department.class));
		System.out.println(department.getDepartmentName());
		return department;
	}

	@Override
	public Collection<Department> findAllDepartments() throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void saveOrUpdate(Department department) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public void removeDepartment(Department deparment) throws DataAccessException {
		// TODO Auto-generated method stub

	}

	@Override
	public Collection<Department> findAll() throws DataAccessException {
		Map<String, Object> params = new HashMap<>();
		Collection<Department> depts = this.namedParameterJdbcTemplate.query("SELECT id, office_name from offices"
                , params
                , BeanPropertyRowMapper.newInstance(Department.class));
		System.err.println(depts.size());
		return depts;

	}

	@Override
	public String findNameById(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
	}

}
