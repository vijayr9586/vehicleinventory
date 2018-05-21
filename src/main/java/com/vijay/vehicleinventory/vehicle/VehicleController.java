package com.vijay.vehicleinventory.vehicle;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
 
import com.vijay.vehicleinventory.vehicle.Vehicle;
import com.vijay.vehicleinventory.vehicle.VehicleService;
 
@RestController
public class VehicleController {
	 @Autowired
	 VehicleService vehicleService;
	       
	    @RequestMapping(method=RequestMethod.POST, value = "/vehicle")
	    public String addVehicle(@RequestBody Vehicle vehicle){
	    	if(vehicleService.validateVehicleType(vehicle.getVehicleType())) {
		    	vehicleService.addVehicle(vehicle);
		        return vehicle.toString();
		    }
	    	else {
	    		return "Invalid vehicle type. VehicleType can be Car, Truck, Airplane, Amphibian, Boat";
	    	}
	    }
	       	       
	    @RequestMapping("/vehicle")
	    public List<Vehicle> findAll(){
	        return vehicleService.getAllVehicles();
	    }
	       
	    @RequestMapping("/vehicle/{id}")
	    public Vehicle findById(@PathVariable("id") long id){
	        return vehicleService.getVehicleById(id);
	    }
	       
	    
	    @RequestMapping(method=RequestMethod.PUT, value="/vehicle/{id}")
	    public String updateVehicle(@RequestBody Vehicle vehicle, @PathVariable("id") long id) {
	    	if(vehicleService.validateVehicleType(vehicle.getVehicleType())) {
	    		vehicleService.updateVehicle(id, vehicle);
	    		return vehicle.toString();
	    	}
	    	else {
	    		return "Invalid vehicle type. VehicleType can be Car, Truck, Airplane, Amphibian, Boat";
	    	}
	    }
	    
	    @RequestMapping(method=RequestMethod.DELETE, value="/vehicle/{id}")
	    public void deleteVehicle(@PathVariable("id") long id){
	    	vehicleService.deleteVehicle(id);
	    }
	    
	    @RequestMapping(value="/vehicle/search")
	    public List<Vehicle> findByVehicleType(@RequestParam(name="vehicleType") String vehicleType){
	    	return vehicleService.getByVehicleType(vehicleType);
	    }

}
