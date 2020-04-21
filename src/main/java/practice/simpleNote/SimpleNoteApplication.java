package practice.simpleNote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleNoteApplication.class, args);
	}

	//TODO ORDER FUNCTIONS INTO PRIVATE AND PUBLIC (so it's easier to keep track)
	//TODO think this over, does DELETE throw exceptions ?
}
