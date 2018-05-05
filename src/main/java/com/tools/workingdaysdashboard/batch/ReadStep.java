package com.tools.workingdaysdashboard.batch;

import org.joda.time.format.DateTimeFormat;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;
import org.springframework.validation.BindException;

import javax.sql.DataSource;

@Configuration
public class ReadStep {

    @Autowired
    private DataSource dataSource;

    @Bean
    public FlatFileItemReader<AttendanceRecord> fileReader(Resource in) throws Exception {
        DefaultLineMapper<AttendanceRecord> mapper =  new DefaultLineMapper<>();
        mapper.setFieldSetMapper(new AttendanceRecordMapper());
        mapper.setLineTokenizer(new DelimitedLineTokenizer("|"));
        return new FlatFileItemReaderBuilder<AttendanceRecord>()
                .name("pipes-file-reader")
                .resource(in)
                .targetType(AttendanceRecord.class)
                .linesToSkip(9)
                .encoding("utf-8")
                .lineMapper(mapper)
                .build();
    }

    @Bean
    public ItemWriter<AttendanceRecord> writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<AttendanceRecord>()
                .dataSource(dataSource)
                .sql("MERGE INTO ATTENDANCE key(employeeId,empName,date, event) values (:employeeId, :empName, :date, :event)")
                .beanMapped()
                .build();
    }

    class AttendanceRecordMapper implements FieldSetMapper<AttendanceRecord>{

        @Override
        public AttendanceRecord mapFieldSet(FieldSet fieldSet) throws BindException {
            if (fieldSet == null || fieldSet.getFieldCount() < 6) {
                return null;
            }
            AttendanceRecord record = new AttendanceRecord();
            record.setEmployeeId(fieldSet.readLong(1));
            record.setEmpName(fieldSet.readString(2));
            record.setDate(DateTimeFormat.forPattern("dd.MM.yyyy HH:mm:ss")
                    .parseDateTime(fieldSet.readString(3)+" "+fieldSet.readString(4)).toDate());
            record.setEvent(fieldSet.readString(5));
            return record;
        }
    }
}
