package com.kelvin.spring.batch.utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DOBProcessor {

    private int dobMonth = 1;
    private int dobDay = 1;

    public static LocalDate dob(String dobYear){
        int dobY = Integer.parseInt(dobYear);
        LocalDate now = LocalDate.now();
        LocalDate dob = now.minusYears(dobY)
                /*.minusMonths(dobMonth)
                .minusDays(dobDay)*/;

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return dob;
    }
//
    public static void main(String [] arg){
        DOBProcessor dob = new DOBProcessor();
        System.out.println(dob.dob("29"));
    }
}
