package it.S7L5WP.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="sonda")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Sonda{
	  
	  	@Id
	  	@Column(name = "idsonda")
	  	@GeneratedValue(strategy = GenerationType.IDENTITY)
	    private int id;
	    private double latitudine;
	    private double longitudine;
	  	@Column(name = "smokelevel")
	    private int livelloFumo;

	    //constructor
	    public Sonda(double latitudine, double longitudine, int smokelevel) {
	        this.latitudine = latitudine;
	        this.longitudine = longitudine;
	        this.livelloFumo = smokelevel;
	    }
}