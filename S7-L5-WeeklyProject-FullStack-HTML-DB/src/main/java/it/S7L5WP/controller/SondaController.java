package it.S7L5WP.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.view.RedirectView;

import it.S7L5WP.models.Sonda;
import it.S7L5WP.services.SondaService;

@RestController
@RequestMapping("/alarm")
public class SondaController {
	
	@Autowired
	SondaService serv;
	
	//						url copy paste per velocizzare.
	//		http://localhost:8080/alarm?Id=1&lat=66.1234&lon=6.5678&smokelevel=6
	// Get by id / all / parametri ----------------------------------------
	@GetMapping("")
	public ResponseEntity<Object> getSondaMultiTask(
	            @RequestParam(required=false) Integer idsonda,
	            @RequestParam(required=false) Double lat, 
	            @RequestParam(required=false) Double lon, 
	            @RequestParam(required=false) Integer smokelevel) {
	    
	    // Se non ci sono parametri, restituisci la lista completa
	    if (idsonda == null && lat == null && lon == null && smokelevel == null) {
	        List<Sonda> sondaList = serv.getAll();
	        return new ResponseEntity<>(sondaList, HttpStatus.OK);
	    } 
	    
	    // Se viene fornito solo Id, cerca l'oggetto Sonda corrispondente
	    else if (lat == null && lon == null && smokelevel == null) {
	        Optional<Sonda> sondaObj = serv.getById(idsonda);
	        if (!sondaObj.isPresent()) {
	            return new ResponseEntity<>("Sonda non trovata per l'id specificato", HttpStatus.NOT_FOUND);
	        }
	        Sonda sonda = sondaObj.get();
	        return new ResponseEntity<>(sonda, HttpStatus.OK);
	    } 
	     
	    // Altrimenti, filtra l'oggetto Sonda corrispondente utilizzando tutti i parametri forniti
	    else {
	        Optional<Sonda> sondaObj = serv.getById(idsonda);
	        if (!sondaObj.isPresent()) {
	            return new ResponseEntity<>("Sonda non trovata per l'id specificato", HttpStatus.NOT_FOUND);
	        }
	        Sonda sonda = sondaObj.get();
	        if (sonda.getLatitudine() == lat && sonda.getLongitudine() == lon && sonda.getLivelloFumo() == smokelevel) {
	            return new ResponseEntity<>(sonda, HttpStatus.OK);
	        }
	        else {
	            return new ResponseEntity<>("Sonda non trovata per i parametri specificati", HttpStatus.NOT_FOUND);
	        }
	    }
	}
	
	// Get by id ----------------------------------------
    @GetMapping("/sonde/{id}")
    public ResponseEntity<Object> getById(@PathVariable Integer id) {
        Optional<Sonda> sondaObj = serv.getById(id);
        ResponseEntity<Object> check = checkExists(sondaObj);
        if( check != null ) return check;

        return new ResponseEntity<>(sondaObj.get(), HttpStatus.OK);
    }
    
    // post ----------------------------------------
    @PostMapping("/post")
    public RedirectView createSonda(@RequestParam double latitudine, 
                                     @RequestParam double longitudine,
                                     @RequestParam int smokelevel
    								) {
        Sonda sonda = new Sonda(latitudine, longitudine, smokelevel);
        serv.save(sonda);
        return new RedirectView("/crea_successo");
    }
    
    //put ------------------------------------------
    @PutMapping("/update/{id}")
    public ResponseEntity<Object> updateSonda(@PathVariable int id, @RequestParam(required=false) Integer smokelevel) {
        Optional<Sonda> sondaObj = serv.getById(id);
        ResponseEntity<Object> check = checkExists(sondaObj);
        if (check != null) {
            return check;
        }
        Sonda sonda = sondaObj.get();
        if (smokelevel != null) {
            sonda.setLivelloFumo(smokelevel);
        }
        serv.save(sonda);
        return new ResponseEntity<>("Sonda aggiornata con successo", HttpStatus.OK);
    }


    //---------------------------------------------------------------------//
    private ResponseEntity<Object> checkExists(Optional<Sonda> obj) {
        if( !obj.isPresent() ) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return null;
    }
}
