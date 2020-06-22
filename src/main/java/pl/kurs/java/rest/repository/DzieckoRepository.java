package pl.kurs.java.rest.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.kurs.java.rest.model.Dziecko;

public interface DzieckoRepository extends JpaRepository<Dziecko, Integer> {

}
