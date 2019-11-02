package com.kelvin.spring.batch.processor;

import com.kelvin.spring.batch.dto.StudentDTO;
import com.kelvin.spring.batch.model.Student;
import com.kelvin.spring.batch.utils.DOBProcessor;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

@Component
public class StudentProcessor implements ItemProcessor<StudentDTO, Student> {
    @Override
    public Student process(StudentDTO studentDTO) throws Exception {
        Student student = new Student();
        student.setStudentId(studentDTO.getStudentId());
        student.setFirstName(studentDTO.getFirstName());
        student.setLastName(studentDTO.getLastName());
        student.setEmail(studentDTO.getEmail());
        student.setDob(DOBProcessor.dob(studentDTO.getAge()));
        return student;
    }
}
