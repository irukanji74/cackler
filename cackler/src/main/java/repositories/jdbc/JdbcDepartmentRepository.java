package repositories.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
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
		Collection<Department> depts = this.namedParameterJdbcTemplate.query("SELECT id, department_name FROM department"
				, new RowMapper<Department>(){
					@Override
					public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
						Department department = new Department();
						department.setId(rs.getInt("id"));
						department.setDepartmentName("department_name");
						return department;
					}
					
				});
				
				/* Why not working!!!!!???????(
				"SELECT id, department_name FROM department"
                , new HashMap<String, Object>()
                , BeanPropertyRowMapper.newInstance(Department.class));*/
		System.err.println(depts.size());
		return depts;
	}

	@Override
	public void saveOrUpdate(Department department) throws DataAccessException {
		//Version simplyfying and shortening the number of code
		//this obj is retrieving all the properties from owner.
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(department);
		//department.Number newKey = this.simpleJdbcInsert.executeAndReturnKey(parameterSource);
		//System.out.println(newKey.intValue());
		//department.setId(newKey.intValue());
		this.namedParameterJdbcTemplate.update("Insert into department (id, department_name) values (:id, :departmentName)"
				                               , parameterSource);
		
		//Primitive version, too much of code
		/*String sqlQuery = "Insert into department (id, department_name) values (:id, :departmentName)";
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", department.getId());
		mapSqlParameterSource.addValue("departmentName",department.getDepartmentName());
		this.namedParameterJdbcTemplate.update(sqlQuery, mapSqlParameterSource);
*/
	}

	@Override
	public void removeDepartment(Department deparment) throws DataAccessException {
		// TODO Auto-generated method stub

	}

}
