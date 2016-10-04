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
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource)
				                    .withTableName("offices")
				                    .usingGeneratedKeyColumns("id");
	}

	@Override
	public Department findById(int id) throws DataAccessException {
		// TODO Auto-generated method stub
		return null;
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
		return this.namedParameterJdbcTemplate.query("SELECT office_name from offices"
				                                     , params
				                                     , BeanPropertyRowMapper.newInstance(Department.class));
		
	}

}
