package com.trainticket.dao.daoImpl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.dao.StationDao;
import com.trainticket.exception.DBException;

@Repository("stationDao")
public class StationDaoImpl implements StationDao {
	
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	@Override
	public List<String> getStationByName(String station) throws DBException {
		try{
			return (List<String>) template.queryForList("select TName from trainstation where TName like ?",   
		    new Object[]{station+"%"}, String.class);
		}catch(Exception e){
			throw new DBException();
		}
	}
	
	@Override
	public List<String> getStationByPy(String station) throws DBException {
		try{
			return (List<String>) template.queryForList("select TName from trainstation where Tpy like ?",   
					new Object[]{station+"%"}, String.class);
		}catch(Exception e){
			throw new DBException();
		}
	}

	@Override
	public String getCode(String tname) {
		try{
			List<String> l=(List<String>) template.queryForList("select TCode from trainstation where TName = ?",   
				 new Object[]{tname}, String.class);
			return l.get(0);
		}catch(Exception e){
			return "";
		}
	}
	
	@Override
	public String getNameByCode(String code) {
		try{
			List<String> l=(List<String>) template.queryForList("select TName from trainstation where TCode = ?",   
				 new Object[]{code}, String.class);
			return l.get(0);
		}catch(Exception e){
			return "";
		}
	}
	
	

}
