package com.cbo.mntr.job.listener;

import java.util.List;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

public class MenuDetailsJobListener implements JobExecutionListener {

	private static final Logger logger = Logger.getLogger(MenuDetailsJobListener.class);
	private DateTime start, end;

	@Override
	public void afterJob(JobExecution jobExecution) {
		logger.info("Inside [MenuDetailsJobListener][afterJob]");
		end = new DateTime();
		logger.info("Menu Details Batch Process Finished At :" + end);
		logger.info("Total time take in millis :" + getTimeInMillis(start, end));
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			logger.info("Menu Details Batch Process job completed successfully");
		} else if (jobExecution.getStatus() == BatchStatus.FAILED) {
			logger.info("Menu Details Batch Process job failed with following exceptions ");
			List<Throwable> exceptionList = jobExecution.getAllFailureExceptions();
			for (Throwable th : exceptionList) {
				logger.info("Menu Details Batch Process Error : " + th.getLocalizedMessage());
			}
		}
	}

	@Override
	public void beforeJob(JobExecution arg0) {
		logger.info("Inside [MenuDetailsJobListener][beforeJob]");
		start = new DateTime();
		logger.info("Menu Details Batch Process Starts At : " + start);
	}

	private long getTimeInMillis(DateTime start, DateTime stop) {
		return stop.getMillis() - start.getMillis();
	}

}
