package com.org.mntr.job.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.org.mntr.entity.MenuDetails;

public class MenuDetailMapper implements FieldSetMapper<MenuDetails> {

	@Override
	public MenuDetails mapFieldSet(FieldSet fieldSet) throws BindException {
		MenuDetails menu = new MenuDetails();
		menu.setUserDefMenuId(fieldSet.readLong(0));
		menu.setMenuName(fieldSet.readString(1));
		menu.setMenuDesc(fieldSet.readString(2));
		menu.setUrl(fieldSet.readString(3));
		menu.setMenuType(fieldSet.readInt(4));
		menu.setMenuPriority(fieldSet.readInt(5));
		menu.setMenuMaster(fieldSet.readLong(6));
		menu.setStatus(fieldSet.readInt(7));
		menu.setCreatedBy(fieldSet.readLong(8));
		menu.setCreatedDt(fieldSet.readDate(9, "yyyy-MM-dd HH:mm:ss"));
		menu.setModifiedBy(fieldSet.readLong(10));
		menu.setModifiedDt(fieldSet.readDate(11, "yyyy-MM-dd HH:mm:ss"));
		menu.setIconName(fieldSet.readString(12));
		menu.setHasChild(fieldSet.readInt(13));
		return menu;
	}

}
