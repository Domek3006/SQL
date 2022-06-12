package pl.poznan.put.Validation;

import org.springframework.stereotype.Service;
import pl.poznan.put.Entities.Partner;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class PartnerValidationService {
    public ArrayList<String> validate(Partner partner, boolean insert){
        ArrayList<String> message = new ArrayList<>();
        if(insert){
            if(partner.getName().isBlank()){
                message.add("Nazwa nie może być pusta");
            }
            else{
                partner.setName(partner.getName().toUpperCase());
            }
            if(partner.getService().isBlank()){
                message.add("Usługa nie może być pusta");
            }
            else{
                partner.setService(partner.getService().toUpperCase());
            }
            if(partner.getCost() < 0){
                message.add("Koszt nie może być ujemny");
            }
            if(partner.getEnds() == null || partner.getEnds().before(Date.valueOf(LocalDate.now()))){
                message.add("Data końca umowy musi być późniejsza niż " + LocalDate.now().toString());
            }

        }
        return message;
    }
}
