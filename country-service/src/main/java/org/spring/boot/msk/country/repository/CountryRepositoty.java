package org.spring.boot.msk.country.repository;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.spring.boot.msk.common.model.Country;

@Repository
public class CountryRepositoty {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public Country getCountryByCode(String countryCode) {
		String query = "select code , name, continent, region, population from country where code = ? ";
		
		return jdbcTemplate.queryForObject(query, new CountryRowMapper(), countryCode);
	}
	
	
	public Country getCountryByReigon(String region) {
		String query = "select code , name, continent, region, population from country where region = ? ";
		
		return jdbcTemplate.queryForObject(query, new CountryRowMapper(), region);
	}
	
	public List<Country> getAllCountries() {
		String query = "select code , name, continent, region, population from country";
		
		return jdbcTemplate.query(query,  new CountryRowMapper());
	}
	
	
	static  class CountryRowMapper implements RowMapper<Country>{

		@Override
		public Country mapRow(ResultSet rs, int rowNum) throws SQLException {
			return Country
					.builder()
					.code(rs.getString("code"))
					.name(rs.getString("name"))
					.description(rs.getString("name"))
					.continent(rs.getString("continent"))
					.region(rs.getString("region"))
					.population(rs.getLong("population")).build();
		}
		
	}



	
}
