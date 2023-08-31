package com.example.demo.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CustomerRepository;
import com.example.demo.dao.RestaurantRepository;
//import com.example.demo.dao.CustomerRepositoryImpl;
import com.example.demo.entities.Customer;
//import com.example.demo.entities.User;
import com.example.demo.entities.Restaurant;


@Controller
@RequestMapping("/res")
@CrossOrigin("http://localhost:3000")
public class RestaurantController {

	@Autowired
	private RestaurantRepository restaurantRepository;
	
	
	@PostMapping("/signup")
    public ResponseEntity<String> signUp(@RequestBody Restaurant restaurant) {
        restaurant.setApproved(false);
        restaurantRepository.save(restaurant);
        return ResponseEntity.ok("Restaurant sign-up request sent.");
    }
    @PostMapping("/approve/{restaurantId}")
    public ResponseEntity<String> approveRestaurant(@PathVariable int restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.setApproved(true);
            restaurant.setIsactive(true);
            restaurantRepository.save(restaurant);
            return ResponseEntity.ok("Restaurant approved.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/pending")
    public ResponseEntity<List<Restaurant>> pending() {
       
    	 return new ResponseEntity<>(restaurantRepository.findByApproved(false),HttpStatus.OK);
    }
    
    @GetMapping("/getallres")
    public ResponseEntity<List<Restaurant>> getallres() {
       
    	 return new ResponseEntity<>(restaurantRepository.findByApproved(true),HttpStatus.OK);
    }
    
    
    @PostMapping("/makeactive/{restaurantId}")
    public ResponseEntity<String> makeactive(@PathVariable int restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.setIsactive(true);
            restaurantRepository.save(restaurant);
            return ResponseEntity.ok("Restaurant made Active.");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/makeinactive/{restaurantId}")
    public ResponseEntity<String> makeinactive(@PathVariable int restaurantId) {
        Optional<Restaurant> optionalRestaurant = restaurantRepository.findById(restaurantId);
        if (optionalRestaurant.isPresent()) {
            Restaurant restaurant = optionalRestaurant.get();
            restaurant.setIsactive(false);
            restaurantRepository.save(restaurant);
            return ResponseEntity.ok("Restaurant made Inactive");
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping("/getactive")
    public ResponseEntity<List<Restaurant>> getactive() {
       
    	 return new ResponseEntity<>(restaurantRepository.findByIsactive(true),HttpStatus.OK);
    }
    
    
	
    
    
	
}
