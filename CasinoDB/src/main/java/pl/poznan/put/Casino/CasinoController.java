package pl.poznan.put.Casino;

import org.hibernate.query.criteria.internal.expression.function.CurrentDateFunction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pl.poznan.put.Entities.*;
import pl.poznan.put.Repositories.*;
import pl.poznan.put.Validation.*;

import javax.persistence.criteria.CriteriaBuilder;
import javax.validation.Valid;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;


@SuppressWarnings("SpringJavaInjectionPointsAutowiringInspection")
@Controller
public class CasinoController {

    private Long toUpdate;
    private String searchName;

    @Autowired
    CasinoRepository casinoRepository;

    @Autowired
    GameRepository gameRepository;

    @Autowired
    RestaurantRepository restaurantRepository;

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    EnforcerRepository enforcerRepository;

    @Autowired
    ChipRepository chipRepository;

    @Autowired
    DishRepository dishRepository;

    @Autowired
    BarRepository barRepository;

    @Autowired
    DrinkRepository drinkRepository;

    @Autowired
    DebtorRepository debtorRepository;

    @Autowired
    GuestRepository guestRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    ReviewRepository reviewRepository;

    @Autowired
    ChipCasRepository chipCasRepository;

    @Autowired
    CasinoValidationService casinoValidationService;

    @Autowired
    ChipValidationService chipValidationService;

    @Autowired
    EmployeeValidationService employeeValidationService;

    @Autowired
    GameValidationService gameValidationService;

    @Autowired
    PartnerValidationService partnerValidationService;

    @Autowired
    GuestValidationService guestValidationService;

    @RequestMapping("/")
    public String index() {
        return "redirect:/casinos";
    }

    @RequestMapping("/casinos")
    public String casinos(Model model) {
        /*List<Double> income = new ArrayList<>();
        List<Casino> tmp = casinoRepository.findIds();
        for(Casino c : tmp){
            income.add(casinoRepository.income(c.getId_cas()));
        }*/
        //model.addAttribute("income", income);
        model.addAttribute("casinos", casinoRepository.findAll());
        return "casinos";
    }

    @RequestMapping("/games")
    public String games(Model model){
        model.addAttribute("games", gameRepository.findGames());
        return "games";
    }

    @RequestMapping("/restaurants")
    public String restaurants(Model model){
        model.addAttribute("restaurants", restaurantRepository.findRestaurants());
        return "restaurants";
    }

    @RequestMapping("/employees")
    public String employees(Model model){
        model.addAttribute("employees", employeeRepository.findEmployees());
        return "employees";
    }

    @RequestMapping("/chips")
    public String chips(Model model){
        model.addAttribute("chips", chipRepository.findAll());
        return "chips";
    }

    @RequestMapping("/dishes")
    public String dishes(@RequestParam("rest") Long rest, @RequestParam("cas") Long cas, Model model){
        model.addAttribute("dishes", dishRepository.findDishes(cas, rest));
        model.addAttribute("rest", rest);
        model.addAttribute("cas", cas);
        return "dishes";
    }

    @RequestMapping("/bars")
    public String bars(Model model){
        List<Object[]> tmp = barRepository.findBars();
        model.addAttribute("bars", tmp);
        return "bars";
    }

    @RequestMapping("/drinks")
    public String drinks(@RequestParam("rest") Long rest, @RequestParam("cas") Long cas, Model model){
        model.addAttribute("drinks", drinkRepository.findDrinks(cas, rest));
        model.addAttribute("rest", rest);
        model.addAttribute("cas", cas);
        return "drinks";
    }

    @RequestMapping("/debtors")
    public String debtors(Model model){
        model.addAttribute("debtors", debtorRepository.findDebtors());
        return "debtors";
    }

    @RequestMapping("/guests")
    public String guests(Model model){
        model.addAttribute("guests", guestRepository.findAll());
        model.addAttribute("guest", new Guest());
        model.addAttribute("casinos", casinoRepository.findAll());
        return "guests";
    }

    @RequestMapping("/partners")
    public String partners(Model model){
        model.addAttribute("partners", partnerRepository.findPartners());
        return "partners";
    }

    @RequestMapping("/reviews")
    public String reviews(Model model){
        model.addAttribute("reviews", reviewRepository.findReviews());
        return "reviews";
    }

    //@RequestParam String type, @ModelAttribute("casino") , Model model
    @RequestMapping(value = "/insert")
    public String insert(@RequestParam("type") String type, Model model){
        switch (type){
            case "Casino":
                model.addAttribute("casino", new Casino());
                break;
            case "Bar":
                model.addAttribute("casinos", casinoRepository.findAll());
                model.addAttribute("bar", new Bar());
                break;
            case "Chip":
                model.addAttribute("casinos", casinoRepository.findAll());
                model.addAttribute("chip", new Chip());
                break;
            case "Employee":
                model.addAttribute("employee", new Employee());
                model.addAttribute("casinos", casinoRepository.findAll());
                break;
            case "Game":
                model.addAttribute("game", new Game());
                model.addAttribute("casinos", casinoRepository.findAll());
                break;
            case "Guest":
                model.addAttribute("guest", new Guest());
                model.addAttribute("casinos", casinoRepository.findAll());
                break;
            case "Partner":
                model.addAttribute("partner", new Partner());
                model.addAttribute("casinos", casinoRepository.findAll());
                break;
            case "Review":
                model.addAttribute("review", new Review());
                model.addAttribute("guests", reviewRepository.findValid());
                break;
            case "Restaurant":
                model.addAttribute("restaurant", new Restaurant());
                model.addAttribute("casinos", casinoRepository.findAll());
                break;
        }
        return "inserts/insert" + type;
    }

    @RequestMapping("/inserts/insertDish")
    public String insertDish(@RequestParam("rest") Long rest, @RequestParam("cas") Long cas, Model model){
        model.addAttribute("dish", new Dish());
        model.addAttribute("rest", rest);
        model.addAttribute("cas", cas);
        return "inserts/insertDish";
    }

    @RequestMapping("/inserts/insertDrink")
    public String insertDrink(@RequestParam("rest") Long rest, @RequestParam("cas") Long cas, Model model){
        model.addAttribute("drink", new Drink());
        model.addAttribute("rest", rest);
        model.addAttribute("cas", cas);
        return "inserts/insertDrink";
    }

    @RequestMapping("/delete")
    public String delete(@RequestParam("id") Long id, @RequestParam("type") String type){
        switch (type){
            case "casinos":
                chipCasRepository.deleteWithCasino(id);
                casinoRepository.deleteById(id);
                break;
            case "bars":
                barRepository.deleteById(id);
                break;
            case "chips":
                chipCasRepository.deleteWithChip(id);
                chipRepository.deleteById(id);
                break;
            case "debtors":
                debtorRepository.deleteById(id);
                break;
            case "employees":
                employeeRepository.deleteById(id);
                break;
            case "enforcers":
                enforcerRepository.deleteById(id);
                break;
            case "games":
                gameRepository.deleteById(id);
                break;
            case "guests":
                guestRepository.deleteById(id);
                break;
            case "partners":
                partnerRepository.deleteById(id);
                break;
            case "reviews":
                reviewRepository.deleteById(id);
                break;
            case "restaurants":
                restaurantRepository.deleteById(id);
                break;
        }
        return "redirect:/" + type;
    }

    @RequestMapping("/deleteDrIsh")
    public String delete(@RequestParam("name") String name, @RequestParam("bar") long bar, @RequestParam("cas") long cas, @RequestParam("type") String type){
        if (type == "drinks") {
            drinkRepository.deleteById(new Drink(name, bar, cas));
        }
        else{
            dishRepository.deleteById(new DishId(name, bar, cas));
        }
        return "redirect:/" + type;
    }

    @RequestMapping(value = "/insCas", method = RequestMethod.POST)
    public String insCas(@Valid @ModelAttribute("casino") Casino casino, BindingResult result){
        ArrayList<String> errs = casinoValidationService.validate(casino, true);
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            return "inserts/insertCasino";
        }
        casinoRepository.addCasino(casino.getName(), casino.getCity(), casino.getSeats());
        return "redirect:/casinos";
    }

    @RequestMapping("/insBar")
    public String insBar(@Valid @ModelAttribute("bar") Bar bar, BindingResult result, Model model){
        if(bar.getName().isBlank()){
            ObjectError error = new ObjectError("globalError", "Nazwa nie może być pusta");
            result.addError(error);
        }
        if (result.hasErrors()){
            model.addAttribute("casinos", casinoRepository.findAll());
            return "inserts/insertBar";
        }
        barRepository.addBar(bar.getName(), bar.getId_cas());
        return "redirect:/bars";
    }

    @RequestMapping(value = "/insChip", method = RequestMethod.POST)
    public String insChip(@Valid @ModelAttribute("chip") Chip chip, @RequestParam("id") Long id, BindingResult result, Model model){
        ArrayList<String> errs = chipValidationService.validate(chip, true);
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("casinos", casinoRepository.findAll());
            return "inserts/insertChip";
        }
        chipRepository.addChip(chip.getValue(), chip.getColor(), chip.getSize(), chip.getReal_val(), id);
        return "redirect:/chips";
    }

    @RequestMapping(value = "/insEmp", method = RequestMethod.POST)
    public String insEmp(@Valid @ModelAttribute("employee") Employee employee, BindingResult result, Model model){
        ArrayList<String> errs = employeeValidationService.validate(employee, true);
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("casinos", casinoRepository.findAll());
            return "inserts/insertEmployee";
        }
        employeeRepository.addEmployee(employee.getName(), employee.getId_cas(), employee.getFunction(), employee.getEnds(), employee.getSalary());
        return "redirect:/employees";
    }

    @RequestMapping(value = "/insGame", method = RequestMethod.POST)
    public String insGame(@Valid @ModelAttribute("game") Game game, BindingResult result, Model model){
        ArrayList<String> errs = gameValidationService.validate(game, true);
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("casinos", casinoRepository.findAll());
            return "inserts/insertGame";
        }
        gameRepository.addGame(game.getName(), game.getType(), game.getId_cas());
        return "redirect:/games";
    }

    @RequestMapping(value = "/insPart", method = RequestMethod.POST)
    public String insPart(@Valid @ModelAttribute("partner") Partner partner, BindingResult result, Model model){
        ArrayList<String> errs = partnerValidationService.validate(partner, true);
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("casinos", casinoRepository.findAll());
            return "inserts/insertPartner";
        }
        partnerRepository.addPartner(partner.getName(), partner.getId_cas(), partner.getService(), partner.getCost(), partner.getEnds());
        return "redirect:/partners";
    }

    @RequestMapping(value = "/insGu", method = RequestMethod.POST)
    public String insGu(@Valid @ModelAttribute("guest") Guest guest, BindingResult result, Model model){
        ArrayList<String> errs = guestValidationService.validate(guest, true);
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("casinos", casinoRepository.findAll());
            return "inserts/insertGuest";
        }
        guestRepository.addGuest(guest.getName(), guest.getId_cas(), guest.getReserved());
        return "redirect:/guests";
    }

    @RequestMapping(value = "/insRev", method = RequestMethod.POST)
    public String insRev(@Valid @ModelAttribute("review") Review review, BindingResult result, Model model){
        ArrayList<String> errs = new ArrayList<>();
        if(review.getStars() == null){
            errs.add("Ocena nie może być pusta");
        }
        else{
            if(review.getStars() < 1 || review.getStars() > 5){
                errs.add("Ocena musi być w przedziale 1-5");
            }
        }
        if(review.getId_g() == null){
            errs.add("Brak dostępnych gości");
        }
        /*if(!debtorRepository.findById(review.getId_g()).isEmpty()){
            errs.add("Recenzja odrzucona. Recenzja pisana przez dłużnika");
        }*/
        if(casinoRepository.isDebt(review.getId_g()) == 1){
            errs.add("Recenzja odrzucona. Recenzja pisana przez dłużnika");
        }
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("guests", reviewRepository.findValid());
            return "inserts/insertReview";
        }
        reviewRepository.addReview(review.getDesc(), review.getId_g(), review.getStars());
        return "redirect:/reviews";
    }

    @RequestMapping(value = "/insRest", method = RequestMethod.POST)
    public String insRest(@Valid @ModelAttribute("restaurant") Restaurant restaurant, BindingResult result, Model model){
        ArrayList<String> errs = new ArrayList<>();
        if(restaurant.getName().isBlank()){
            errs.add("Nazwa nie może być pusta");
        }
        else{
            restaurant.setName(restaurant.getName().toUpperCase());
        }
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("casinos", casinoRepository.findAll());
            return "inserts/insertRestaurant";
        }
        restaurantRepository.addRest(restaurant.getName(), restaurant.getId_cas());
        return "redirect:/restaurants";
    }

    @RequestMapping("/insDish")
    public String insDish(@RequestParam("rest") Long rest, @RequestParam("cas") Long cas, @Valid @ModelAttribute Dish dish, BindingResult result, Model model){
        ArrayList<String> errs = new ArrayList<>();
        if(dish.getName().isBlank()){
            errs.add("Nazwa nie może być pusta");
        }
        else{
            dish.setName(dish.getName().toUpperCase());
        }
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("rest", rest);
            model.addAttribute("cas", cas);
            return "inserts/insertDish";
        }
        dishRepository.addDish(dish.getName(), rest, cas);
        return "redirect:/dishes/?&rest=" + rest + "&cas=" + cas;
    }

    @RequestMapping("/insDrink")
    public String insDrink(@RequestParam("rest") Long rest, @RequestParam("cas") Long cas, @Valid @ModelAttribute Drink drink, BindingResult result, Model model) {
        ArrayList<String> errs = new ArrayList<>();
        if(drink.getName().isBlank()){
            errs.add("Nazwa nie może być pusta");
        }
        else{
            drink.setName(drink.getName().toUpperCase());
        }
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            model.addAttribute("rest", rest);
            model.addAttribute("cas", cas);
            return "inserts/insertDrink";
        }
        drinkRepository.addDrink(drink.getName(), rest, cas);
        return "redirect:/drinks/?&rest=" + rest.toString() + "&cas=" + cas.toString();
    }

    @RequestMapping("/update")
    public String update(@RequestParam("type") String type, @RequestParam("id") Long id, Model model){
        switch (type){
            case "Casino":
                model.addAttribute("casino", new Casino());
                Optional<Casino> casino = casinoRepository.findById(id);
                model.addAttribute("oldName", casino.get().getName());
                model.addAttribute("oldCity", casino.get().getCity());
                model.addAttribute("oldSeats", casino.get().getSeats());
                toUpdate = id;
                break;
            case "Bar":
                model.addAttribute("bar", new Bar());
                Optional<Bar> bar = barRepository.findById(id);
                model.addAttribute("oldName", bar.get().getName());
                toUpdate = id;
                break;
            case "Chip":
                model.addAttribute("chip", new Chip());
                Optional<Chip> chip = chipRepository.findById(id);
                model.addAttribute("oldValue", chip.get().getValue());
                model.addAttribute("oldColor", chip.get().getColor());
                model.addAttribute("oldSize", chip.get().getSize());
                model.addAttribute("oldReal_val", chip.get().getReal_val());
                toUpdate = id;
                break;
            case "Debtor":
                model.addAttribute("debtor", new Debtor());
                Optional<Debtor> debtor = debtorRepository.findById(id);
                model.addAttribute("oldDebt", debtor.get().getDebt());
                model.addAttribute("oldPart", debtor.get().getPart());
                model.addAttribute("oldNext", debtor.get().getNext());
                toUpdate = id;
                break;
            case "Employee":
                model.addAttribute("employee", new Employee());
                Optional<Employee> employee = employeeRepository.findById(id);
                model.addAttribute("oldName", employee.get().getName());
                model.addAttribute("oldSalary", employee.get().getSalary());
                model.addAttribute("oldFunction", employee.get().getFunction());
                model.addAttribute("oldEnds", employee.get().getEnds());
                toUpdate = id;
                break;
            case "Game":
                model.addAttribute("game", new Game());
                Optional<Game> game = gameRepository.findById(id);
                model.addAttribute("oldName", game.get().getName());
                model.addAttribute("oldType", game.get().getType());
                model.addAttribute("oldIncome", game.get().getIncome());
                toUpdate = id;
                break;
            case "Guest":
                model.addAttribute("guest", new Guest());
                Optional<Guest> guest = guestRepository.findById(id);
                model.addAttribute("oldName", guest.get().getName());
                model.addAttribute("oldReserved", guest.get().getReserved());
                model.addAttribute("oldWinnings", guest.get().getWinnings());
                toUpdate = id;
                break;
            case "Partner":
                model.addAttribute("partner", new Partner());
                Optional<Partner> partner = partnerRepository.findById(id);
                model.addAttribute("oldName", partner.get().getName());
                model.addAttribute("oldService", partner.get().getService());
                model.addAttribute("oldCost", partner.get().getCost());
                model.addAttribute("oldEnds", partner.get().getEnds());
                toUpdate=id;
                break;
            case "Restaurant":
                model.addAttribute("restaurant", new Restaurant());
                Optional<Restaurant> restaurant = restaurantRepository.findById(id);
                model.addAttribute("oldName", restaurant.get().getName());
                model.addAttribute("oldIncome", restaurant.get().getIncome());
                toUpdate=id;
                break;
            case "Review":
                model.addAttribute("review", new Review());
                Optional<Review> review = reviewRepository.findById(id);
                model.addAttribute("oldDesc", review.get().getDesc());
                model.addAttribute("oldStars", review.get().getStars());
                toUpdate=id;
                break;
        }
        return "updates/update" + type;
    }

    @RequestMapping("/updateDrIsh")
    public String update(@RequestParam("name") String name, @RequestParam("bar") Long bar, @RequestParam("cas") Long cas, @RequestParam("type") String type, Model model){
        searchName = name;
        if (type.matches("Drink")) {
            model.addAttribute("drink", new Drink());
            Optional<Drink> drink = drinkRepository.findById(new Drink(name, bar, cas));
            model.addAttribute("oldName", drink.get().getName());
            model.addAttribute("bar", bar);
            model.addAttribute("cas", cas);
        }
        else{
            model.addAttribute("dish", new Dish());
            Optional<Dish> dish = dishRepository.findById(new DishId(name, bar, cas));
            model.addAttribute("oldName", dish.get().getName());
            model.addAttribute("oldPopularity", dish.get().getPopularity());
            model.addAttribute("rest", bar);
            model.addAttribute("cas", cas);
        }
        return "updates/update" + type;
    }

    @RequestMapping(value = "/upCas", method = RequestMethod.POST)
    public String upCas(@Valid @ModelAttribute("casino") Casino casino, BindingResult result){
        ArrayList<String> errs = casinoValidationService.validate(casino, false);
        if(!errs.isEmpty()){
            for (String err : errs) {
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if (result.hasErrors()){
            return "updates/updateCasino";
        }
        Optional<Casino> tmp = casinoRepository.findById(toUpdate);
        String name = casino.getName();
        String city = casino.getCity();
        Integer seats = casino.getSeats();
        if(name.isBlank()){
            name = tmp.get().getName();
        }
        if(city.isBlank()){
            city = tmp.get().getCity();
        }
        if(seats == null){
            seats = tmp.get().getSeats();
        }
        casinoRepository.updateCasino(name, city, seats, toUpdate);
        return "redirect:/casinos";
    }

    @RequestMapping(value = "/upBar", method = RequestMethod.POST)
    public String upBar(@ModelAttribute("bar") Bar bar, BindingResult result){
        String name = bar.getName().toUpperCase();
        if (name.isBlank()){
            Optional<Bar> tmp = barRepository.findById(toUpdate);
            name = tmp.get().getName();
        }
        barRepository.updateBar(toUpdate, name);
        return "redirect:/bars";
    }

    @RequestMapping(value = "/upChip", method = RequestMethod.POST)
    public String upChip(@ModelAttribute("chip") Chip chip, BindingResult result){
        ArrayList<String> errs = chipValidationService.validate(chip, false);
        if(!errs.isEmpty()){
            for(String err: errs){
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if(result.hasErrors())
        {
            return "updates/updateChip";
        }
        Integer value = chip.getValue();
        String color = chip.getColor();
        String size = chip.getSize();
        Integer real_val = chip.getReal_val();
        Optional<Chip> tmp = chipRepository.findById(toUpdate);
        if (value == null)
        {
            value = tmp.get().getValue();
        }
        if (color.isBlank()){
            color = tmp.get().getColor();
        }
        if (size.isBlank()){
            size = tmp.get().getSize();
        }
        if (real_val == null){
            real_val = tmp.get().getReal_val();
        }

        chipRepository.updateChip(value, color, size, real_val, toUpdate);
        return "redirect:/chips";
    }
    @RequestMapping(value = "/upDebtor", method = RequestMethod.POST)
    public String upDebtor(@ModelAttribute("debtor") Debtor debtor, BindingResult result){
        if(debtor.getPart() < 0 || debtor.getNext().before(Date.valueOf(LocalDate.now())))
        {
            return "updates/updateDebtor";
        }
        Double debt = debtor.getDebt();
        Double part = debtor.getPart();
        Date next = debtor.getNext();
        Long id_part = debtor.getId_part();
        Optional<Debtor> tmp = debtorRepository.findById(toUpdate);
        if (debt == null)
        {
            debt = tmp.get().getDebt();
        }
        if (part == null){
            part = tmp.get().getPart();
        }
        if (next == null) {
            next = tmp.get().getNext();
        }
        debtorRepository.updateDebtor(debt, part, next, toUpdate);
        return "redirect:/chips";
    }
    @RequestMapping(value = "/upDish", method = RequestMethod.POST)
    public String upDish(@Param("rest") Long rest, @Param("cas") Long cas, @ModelAttribute("dish") Dish dish, BindingResult result){
        String name = dish.getName();
        DishId id = new DishId(searchName, rest, cas);
        Integer popularity = dish.getPopularity();
        Optional<Dish> tmp = dishRepository.findById(id);
        if (name.isBlank())
        {
            name = tmp.get().getName();
        }
        if (popularity == null){
            popularity = tmp.get().getPopularity();
        }

        dishRepository.updateDish(name, popularity, searchName);
        return "redirect:/dishes/?&rest=" + rest.toString() + "&cas=" + cas.toString();
    }
    @RequestMapping(value = "/upDrink", method = RequestMethod.POST)
    public String upDrink(@Param("bar") Long bar, @Param("cas") Long cas, @ModelAttribute("drink") Drink drink, BindingResult result){
        String name = drink.getName();
        DishId id = new DishId(searchName, bar, cas);
        Optional<Dish> tmp = dishRepository.findById(id);
        if (name.isBlank())
        {
            name = tmp.get().getName();
        }

        drinkRepository.updateDrink(name, searchName);
        return "redirect:/drinks/?&rest=" + bar.toString() + "&cas=" + cas.toString();
    }
    @RequestMapping(value = "/upEmployee", method = RequestMethod.POST)
    public String upEmployee(@ModelAttribute("employee") Employee employee, BindingResult result){
        ArrayList<String> errs = employeeValidationService.validate(employee, false);
        if(!errs.isEmpty()){
            for(String err: errs){
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if(result.hasErrors())
        {
            return "updates/updateEmployee";
        }
        String name = employee.getName();
        Double salary = employee.getSalary();
        String function = employee.getFunction();
        Date ends = employee.getEnds();
        Optional<Employee> tmp = employeeRepository.findById(toUpdate);
        if (name.isBlank())
        {
            name = tmp.get().getName();
        }
        if (salary == null)
        {
            salary = tmp.get().getSalary();
        }
        if (function.isBlank())
        {
            function = tmp.get().getFunction();
        }
        if (ends == null)
        {
            ends = tmp.get().getEnds();
        }

        employeeRepository.updateEmployee(name, salary, function, ends, toUpdate);
        return "redirect:/employees";
    }

    @RequestMapping(value = "/upGame", method = RequestMethod.POST)
    public String upGame(@ModelAttribute("game") Game game, BindingResult result){
        ArrayList<String> errs = gameValidationService.validate(game, false);
        if(!errs.isEmpty()){
            for(String err: errs){
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if(result.hasErrors())
        {
            return "updates/updateGame";
        }
        String name = game.getName();
        String type = game.getType();
        Double income = game.getIncome();
        Optional<Game> tmp = gameRepository.findById(toUpdate);
        if (name.isBlank())
        {
            name = tmp.get().getName();
        }
        if (type.isBlank())
        {
            type = tmp.get().getType();
        }
        if (income == null)
        {
            income = tmp.get().getIncome();
        }

        gameRepository.updateGame(name, type, income, toUpdate);
        return "redirect:/games";
    }

    @RequestMapping(value = "/upGuest", method = RequestMethod.POST)
    public String upGuest(@ModelAttribute("guest") Guest guest, BindingResult result){
        ArrayList<String> errs = guestValidationService.validate(guest, false);
        if(!errs.isEmpty()){
            for(String err: errs){
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if(result.hasErrors())
        {
            return "updates/updateGuest";
        }
        String name = guest.getName();
        Date reserved = guest.getReserved();
        Double winnings = guest.getWinnings();
        Optional<Guest> tmp = guestRepository.findById(toUpdate);
        if (name.isBlank())
        {
            name = tmp.get().getName();
        }
        if (reserved == null)
        {
            reserved = tmp.get().getReserved();
        }
        if (winnings == null)
        {
            winnings = tmp.get().getWinnings();
        }

        guestRepository.updateGuest(name, reserved, winnings, toUpdate);
        return "redirect:/guests";
    }

    @RequestMapping(value = "/upPartner", method = RequestMethod.POST)
    public String upPartner(@ModelAttribute("partner") Partner partner, BindingResult result){
        ArrayList<String> errs = partnerValidationService.validate(partner, false);
        if(!errs.isEmpty()){
            for(String err: errs){
                ObjectError error = new ObjectError("globalError", err);
                result.addError(error);
            }
        }
        if(result.hasErrors())
        {
            return "updates/updatePartner";
        }
        String name = partner.getName();
        String service = partner.getService();
        Double cost = partner.getCost();
        Date ends = partner.getEnds();
        Optional<Partner> tmp = partnerRepository.findById(toUpdate);
        if (name.isBlank())
        {
            name = tmp.get().getName();
        }
        if (service.isBlank())
        {
            service = tmp.get().getService();
        }
        if (cost == null)
        {
            cost = tmp.get().getCost();
        }
        if (ends == null)
        {
            ends = tmp.get().getEnds();
        }

        partnerRepository.updatePartner(name, service, cost, ends, toUpdate);
        return "redirect:/partners";
    }

    @RequestMapping(value = "/upRestaurant", method = RequestMethod.POST)
    public String upRestaurant(@ModelAttribute("restaurant") Restaurant restaurant, BindingResult result){
        String name = restaurant.getName();
        Double income = restaurant.getIncome();
        Optional<Restaurant> tmp = restaurantRepository.findById(toUpdate);
        if (name.isBlank())
        {
            name = tmp.get().getName();
        }
        if (income == null)
        {
            income = tmp.get().getIncome();
        }
        restaurantRepository.updateRestaurant(name, income, toUpdate);
        return "redirect:/restaurants";
    }

    @RequestMapping(value = "/upReview", method = RequestMethod.POST)
    public String upReview(@ModelAttribute("review") Review review, BindingResult result){
        String desc = review.getDesc();
        Integer stars = review.getStars();
        Optional<Review> tmp = reviewRepository.findById(toUpdate);
        if (desc.isBlank())
        {
            desc = tmp.get().getDesc();
        }
        if (stars == null)
        {
            stars = tmp.get().getStars();
        }
        reviewRepository.updateReview(desc, stars, toUpdate);
        return "redirect:/reviews";
    }
}
