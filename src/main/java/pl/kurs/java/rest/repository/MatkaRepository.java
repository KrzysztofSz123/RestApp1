package pl.kurs.java.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kurs.java.rest.model.Matka;

public interface MatkaRepository extends JpaRepository<Matka, Integer> {

}
