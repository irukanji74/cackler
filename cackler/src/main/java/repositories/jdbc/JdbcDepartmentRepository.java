package repositories.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.BeanPropertySqlParameterSource;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.core.simple.SimpleJdbcTemplate;
import org.springframework.orm.ObjectRetrievalFailureException;
import org.springframework.stereotype.Repository;

import model.Department;
import repositories.DepartmentRepository;

@Repository
public class JdbcDepartmentRepository implements DepartmentRepository {

	// All the CRUD ops with NamedParameterJdbcTemplate
    // http://www.dineshonjava.com/2012/12/using-namedparameterjdbctemplate-in.html#.V_i2g_mLTDc
	
	// if i want to use SimpleJdbcTemplate instance i can get it by
	// JdbcTemplate template = (JdbcTemplate)namedParameterJdbcTemplate.getJdbcOperations();
	private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

	private SimpleJdbcInsert simpleJdbcInsert;

	@Autowired
	public JdbcDepartmentRepository(DataSource dataSource) {
		this.namedParameterJdbcTemplate = new NamedParameterJdbcTemplate(dataSource);
		System.out.println(namedParameterJdbcTemplate);
		this.simpleJdbcInsert = new SimpleJdbcInsert(dataSource).withTableName("department")
				.usingGeneratedKeyColumns("id");
	}

	@Override
	public Department findById(int id) {
		Department department;

		Map<String, Object> params = new HashMap<>();
		params.put("id", id);
		department = this.namedParameterJdbcTemplate.queryForObject(
				"SELECT department_name FROM department WHERE id= :id", params,
				BeanPropertyRowMapper.newInstance(Department.class));

		return department;
	}

	@Override
	public Collection<Department> findAllDepartments() throws DataAccessException {
		Collection<Department> depts = this.namedParameterJdbcTemplate
				.query("SELECT id, department_name FROM department", new RowMapper<Department>() {
					@Override
					public Department mapRow(ResultSet rs, int rowNum) throws SQLException {
						Department department = new Department();
						department.setId(rs.getInt("id"));
						department.setDepartmentName("department_name");
						return department;
					}

				});

		/*
		 * Why not working!!!!!???????(
		 * "SELECT id, department_name FROM department" , new HashMap<String,
		 * Object>() , BeanPropertyRowMapper.newInstance(Department.class));
		 */
		System.err.println(depts.size());
		return depts;
	}

	@Override
	public void saveOrUpdate(Department department) throws DataAccessException {
		// this obj is retrieving all the properties from owner.
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(department);
		if (department.isNew()) {

			//How to retrieve an auto generated key 
			//http://docs.spring.io/spring/docs/current/spring-framework-reference/htmlsingle/#jdbc-auto-genereted-keys
			
			// department.Number newKey =
			// this.simpleJdbcInsert.executeAndReturnKey(parameterSource);
			// System.out.println(newKey.intValue());
			// department.setId(newKey.intValue());

			// Version simplyfying and shortening the number of code
			this.namedParameterJdbcTemplate.update(
					"Insert into department (id, department_name) values (:id, :departmentName)", parameterSource);

		} else {

			// update existing department
			this.namedParameterJdbcTemplate.update("UPDATE department SET department_name=:departmentName WHERE id=:id",
					parameterSource);
		}
		// Primitive version, too much of code
		/*
		 * @Override public void saveOrUpdate(Department department) throws
		 * DataAccessException { String sqlQuery =
		 * "Insert into department (id, department_name) values (:id, :departmentName)"
		 * ; MapSqlParameterSource mapSqlParameterSource = new
		 * MapSqlParameterSource(); mapSqlParameterSource.addValue("id",
		 * department.getId());
		 * mapSqlParameterSource.addValue("departmentName",department.
		 * getDepartmentName());
		 * this.namedParameterJdbcTemplate.update(sqlQuery,
		 * mapSqlParameterSource); }
		 */
	}

	@Override
	public void saveOrUpdateWithSimpleJdbcInsert(Department department) throws DataAccessException {
		BeanPropertySqlParameterSource parameterSource = new BeanPropertySqlParameterSource(department);
		String sqlQuery = "Insert into department (id, department_name) values (:id, :departmentName)";
		MapSqlParameterSource mapSqlParameterSource = new MapSqlParameterSource();
		mapSqlParameterSource.addValue("id", department.getId());
		mapSqlParameterSource.addValue("departmentName", department.getDepartmentName());
		if (department.isNew()) {
			simpleJdbcInsert.execute(mapSqlParameterSource);
		} else {
			// update existing department
			this.namedParameterJdbcTemplate.update("UPDATE department SET department_name=:departmentName WHERE id=:id",
					parameterSource);
		}
	}

	@Override
	public void removeDepartment(int id) throws DataAccessException {
		final String deleteSql = "DELETE FROM department WHERE id = :id";
		SqlParameterSource paramSource = new MapSqlParameterSource("id", id);

		this.namedParameterJdbcTemplate.update(deleteSql, paramSource);

	}
	
}
