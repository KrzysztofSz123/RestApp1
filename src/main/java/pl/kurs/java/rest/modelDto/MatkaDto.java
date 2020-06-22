package pl.kurs.java.rest.modelDto;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import pl.kurs.java.rest.model.Dziecko;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MatkaDto {
	private Integer id;
	private String imie; 
	private int wiek; 
	private Set<DzieckoDto> dzieci;
}
