package com.diallo.mockExam.utility;
import com.diallo.mockExam.model.Employee;
import org.springframework.stereotype.Component;
import java.util.Comparator;
import java.util.List;


@Component
public class HelperFunctions {

    public static  List<Employee> sortEmployeeByFirstName(List<Employee> list){
       return  list.stream()
                .sorted(Comparator.comparing(Employee::getFirstName)).toList();
    }
}
