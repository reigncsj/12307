package com.trainticket.dao.daoImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.trainticket.dao.ReigsterDao;

@Repository("reigsterDao")
public class ReigsterDaoImpl implements ReigsterDao{
	@Autowired
    @Qualifier("jdbcTemplate2")
    private JdbcTemplate template;
}
