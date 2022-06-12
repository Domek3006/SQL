package pl.poznan.put.Validation;

import org.springframework.stereotype.Service;
import pl.poznan.put.Entities.Casino;

import java.util.ArrayList;

@Service
public class CasinoValidationService {
    public ArrayList<String> validate(Casino casino, boolean insert){
        ArrayList<String> message = new ArrayList<>();

        if (insert) {
            if (casino.getName().isBlank()) {
                message.add("Nazwa kasyna nie może być pusta");
            } else {
                casino.setName(casino.getName().toUpperCase());
            }

            if (casino.getCity().isBlank()) {
                message.add("Nazwa miasta nie może być pusta");
            } else {
                casino.setCity(casino.getCity().toUpperCase());
                if (!casino.getCity().matches("[A-Z]+")) {
                    message.add("Nazwa miasta może zawierać jedynie litery");
                }
            }

            if (casino.getSeats() == null) {
                message.add("Liczba miejsc nie może być pusta");
            } else {
                if (casino.getSeats() < 1) {
                    message.add("Kasyno musi mieć przynajmniej jedno miejsce");
                }
            }
        }
        else{
            if (!casino.getName().isBlank()) {
                casino.setName(casino.getName().toUpperCase());
            }

            if (!casino.getCity().isBlank()) {
                casino.setCity(casino.getCity().toUpperCase());
                if (!casino.getCity().matches("[A-Z]+")) {
                    message.add("Nazwa miasta może zawierać jedynie litery");
                }
            }

            if (!(casino.getSeats() == null)) {
                if (casino.getSeats() < 1) {
                    message.add("Kasyno musi mieć przynajmniej jedno miejsce");
                }
            }
        }
        return message;
    }
}
