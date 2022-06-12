package pl.poznan.put.Validation;

import org.springframework.stereotype.Service;
import pl.poznan.put.Entities.Employee;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class EmployeeValidationService {
    public ArrayList<String> validate(Employee employee, boolean insert){
        ArrayList<String> message = new ArrayList<>();
        if(insert){
            if(employee.getName().isBlank()){
                message.add("Nazwisko nie może być puste");
            }
            else {
                employee.setName(employee.getName().toUpperCase());
                if(!employee.getName().matches("[A-Z]+")){
                    message.add("Nazwisko musi zawierać tylko litery");
                }
            }
            if(employee.getSalary() < 2800){
                message.add("Płaca musi być większa niż 2800 zł");
            }
            if(employee.getEnds() == null || employee.getEnds().before(Date.valueOf(LocalDate.now()))) {
                message.add("Data końca umowy musi być późniejsza niż " + LocalDate.now().toString());
            }
        }
        return message;
    }
}
