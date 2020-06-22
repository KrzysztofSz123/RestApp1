package pl.kurs.java.rest.service;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.stream.Stream;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import org.springframework.util.ResourceUtils;
import pl.kurs.java.rest.model.Dziecko;
import pl.kurs.java.rest.model.Matka;
import pl.kurs.java.rest.repository.DzieckoRepository;
import pl.kurs.java.rest.repository.MatkaRepository;

@Service
public class DataReader {

	@Autowired
	private MatkaRepository matkaRepository;

	@Autowired
	private DzieckoRepository dzieckoRepository;

	@PostConstruct
	public void load() throws FileNotFoundException {
		System.out.println("-------");
		try{

			InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/mamy.txt");

			StringBuilder textBuilder = new StringBuilder();
			try (Reader reader = new BufferedReader(new InputStreamReader
					(inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
				int c = 0;
				while ((c = reader.read()) != -1) {
					textBuilder.append((char) c);
				}
			}
			System.out.println(textBuilder);
		}catch (Exception exc) {
			System.out.println("Error.MAMY");
		}
        try{

            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("/noworodki.txt");

            StringBuilder textBuilder = new StringBuilder();
            try (Reader reader = new BufferedReader(new InputStreamReader
                    (inputStream, Charset.forName(StandardCharsets.UTF_8.name())))) {
                int c = 0;
                while ((c = reader.read()) != -1) {
                    textBuilder.append((char) c);
                }
            }
            System.out.println(textBuilder);
        }catch (Exception exc) {
            System.out.println("Error1.NOWORODKI");
        }

		File file = ResourceUtils.getFile("classpath:mamy.txt");
		File file2 = ResourceUtils.getFile("classpath:noworodki.txt");
		
		try (Stream<String> linie = Files.lines(file.toPath(), Charset.defaultCharset())) {
			linie.map(linia -> linia.split(" "))//
					.map(wyrazy -> new Matka(Integer.parseInt(wyrazy[0]), wyrazy[1], Integer.parseInt(wyrazy[2]), null))//
					.forEach(matkaRepository::saveAndFlush);
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

		try (Stream<String> linie = Files.lines(file2.toPath(),
				Charset.forName("cp1250"))) {
			linie.map(linia -> linia.split(" "))//
					.map(wyrazy -> new Dziecko(Integer.parseInt(wyrazy[0]), wyrazy[1], wyrazy[2],
							LocalDate.parse(wyrazy[3]), Integer.parseInt(wyrazy[4]), Integer.parseInt(wyrazy[5]),
							matkaRepository.findById(Integer.parseInt(wyrazy[6])).get()))//
					.forEach(dzieckoRepository::saveAndFlush);

		} catch (Exception e) {
			throw new IllegalStateException(e);
		}

	}

}
