package com.trainticket.dao.daoImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.bean.QueryInf;
import com.trainticket.dao.TransferStationDao;
import com.trainticket.exception.DBException;
@Repository("transferStationDao")
public class TransferStationDaoImpl implements TransferStationDao {
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;

	@Override
	public List<String> queryTransferStation(QueryInf q) throws DBException {
		try{
			return template.queryForList(getSql(q)+"limit 0,"+q.getNum(), String.class);
		}catch(Exception e){
			throw new DBException();
		}
	}
	
	private String getSql(QueryInf q){
		String sql="select b1.TName from ";
		if(q.getStartStation()==null){
				sql+= "(select t2.TName,t2.TCode from train t2 where t2.TCode in "
				+ "(select TCode from train where TName = '"+q.getStart()+"') and t2.TNo> "
				+ "(select t3.TNo from train t3 where t3.TCode=t2.TCode and t3.TName='"+q.getStart()+"'))";
		}else{
			sql+="(select t2.TName,t2.TCode from train t2 where (";
			List<String> l=q.getStartStation();
			for(int i=0;i<l.size();i++){
				sql+="(t2.TCode in "
				+ "(select TCode from train where TName = '"+l.get(i)+"') and t2.TNo> "
				+ "(select t3.TNo from train t3 where t3.TCode=t2.TCode and t3.TName='"+l.get(i)+"'))";
				if(i!=l.size()-1)
					sql+=" OR ";
			}
			sql+="))";
		}
		sql+= " b1,(select t4.TName,t4.TCode from train t4 where";
		if(q.getEndStation()==null){
			sql+=" t4.TCode in "
					+ "(select TCode from train where TName = '"+q.getEnd()+"') and "
					+ "t4.TNo< (select t5.TNo from train t5 where t5.TCode=t4.TCode and t5.TName='"+q.getEnd()+"'))";
					//+ " b2 where b1.TName=b2.TName and b1.TCode<>b2.TCode GROUP BY b1.TName ORDER BY num desc";
		}
		else{
			sql+=" (";
			List<String> l2=q.getEndStation();
			for(int i=0;i<l2.size();i++){
				sql+="(t4.TCode in "
						+ "(select TCode from train where TName = '"+l2.get(i)+"') and "
						+ "t4.TNo< (select t5.TNo from train t5 where t5.TCode=t4.TCode and t5.TName='"+l2.get(i)+"'))";
				if(i!=l2.size()-1)
					sql+=" OR ";
			}
			sql+="))";
		}
		sql+="b2 where b1.TName=b2.TName and b1.TCode<>b2.TCode and"
				+ " b1.TName not in (select TName from trainstation where City = '"+q.getStartCity()+"')"
						+ " and b2.TName not in (select TName from trainstation where City = '"+q.getEndCity()+"')"
				+ "GROUP BY b1.TName ORDER BY count(*) desc ";
		return sql;
	}

	@Override
	public List<String> queryAllTransferStation(QueryInf q) throws DBException {
		try{
			return template.queryForList(getSql(q), String.class);
		}catch(Exception e){
			throw new DBException();
		}
	}

	@Override
	public List<String> queryUsualTransferStation(QueryInf q) throws DBException {
		try{
			return template.queryForList(getSql1(q), String.class);
		}catch(Exception e){
			throw new DBException();
		}
	}

	private String getSql1(QueryInf q) {
		return "select Transfer from TransferInf where Start='"+q.getStartCity()+"' and End='"+q.getEndCity()+"' Order by Num desc limit 0,"+q.getNum();
	}
}
