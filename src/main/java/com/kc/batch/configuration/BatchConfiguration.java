package com.kc.batch.configuration;

import java.net.MalformedURLException;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.JdbcPagingItemReader;
import org.springframework.batch.item.database.PagingQueryProvider;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.database.support.SqlPagingQueryProviderFactoryBean;
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
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.core.task.TaskExecutor;
import org.springframework.jdbc.core.JdbcTemplate;

import com.kc.batch.dao.entity.Hotel;
import com.kc.batch.dao.entity.Validator;
import com.kc.batch.dest.dao.repository.IHotelRepository;
import com.kc.batch.source.dao.repository.HotelImpl;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration  {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    public IHotelRepository repo;

    @Bean(name = "jdbcTemplate1")
    public JdbcTemplate jdbcTemplate1(DataSource ds) {
     return new JdbcTemplate(ds);
    }

    @Autowired
    public HotelImpl impl;
    
    @Bean 
    public JdbcCursorItemReader<Hotel> read(DataSource dataSource){
    	
    	return new JdbcCursorItemReaderBuilder<Hotel>()
    			.dataSource(dataSource)
    			.name("hotelItemReader")
    			.sql("select * from hotel")
    			.rowMapper(new SourceRowMapper())
    			.build();
    	
    	
    }
    
    private static final int rows=100;
    
    
    @Bean
	public Validator validator() {
		return new Validator();
	}
    
    @Bean
	public HotelProcessor hotelProcessor() {
		return new HotelProcessor();
	}
    
    @Bean
	public JdbcPagingItemReader<Hotel> pagingReader(DataSource dataSource) {
    	JdbcPagingItemReader<Hotel> reader = new JdbcPagingItemReader<>();
		reader.setDataSource(dataSource);
		reader.setFetchSize(rows);
		reader.setPageSize(rows);
		reader.setQueryProvider(queryProvider(dataSource));
		reader.setRowMapper(new SourceRowMapper());
		//reader.setPageProcessor(hotelProcessor());
		return reader;
	}
    
    
    private PagingQueryProvider queryProvider(DataSource dataSource) {
		SqlPagingQueryProviderFactoryBean bean = new SqlPagingQueryProviderFactoryBean();
		bean.setDataSource(dataSource);
		bean.setSelectClause("select id,hotel_id,address");
		bean.setFromClause("from hotel");
		bean.setSortKey("hotel_id");
		try {
			return bean.getObject();
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
    
    
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
                .listener(listener())
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
                .names(new String[]{"hotelId"})
                .build();
    }
    
    
    @Bean
	public Aggregator writer() {
		return new Aggregator();
	}
    
    @Bean
	public JobExecutionListener listener() {
		return new JobCompletionNotificationListener();
	}
    
    @Bean
	public TaskExecutor taskExecutor(){
	    SimpleAsyncTaskExecutor asyncTaskExecutor=new SimpleAsyncTaskExecutor("spring_batch");
	    asyncTaskExecutor.setConcurrencyLimit(5);
	    return asyncTaskExecutor;
	}

    
    /*@Bean
	ItemWriter<Hotel> writer() {
		return new FlatFileItemWriterBuilder<Hotel>().
				name("OrderWriter").
				lineAggregator(new PassThroughLineAggregator<>()).
				resource(outputResource).build();
	}*/
    
    @Bean
    public Step step1(DataSource dataSource) {
    	
    	
    	/*for(int i=500000;i<500500;i++) {
    		Hotel hot = new Hotel();
    		hot.setHotel_id(i);
    		hot.setId(i);
    		hot.setAddress("My Home Address");
    		hot.setFree_breakfast(i%2==0);
    		hot.setName("Random "+i);
    		hot.setType("hotel");
    		impl.testCreate(hot);
    		repo.save(hot);
    	}
    	
    	System.out.println("Done ..");*/
    	
    	
    	try {
			return stepBuilderFactory.get("step1")
			        .<Hotel, Hotel> chunk(rows)
			        .reader(pagingReader(dataSource))
			        //.reader(jsonItemReader())
			        //.reader(read(dataSource))
			        //.processor(processor())
			        .writer(writer())
			        .taskExecutor(taskExecutor())
			        .build();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        
        return null;
    }
}
