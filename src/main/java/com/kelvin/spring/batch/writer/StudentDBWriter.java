package com.kelvin.spring.batch.writer;

import com.kelvin.spring.batch.model.Student;
import com.kelvin.spring.batch.repo.StudentRepository;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentDBWriter implements ItemWriter<Student> {

    @Autowired
    private StudentRepository studentRepository;

    @Override
    public void write(List<? extends Student> list) throws Exception {
        studentRepository.saveAll(list);
    }
}
