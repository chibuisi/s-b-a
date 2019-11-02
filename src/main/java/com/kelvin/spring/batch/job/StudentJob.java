package com.kelvin.spring.batch.job;

import com.kelvin.spring.batch.dto.StudentDTO;
import com.kelvin.spring.batch.mapper.StudentFileRowMapper;
import com.kelvin.spring.batch.model.Student;
import com.kelvin.spring.batch.processor.StudentProcessor;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;

import javax.sql.DataSource;

@Configuration
public class StudentJob {
    private JobBuilderFactory jobBuilderFactory;
    private StepBuilderFactory stepBuilderFactory;
    private DataSource dataSource;
    private StudentProcessor studentProcessor;

    @Autowired
    public StudentJob(JobBuilderFactory jobBuilderFactory, StepBuilderFactory stepBuilderFactory, DataSource dataSource, StudentProcessor studentProcessor) {
        this.jobBuilderFactory = jobBuilderFactory;
        this.stepBuilderFactory = stepBuilderFactory;
        this.dataSource = dataSource;
        this.studentProcessor = studentProcessor;
    }

    @Qualifier(value = "job")
    @Bean
    public Job job() throws Exception {
        return this.jobBuilderFactory.get("job")
                .start(step())
                .build();
    }

    @Bean
    public Step step() throws Exception{
        return this.stepBuilderFactory.get("step1")
                .<StudentDTO,Student>chunk(5)
                .reader(studentReader())
                .processor(studentProcessor)
                .writer(studentDBWriterDefault())
                .build();
    }

    @Bean
    public JdbcBatchItemWriter<Student> studentDBWriterDefault() {
        JdbcBatchItemWriter<Student> itemWriter = new JdbcBatchItemWriter<Student>();
        itemWriter.setDataSource(dataSource);
        itemWriter.setSql("insert into student (student_id, first_name, last_name, email, dob) values (:studentId, :firstName, :lastName, :email, :dob)");
        itemWriter.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Student>());
        return itemWriter;
    }

    @Bean
    @StepScope
    public FlatFileItemReader<StudentDTO> studentReader() throws Exception {
        FlatFileItemReader<StudentDTO> reader = new FlatFileItemReader<>();
        reader.setResource(inputFileRes(null));
        reader.setLineMapper(new DefaultLineMapper<StudentDTO>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("studentId", "firstName", "lastName", "email", "age");
                setDelimiter(",");
            }});
            setFieldSetMapper(new StudentFileRowMapper());
        }});
        return reader;
    }

    @Bean
    @StepScope
    Resource inputFileRes(@Value("#{jobParameters[fileName]}") final String fileName) throws Exception {
        return new ClassPathResource(fileName);
    }
}
