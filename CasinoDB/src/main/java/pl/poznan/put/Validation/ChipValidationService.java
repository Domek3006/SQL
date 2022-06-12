package pl.poznan.put.Validation;

import org.springframework.stereotype.Service;
import pl.poznan.put.Entities.Chip;

import java.util.ArrayList;

@Service
public class ChipValidationService {
    public ArrayList<String> validate(Chip chip, boolean insert){
        ArrayList<String> message = new ArrayList<>();

        if (insert) {
            if (chip.getColor().isBlank()) {
                message.add("Kolor żetonu nie może być pusty");
            } else {
                chip.setColor(chip.getColor().toUpperCase());
            }

            if (chip.getValue() == null) {
                message.add("Wartość nie może być pusta");
            } else {
                if(chip.getValue() < 0){
                    message.add("Wartość nie może być ujemna");
                }
                if(chip.getValue() % 10 != 0){
                    message.add("Wartość musi być wielokrotnością 10");
                }
            }

            if (chip.getReal_val() == null) {
                message.add("Wartość rzeczywista nie może być pusta");
            } else {
                if(chip.getReal_val() < 0){
                    message.add("Wartość rzeczywista nie może być ujemna");
                }
            }
        }
        else{
            if(chip.getValue() != null && chip.getValue() < 0){
                message.add("Wartość nie może być ujemna");
            }
            if(chip.getValue() != null && chip.getValue() % 10 != 0){
                message.add("Wartość musi być wielokrotnością 10");
            }
            if(chip.getReal_val() != null && chip.getReal_val() < 0){
                message.add("Wartość rzeczywista nie może być ujemna");
            }
        }
        return message;
    }
}
