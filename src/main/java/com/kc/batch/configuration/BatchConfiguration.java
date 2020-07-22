package com.kc.batch.configuration;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.json.JacksonJsonObjectReader;
import org.springframework.batch.item.json.JsonItemReader;
import org.springframework.batch.item.json.builder.JsonItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.kc.batch.dao.entity.Hotel;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration  {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    

    /*@Bean 
    public JdbcCursorItemReader<Voltage> read(final DataSource dataSource){
    	
    	return new JdbcCursorItemReaderBuilder<Voltage>()
    			.dataSource(dataSource)
    			.name("voltItemReader")
    			.sql("select volt,time from voltage")
    			.rowMapper(new SourceRowMapper())
    			.build();
    }*/
    
    Resource sourceResource = new FileSystemResource("/Users/amala/Documents/Kamal/batch-processing-large-datasets-spring/src/main/resources/source.json");

    @Bean
    public JsonItemReader<Hotel> jsonItemReader() throws MalformedURLException {
        return new JsonItemReaderBuilder<Hotel>()
                .jsonObjectReader(new JacksonJsonObjectReader<Hotel>(Hotel.class))
                .resource(sourceResource)
                .name("hotelJsonItemReader")
                .build();
    }
    
 
    @Bean
    public FilterProcessor processor() {
        return new FilterProcessor();
    }


    @Bean
    public Job mismatchJob(NotificationListener listener, Step step1) {
        return jobBuilderFactory.get("FoundMisMatchJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
    
    Resource outputResource = new FileSystemResource("/Users/amala/Documents/Kamal/batch-processing-large-datasets-spring/src/main/resources/mismatch.csv");
   
    @Bean
    public FlatFileItemWriter<Hotel> fWriter() {
        return new FlatFileItemWriterBuilder<Hotel>()
                .name("MisMatchItemWriter")
                .resource(outputResource)
                .delimited()
                .delimiter(",")
                .names(new String[]{"id", "name"})
                .build();
    }
    
    

    @Bean
    public Step step1(FlatFileItemWriter<Hotel> writer,DataSource dataSource) {
    	
        try {
			return stepBuilderFactory.get("step1")
			        .<Hotel, Hotel> chunk(10)
			        .reader(jsonItemReader())
			        .processor(processor())
			        .writer(writer)
			        .build();
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
}
