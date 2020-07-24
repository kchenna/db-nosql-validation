package com.kc.batch.configuration;

import java.io.BufferedWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;

import com.kc.batch.dao.entity.Hotel;
import com.kc.batch.dao.entity.Validator;

public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	@Autowired
	private Validator validator;
	
	@Override
	public void afterJob(JobExecution jobExecution) {
		if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
			Path path = Paths.get("/Users/amala/Documents/Kamal/mismatch.csv");
			System.out.println("path" + path.getFileName());
			
			try (BufferedWriter fileWriter = Files.newBufferedWriter(path)) {
				fileWriter.newLine();
				for (Hotel pd : validator.getValidator()) {
					System.out.println(" hotel " + pd.getHotelId());
							fileWriter.write(new StringBuilder().append(pd.getHotelId()).toString());
					fileWriter.newLine();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
}
