package com.trainticket.dao.daoImpl;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.bean.QueryInf;
import com.trainticket.bean.Train;
import com.trainticket.dao.InsertDao;
import com.trainticket.exception.ContentException;

@Repository("insertDao")
public class InsertDaoImpl implements InsertDao {
	Logger logger = LoggerFactory.getLogger(InsertDaoImpl.class);
	
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	@Override
	public boolean insertData(List<Train> l) {
		try{
			if(l.size()<2)
				return false;
			else{
				String sql= getsql(l);
				template.update(sql);
				System.out.println(l.get(0).getStationTrainCode());
			}
			return true;
		}
		catch(Exception e){
			e.getMessage();
			return false;
		}
	}
	
	private String getsql(List<Train> l){
		String sql="insert into Train values";
		String code=l.get(0).getStationTrainCode();
		String code2=l.get(0).getOtherCode();
		for(int i=0;i<l.size();i++){
			if(i==0){
				sql+="('"+code+"','"+code2+"',"+l.get(i).getStationNo()+",'"+l.get(i).getStartTime()
				+"','"+l.get(i).getStartTime()+"','0分钟','"+l.get(i).getStationName()+"'),";
			}
			else if(i==l.size()-1){
				sql+="('"+code+"','"+code2+"',"+l.get(i).getStationNo()+",'"+l.get(i).getArriveTime()
				+"','"+l.get(i).getArriveTime()+"','0分钟','"+l.get(i).getStationName()+"')";
			}
			else{
				sql+="('"+code+"','"+code2+"',"+l.get(i).getStationNo()+",'"+l.get(i).getArriveTime()
				+"','"+l.get(i).getStartTime()+"','"+l.get(i).getStopoverTime()+"','"+l.get(i).getStationName()+"'),";
			}
		}
		return sql;
	}

	@Override
	public void insertSelect(QueryInf q) throws ContentException {
		if(q.getStart()==null||q.getTransfer()==null||q.getEnd()==null){	
			throw new ContentException();
		}
		else if(q.getStart().equals("")||q.getTransfer().equals("")||q.getEnd().equals("")){
			throw new ContentException();
		}
		try{
			String sql ="insert into transferinf(Start,Transfer,End) values('"+q.getStartCity()+"','"+q.getTransfer()+"','"+q.getEndCity()+"')";
			template.update(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			String sql1="update	transferinf set Num=Num+1 where Start='"+q.getStartCity()+"' and Transfer='"+q.getTransfer()+"' and End='"+q.getEndCity()
			+"' ";
			template.update(sql1);
		}catch(Exception e){
		}
		
		
	}
}
