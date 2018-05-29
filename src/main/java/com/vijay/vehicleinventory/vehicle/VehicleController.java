package com.vijay.vehicleinventory.vehicle;
import java.util.List;
import java.util.NoSuchElementException;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.vijay.vehicleinventory.exception.InvalidVehicleException;
import com.vijay.vehicleinventory.exception.VehicleNotFoundException;
import com.vijay.vehicleinventory.vehicle.Vehicle;
import com.vijay.vehicleinventory.vehicle.VehicleService;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
public class VehicleController {
	private static final Logger logger = LoggerFactory.getLogger(VehicleController.class);
	
	@Autowired
	 VehicleService vehicleService;
	
	@ExceptionHandler(value = {VehicleNotFoundException.class})
    @ResponseStatus(value=HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorInfo handleNotFoundException(HttpServletRequest req, VehicleNotFoundException ex) {
        String errorMessage = ex.getMessage();
        return new ErrorInfo(errorMessage);
    }
	
	@ExceptionHandler(value = {InvalidVehicleException.class})
    @ResponseStatus(value=HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorInfo handleTypeMismatchException(HttpServletRequest req, InvalidVehicleException ex) {
        String errorMessage = ex.getMessage();
        return new ErrorInfo(errorMessage);
    }
	
    @RequestMapping(method=RequestMethod.POST, value = "/vehicle")
    public Vehicle addVehicle(@RequestBody Vehicle vehicle) throws InvalidVehicleException{
    	logger.debug("Begin: Adding new vehicle to the inventory");
    	vehicleService.validateVehicleType(vehicle.getVehicleType());
	    vehicleService.addVehicle(vehicle);
    	return vehicle;
    }
       	       
    @RequestMapping("/vehicle")
    public List<Vehicle> findAll(){
    	logger.debug("Begin: Listing vehicles from inventory");
        return vehicleService.getAllVehicles();
    }
       
    @RequestMapping("/vehicle/{id}")
    public Vehicle findById(@PathVariable("id") long id) throws VehicleNotFoundException{
    	try {
    		logger.debug("Begin: Listing vehicle from inventory by ID:"+Long.toString(id));
    		return vehicleService.getVehicleById(id);
	    }
		catch(NoSuchElementException e) {
			logger.error("Could not find vehicle with Id:"+Long.toString(id));
			throw new VehicleNotFoundException("Could not find vehicle with Id:"+Long.toString(id));
		}
    }
       
    
    @RequestMapping(method=RequestMethod.PUT, value="/vehicle/{id}")
    public Vehicle updateVehicle(@RequestBody Vehicle vehicle, @PathVariable("id") long id) throws InvalidVehicleException, VehicleNotFoundException {
    	try {
    			logger.debug("Begin: Updating vehicle from inventory by ID:"+Long.toString(id));
	    		vehicleService.validateVehicleType(vehicle.getVehicleType());
	    		vehicleService.updateVehicle(id, vehicle);
    	}
    	catch(NoSuchElementException e) {
    		logger.error("Could not find vehicle with Id:"+Long.toString(id));
    		throw new VehicleNotFoundException("Could not find vehicle with Id:"+Long.toString(id));
    	}
    	return vehicle;
    	
    }
    
    @RequestMapping(method=RequestMethod.DELETE, value="/vehicle/{id}")
    public void deleteVehicle(@PathVariable("id") long id) throws VehicleNotFoundException{
    	try {
    		logger.debug("Begin: Deleting vehicle from inventory by ID:"+Long.toString(id));
    		vehicleService.deleteVehicle(id);
    	}
    	catch(EmptyResultDataAccessException e) {
    		logger.error("Could not find vehicle with Id:"+Long.toString(id));
    		throw new VehicleNotFoundException("Could not find vehicle with Id:"+Long.toString(id));
    	}
    }
    
    @RequestMapping(value="/vehicle/search")
    public List<Vehicle> findByVehicleType(@RequestParam(name="vehicleType") String vehicleType) throws InvalidVehicleException{
    	logger.debug("Begin: Searching vehicle from inventory by Type:"+vehicleType);
    	vehicleService.validateVehicleType(vehicleType);
	    return vehicleService.getByVehicleType(vehicleType);
    }

}
