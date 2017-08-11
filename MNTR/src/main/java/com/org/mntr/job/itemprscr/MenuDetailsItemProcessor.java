package com.org.mntr.job.itemprscr;

import org.apache.log4j.Logger;
import org.springframework.batch.item.ItemProcessor;

import com.org.mntr.entity.MenuDetails;

public class MenuDetailsItemProcessor implements ItemProcessor<MenuDetails, MenuDetails> {
	private static final Logger logger = Logger.getLogger(MenuDetailsItemProcessor.class);

	@Override
	public MenuDetails process(MenuDetails menuDetails) throws Exception {
		try {
			logger.info("Inside [MenuDetailsItemProcessor][process]");

			if (menuDetails.getUserDefMenuId() == 0)
				return null;

			if (menuDetails.getMenuName() == null)
				return null;

			if (menuDetails.getMenuDesc() == null)
				return null;

			if (menuDetails.getUrl() == null)
				return null;

			if (menuDetails.getIconName() == null)
				return null;

			if (menuDetails.getStatus() == 0)
				return null;

			if (menuDetails.getCreatedBy() == 0)
				return null;

			if (menuDetails.getModifiedBy() == 0)
				return null;

			if (menuDetails.getCreatedDt() == null)
				return null;

			if (menuDetails.getModifiedDt() == null)
				return null;

			return menuDetails;

		} catch (Exception ex) {
			logger.error("ITEMPRSCR ERROR: " + ex);
		}
		return null;
	}

}
