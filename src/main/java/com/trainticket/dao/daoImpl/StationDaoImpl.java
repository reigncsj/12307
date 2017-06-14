package com.trainticket.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.dao.StationDao;
import com.trainticket.exception.DBException;

//是用来获取与车站相关的信息
@Repository("stationDao")
public class StationDaoImpl implements StationDao {
	//数据库相关的类
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	//按照车站前缀获得类似的车站
	@Override
	public List<String> getStationByName(String station) throws DBException {
		try{
			return (List<String>) template.queryForList("select TName from trainstation where TName like ?",   
		    new Object[]{station+"%"}, String.class);
		}catch(Exception e){
			throw new DBException();
		}
	}
	//按照车站拼音缩写获得相应的车站
	@Override
	public List<String> getStationByPy(String station) throws DBException {
		try{
			return (List<String>) template.queryForList("select TName from trainstation where Tpy like ?",   
					new Object[]{station+"%"}, String.class);
		}catch(Exception e){
			throw new DBException();
		}
	}

	//获得车战对应的查询码
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
	//听过查询码获得相应的车站名
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

	//获得相同城市里所有的车站
	@Override
	public List<String> getCommonCityStation(String tname) {
		try{
			List<String> l=(List<String>) template.queryForList("select TName from trainstation where City = (select City from trainstation where "
					+ "TName = ? )",   
				 new Object[]{tname}, String.class);
			return l;
		}catch(Exception e){
			return null;
		}
	}

	//获得车站所在的城市
	@Override
	public String getCity(String tname) {
		try{
			List<String> l=(List<String>) template.queryForList("select City from trainstation where TName = ?",   
				 new Object[]{tname}, String.class);
			return l.get(0);
		}catch(Exception e){
			return tname;
		}
	}
}
