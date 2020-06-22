package pl.kurs.java.rest.modelDto;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kurs.java.rest.model.Matka;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class DzieckoDto {
	private Integer id;
	private String plec; 
	private String imie; 
	private LocalDate dataUrodzenia; 
	private int waga;
	private int wzrost; 
	
}
