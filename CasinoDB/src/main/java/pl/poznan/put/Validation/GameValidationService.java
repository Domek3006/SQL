package pl.poznan.put.Validation;

import org.springframework.stereotype.Service;
import pl.poznan.put.Entities.Employee;
import pl.poznan.put.Entities.Game;

import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;

@Service
public class GameValidationService {
    public ArrayList<String> validate(Game game, boolean insert){
        ArrayList<String> message = new ArrayList<>();
        if(insert){
            if(game.getName().isBlank()){
                message.add("Nazwa nie może być pusta");
            }
            else {
                game.setName(game.getName().toUpperCase());
            }
        }
        return message;
    }
}
