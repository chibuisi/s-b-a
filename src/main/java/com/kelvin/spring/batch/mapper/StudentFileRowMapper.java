package com.kelvin.spring.batch.mapper;

import com.kelvin.spring.batch.dto.StudentDTO;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

public class StudentFileRowMapper implements FieldSetMapper<StudentDTO> {
    @Override
    public StudentDTO mapFieldSet(FieldSet fieldSet) throws BindException {
        StudentDTO studentDTO = new StudentDTO();
        studentDTO.setStudentId(fieldSet.readString("studentId"));
        studentDTO.setFirstName(fieldSet.readString("firstName"));
        studentDTO.setLastName(fieldSet.readString("lastName"));
        studentDTO.setEmail(fieldSet.readString("email"));
        studentDTO.setAge(fieldSet.readString("age"));
//        try{
//            studentDTO.setAge(fieldSet.readInt("age"));
//        }
//        catch (Exception e){
//            System.out.println(e.getMessage());
//        }
        return studentDTO;
    }
}
