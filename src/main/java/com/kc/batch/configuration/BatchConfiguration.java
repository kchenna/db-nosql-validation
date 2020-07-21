package com.kc.batch.configuration;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.database.builder.JdbcCursorItemReaderBuilder;
import org.springframework.batch.item.file.FlatFileItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemWriterBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.kc.batch.dao.entity.Hotel;
import com.kc.batch.dao.entity.Voltage;
import com.kc.batch.dao.repository.IHotelRepository;
import com.kc.batch.dao.repository.IVoltageRepository;


@Configuration
@EnableBatchProcessing
public class BatchConfiguration  {

    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    
    @Autowired
    public IVoltageRepository repo;
    
    
    @Autowired
    public IHotelRepository hrepo;

    @Bean 
    public JdbcCursorItemReader<Voltage> read(final DataSource dataSource){
    	
    	return new JdbcCursorItemReaderBuilder<Voltage>()
    			.dataSource(dataSource)
    			.name("voltItemReader")
    			.sql("select volt,time from voltage")
    			.rowMapper(new VoltageRowMapper())
    			.build();
    }
    
    
    
    /*@Bean
    public FlatFileItemReader<Voltage> reader() {
        return new FlatFileItemReaderBuilder<Voltage>()
                .name("voltItemReader")
                .resource(new ClassPathResource("Volts.csv"))
                .delimited()
                .names(new String[]{"volt", "time"})
                .lineMapper(lineMapper())
                .fieldSetMapper(new BeanWrapperFieldSetMapper<Voltage>() {{
                    setTargetType(Voltage.class);
                }})
                .build();
    }*/
    
    
    
    @Bean
    public LineMapper<Voltage> lineMapper() {

        final DefaultLineMapper<Voltage> defaultLineMapper = new DefaultLineMapper<>();
        final DelimitedLineTokenizer lineTokenizer = new DelimitedLineTokenizer();
        lineTokenizer.setDelimiter(";");
        lineTokenizer.setStrict(false);
        lineTokenizer.setNames(new String[] {"volt","time"});

        final VoltageFieldSetMapper fieldSetMapper = new VoltageFieldSetMapper();
        defaultLineMapper.setLineTokenizer(lineTokenizer);
        defaultLineMapper.setFieldSetMapper(fieldSetMapper);

        return defaultLineMapper;
    }

    @Bean
    public VoltageProcessor processor() {
        return new VoltageProcessor();
    }

    @Bean
    public JdbcBatchItemWriter<Voltage> write(final DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Voltage>()
                .itemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<>())
                .sql("INSERT INTO voltage1 (volt, time) VALUES (:volt, :time)")
                .dataSource(dataSource)
                .build();
    }
    
    
    
    @Bean
    public Job importVoltageJob(NotificationListener listener, Step step1) {
        return jobBuilderFactory.get("importVoltageJob")
                .incrementer(new RunIdIncrementer())
                .listener(listener)
                .flow(step1)
                .end()
                .build();
    }
    
    Resource outputResource = new FileSystemResource("/Users/amala/Documents/Kamal/batch-processing-large-datasets-spring/src/main/resources/Volts1.csv");
   
    @Bean
    public FlatFileItemWriter<Voltage> fWriter() {
        return new FlatFileItemWriterBuilder<Voltage>()
                .name("voltItemWriter")
                .resource(outputResource)
                .delimited()
                .names(new String[]{"volt", "time"})
                .build();
    }

    @Bean
    public Step step1(FlatFileItemWriter<Voltage> writer,DataSource dataSource) {
    	
    	
    	Voltage v = new Voltage();
    	v.setTime(4434344);
    	v.setVolt(new BigDecimal(10));
    	repo.save(v);
    	
    	
    	ObjectMapper mapper = new ObjectMapper();
    	String s;
		try {
			s = mapper.writeValueAsString(hrepo.findById("hotel_10025").get());
			String test = "{\"address\":\"Capstone Road, ME7 3JE\",\"alias\":null,\"checkin\":null,\"checkout\":null,\"city\":\"Medway\",\"country\":\"United Kingdom\",\"description\":\"40 bed summer hostel about 3 miles from Gillingham, housed in a districtive converted Oast House in a semi-rural setting.\",\"directions\":null,\"email\":null,\"fax\":null,\"free_breakfast\":true,\"free_internet\":false,\"free_parking\":true,\"id\":\"hotel_10025\",\"name\":\"Medway Youth Hostel\",\"pets_ok\":true,\"phone\":\"+44 870 770 5964\",\"public_likes\":[\"Julius Tromp I\",\"Corrine Hilll\",\"Jaeden McKenzie\",\"Vallie Ryan\",\"Brian Kilback\",\"Lilian McLaughlin\",\"Ms. Moses Feeney\",\"Elnora Trantow\"],\"state\":null,\"title\":\"Gillingham (Kent)\",\"tollfree\":null,\"type\":\"hotel\",\"url\":\"http://www.yha.org.uk\",\"vacancy\":true}";
			
			//TODO 
	    	// Couch JSON Object
		    	try {
		    		System.out.println("validation "+mapper.readTree(s).equals(mapper.readTree(test)));
		    		} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	    	} catch (JsonProcessingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	
    	
        return stepBuilderFactory.get("step1")
                .<Voltage, Voltage> chunk(10)
                .reader(read(dataSource))
                .processor(processor())
                .writer(writer)
                .build();
    }
}
