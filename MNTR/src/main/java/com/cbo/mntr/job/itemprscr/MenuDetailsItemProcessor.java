package com.cbo.mntr.job.itemprscr;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.cbo.mntr.entity.MenuDetails;

public class MenuDetailsItemProcessor implements ItemProcessor<MenuDetails, MenuDetails> {
	private static final Logger logger = Logger.getLogger(MenuDetailsItemProcessor.class);

	@Override
	public MenuDetails process(MenuDetails menuDetails) throws Exception {
		try {
			logger.info("Inside [MenuDetailsItemProcessor][process]");

			if (menuDetails.getMenuName() == null || menuDetails.getMenuName().trim().isEmpty())
				return null;

			if (menuDetails.getMenuDesc() == null || menuDetails.getMenuDesc().trim().isEmpty())
				return null;

			if (menuDetails.getMenuURL() == null || menuDetails.getMenuURL().trim().isEmpty())
				return null;

			if (menuDetails.getMenuType() == null)
				return null;

			if (menuDetails.getMenuPriority() == null)
				return null;

			if (menuDetails.getMenuMaster() == null)
				return null;

			if (menuDetails.getStatus() == null)
				return null;

			if (menuDetails.getCreatedBy() == null)
				return null;

			if (menuDetails.getModifiedBy() == null)
				return null;

			if (menuDetails.getCreatedDT() == null)
				return null;

			if (menuDetails.getModifiedDT() == null)
				return null;

			return menuDetails;

		} catch (Exception ex) {
			logger.error("ITEMPRSCR ERROR: " + ex);
		}
		return null;
	}

}