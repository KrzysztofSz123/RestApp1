package pl.kurs.java.rest.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pl.kurs.java.rest.command.CreatDzieckoCommand;
import pl.kurs.java.rest.model.Dziecko;
import pl.kurs.java.rest.model.Matka;
import pl.kurs.java.rest.modelDto.DzieckoDto;
import pl.kurs.java.rest.modelDto.MatkaDto;
import pl.kurs.java.rest.repository.DzieckoRepository;
import pl.kurs.java.rest.repository.MatkaRepository;

@RestController
@RequestMapping("/matka")
public class MatkaController {
	@Autowired
	private MatkaRepository matkaRepository;
	
	@Autowired
	private DzieckoRepository dzieckoRepository;

	@Autowired
	private ModelMapper mapper;

	@GetMapping
	public ResponseEntity<List<MatkaDto>> getAll() {
		List<Matka> matki = matkaRepository.findAll();
		return new ResponseEntity<List<MatkaDto>>(
				matki.stream().map(g -> mapper.map(g, MatkaDto.class)).collect(Collectors.toList()), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<DzieckoDto> addDziecko(@RequestBody CreatDzieckoCommand newDziecko){
		Matka matka = matkaRepository.findById(newDziecko.getIdMatki()).orElseThrow(()-> new IllegalArgumentException("Nieznane id matki:"+newDziecko.getIdMatki()));
		Dziecko nowe =	new Dziecko(null, newDziecko.getPlec(), newDziecko.getImie(), newDziecko.getDataUrodzenia(), newDziecko.getWaga(), newDziecko.getWzrost(), matka);
		
		return new ResponseEntity<DzieckoDto>(mapper.map(dzieckoRepository.saveAndFlush(nowe), DzieckoDto.class), HttpStatus.CREATED); 
		
	}
}
