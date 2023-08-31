package com.example.demo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

//import org.springframework.data.repository.CrudRepository;


import com.example.demo.entities.Restaurant;

public interface RestaurantRepository extends JpaRepository<Restaurant, Integer> {
	 List<Restaurant> findByApproved(boolean approved);
	 List<Restaurant> findByIsactive(boolean isactive);
}
