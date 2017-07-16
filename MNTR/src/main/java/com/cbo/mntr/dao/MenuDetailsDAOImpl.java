package com.cbo.mntr.dao;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.cbo.mntr.dao.common.AbstractDAO;
import com.cbo.mntr.entity.MenuDetails;

@Repository("menuDetailsDAO")
public class MenuDetailsDAOImpl extends AbstractDAO<Serializable, MenuDetails> implements MenuDetailsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDetails> getAllMenuDetails() throws Exception {
		return createEntityCriteria().list();
	}

}
