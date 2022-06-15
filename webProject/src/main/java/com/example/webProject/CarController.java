package com.example.webProject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller //xml filelar isleyir
//restcontroller ise json formatda isleyir
public class CarController {

    @Autowired
    CarRepository carRepository;

    @GetMapping("/")
    public String home(){
        return "index";//bu metod gedir yoxlayirki templates icinde html doc varmi varsa onun icindekini icra edir
    }

    @GetMapping("/car")
    public String carG(Model model){
        Iterable <Car> car = carRepository.findAll();
        model.addAttribute("cars",car);
        return "listCar";
    }
    @GetMapping("/create")
    public String create(Model model){
        Car car = new Car();
        model.addAttribute("car",car);
        return "create";
    }

    @PostMapping("/createAndSave")
    public  String create(@ModelAttribute Car car){
        carRepository.save(car);
        return "redirect:/car";
    }
    @GetMapping("/showUpdateForm")
    public ModelAndView showUpdateForm(@RequestParam Long carId) {
        ModelAndView mav = new ModelAndView("create");
        Car car = carRepository.findById(carId).get();
        mav.addObject("car", car);
        return mav;
    }
    @GetMapping("/delete")
    public String delete(@RequestParam Long carId) {
        carRepository.deleteById(carId);
        return "redirect:/car";
    }




}
