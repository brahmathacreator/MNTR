package com.cbo.mntr.job.mapper;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.cbo.mntr.entity.MenuDetails;

public class MenuDetailMapper implements FieldSetMapper<MenuDetails> {

	@Override
	public MenuDetails mapFieldSet(FieldSet fieldSet) throws BindException {
		MenuDetails menu = new MenuDetails();
		menu.setMenuName(fieldSet.readString(0));
		menu.setMenuDesc(fieldSet.readString(1));
		menu.setMenuURL(fieldSet.readString(2));
		menu.setMenuType(fieldSet.readInt(3));
		menu.setMenuPriority(fieldSet.readInt(4));
		menu.setMenuMaster(fieldSet.readInt(5));
		menu.setStatus(fieldSet.readInt(6));
		menu.setCreatedBy(fieldSet.readLong(7));
		menu.setCreatedDT(fieldSet.readDate(8, "yyyy-MM-dd HH:mm:ss"));
		menu.setModifiedBy(fieldSet.readLong(9));
		menu.setModifiedDT(fieldSet.readDate(10, "yyyy-MM-dd HH:mm:ss"));
		return menu;
	}

}
