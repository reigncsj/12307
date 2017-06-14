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
	
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;
	//将网络上获取的数据保存到本地数据库（不属于服务器服务范围）
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

	//将用户选择的中转站进行记录
	@Override
	public void insertSelect(QueryInf q) throws ContentException {
		//判断是否有信息确实
		if(q.getStart()==null||q.getTransfer()==null||q.getEnd()==null){	
			throw new ContentException();
		}
		else if(q.getStart().equals("")||q.getTransfer().equals("")||q.getEnd().equals("")){
			throw new ContentException();
		}
		try{
			//查实插入新的中转方案，失败则说明已经存在
			String sql ="insert into transferinf(Start,Transfer,End) values('"+q.getStartCity()+"','"+q.getTransfer()+"','"+q.getEndCity()+"')";
			template.update(sql);
		}catch(Exception e){
			e.printStackTrace();
		}
		
		try{
			//将对应的方案查询数进行加1
			String sql1="update	transferinf set Num=Num+1 where Start='"+q.getStartCity()+"' and Transfer='"+q.getTransfer()+"' and End='"+q.getEndCity()
			+"' ";
			template.update(sql1);
		}catch(Exception e){
		}
		
		
	}
}
