package com.vijay.vehicleinventory.vehicle;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class VehicleService {
	@Autowired
    VehicleRepository repository;
	
	public boolean validateVehicleType(String vehicleType) {
		List<String> items = Arrays.asList("Car", "Truck", "Airplane", "Amphibian", "Boat");
		return items.parallelStream().anyMatch(vehicleType::contains);
	}
	
	public void addVehicle(Vehicle vehicle) {
		repository.save(vehicle);
	}
	
	public List<Vehicle> getAllVehicles() {
		List<Vehicle> vehicles = new ArrayList<>();
		repository.findAll().iterator().forEachRemaining(vehicles::add);
		return vehicles;
	}
	
	public Vehicle getVehicleById(Long id) {
		return repository.findById(id).get();
	}
	
	public void deleteVehicle(Long id) {
		repository.deleteById(id);
	}
	
	public void updateVehicle(Long id, Vehicle vehicle) {
		Vehicle currentVehicle = repository.findById(id).get();
		 
        if (currentVehicle == null) {
            System.out.println("Unable to upate. Vehicle with id " + id + " not found.");
        }
 
        currentVehicle.setVehicleName(vehicle.getVehicleName());
        currentVehicle.setVehicleType(vehicle.getVehicleType());
		repository.save(currentVehicle);
		
	}
	
	public List<Vehicle> getByVehicleType(String vehicleType) {
		List<Vehicle> vehicles = new ArrayList<>();
		repository.findByVehicleType(vehicleType).iterator().forEachRemaining(vehicles::add);
		return vehicles;
	}
}
