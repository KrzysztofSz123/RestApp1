package pl.kurs.java.rest.model;

import java.time.LocalDate;
import java.util.Optional;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@EqualsAndHashCode(exclude = {"matka"})
public class Dziecko {
	public Dziecko(int parseInt, String string, String string2, LocalDate parse, int parseInt2, int parseInt3,
			Optional<Matka> findById) {
		// TODO Auto-generated constructor stub
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String plec; 
	private String imie; 
	private LocalDate dataUrodzenia; 
	private int waga;
	private int wzrost; 
	
	@ManyToOne
	@JoinColumn(name = "id_matki")
	private Matka matka;
}
