package pl.poznan.put.Validation;

import org.springframework.stereotype.Service;
import pl.poznan.put.Entities.Guest;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class GuestValidationService {
    public ArrayList<String> validate(Guest guest, boolean insert){
        ArrayList<String> message = new ArrayList<>();
        if(insert){
            if(guest.getName().isBlank()){
                message.add("Nazwa nie może być pusta");
            }
            else{
                guest.setName(guest.getName().toUpperCase());
            }
            if(guest.getReserved() == null || guest.getReserved().before(Date.valueOf(LocalDate.now()))){
                message.add("Data końca umowy musi być późniejsza niż " + LocalDate.now().toString());
            }
        }
        return message;
    }
}
