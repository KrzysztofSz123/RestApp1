package pl.kurs.java.rest.command;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreatDzieckoCommand {

	private String plec; 
	private String imie; 
	private LocalDate dataUrodzenia; 
	private int waga;
	private int wzrost; 
	private int idMatki;
}
