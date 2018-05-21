package com.vijay.vehicleinventory.vehicle;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
 
import com.vijay.vehicleinventory.vehicle.Vehicle;
public interface VehicleRepository extends CrudRepository<Vehicle, Long>{
	
	List<Vehicle> findByVehicleType(String vehicleType);

}
