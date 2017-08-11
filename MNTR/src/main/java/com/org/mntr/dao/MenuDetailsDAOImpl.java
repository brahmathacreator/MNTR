package com.org.mntr.dao;

import java.io.Serializable;
import java.util.List;
import org.springframework.stereotype.Repository;
import com.org.mntr.dao.common.EntityManagerAbstractDAO;
import com.org.mntr.entity.MenuDetails;

@Repository("menuDetailsDAO")
public class MenuDetailsDAOImpl extends EntityManagerAbstractDAO<Serializable, MenuDetails> implements MenuDetailsDAO {

	@SuppressWarnings("unchecked")
	@Override
	public List<MenuDetails> getAllMenuDetails() throws Exception {
		return (List<MenuDetails>) createEntityCriteria().list();
	}

}
