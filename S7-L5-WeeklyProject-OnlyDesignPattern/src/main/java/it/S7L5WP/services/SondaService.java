package it.S7L5WP.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import it.S7L5WP.models.Sonda;
import it.S7L5WP.repository.SondaRepository;

@Service
public class SondaService {
	
	@Autowired
	SondaRepository repo;
	
	public Sonda save(Sonda s) {
		return repo.save(s);
	}
	
	public Optional<Sonda> getById(int s) {
		return repo.findById(s);
	}
	
	public List<Sonda> getAll() {
		return repo.findAll();
	}
}
