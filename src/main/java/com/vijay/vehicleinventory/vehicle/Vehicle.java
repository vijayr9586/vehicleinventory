package com.vijay.vehicleinventory.vehicle;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
 
@Entity
@Table(name = "vehicle")

public class Vehicle implements Serializable{

		private static final long serialVersionUID = -3009157732242241606L;
	    @Id
	    @GeneratedValue(strategy = GenerationType.AUTO)
	    private long id;

		@Column(name = "vehicleName")
	    private String vehicleName;
	 
	    @Column(name = "vehicleType")
	    private String vehicleType;
	 
	    protected Vehicle() {
	    }
	 
	    public Vehicle(String vehicleName, String vehicleType) {
	        this.vehicleName = vehicleName;
	        this.vehicleType = vehicleType;
	    }
	    
	    public long getId() {
			return id;
		}

		public void setId(long id) {
			this.id = id;
		}
		
	    public String getVehicleName() {
			return vehicleName;
		}

		public void setVehicleName(String vehicleName) {
			this.vehicleName = vehicleName;
		}

		public String getVehicleType() {
			return vehicleType;
		}

		public void setVehicleType(String vehicleType) {
			this.vehicleType = vehicleType;
		}
	 
	    @Override
	    public String toString() {
	        return String.format("Vehicle[id=%d, vehicleName='%s', vehicleType='%s']", id, vehicleName, vehicleType);
	    }
	}


