package com.cbo.mntr.dao;

import java.io.Serializable;

import org.springframework.stereotype.Repository;

import com.cbo.mntr.dao.common.AbstractDAO;
import com.cbo.mntr.entity.MailDetails;

@Repository("mailDetailsDAO")
public class MailDetailsDAOImpl extends AbstractDAO<Serializable, MailDetails> implements MailDetailsDAO {

	@Override
	public void saveMailDetails(MailDetails mailDetails) throws Exception {
		save(mailDetails);
	}

}
