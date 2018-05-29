package com.vijay.vehicleinventory;

import org.springframework.boot.SpringApplication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VehicleinventoryApplication {
	private static final Logger logger = LoggerFactory.getLogger(VehicleinventoryApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(VehicleinventoryApplication.class, args);
		logger.info("VehicleInventory application started");
		
		
	}
	
}
