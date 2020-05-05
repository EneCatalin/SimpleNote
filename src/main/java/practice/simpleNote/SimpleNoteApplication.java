package practice.simpleNote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleNoteApplication.class, args);
	}

	//TODO WRITE AN API MAP

	// TODO IMPORTANT: SHOULD THE USER BE ABLE TO ADD EMPTY NOTES WITH JUST A TITLE ?
	// PROBABLY YES, FIND A WAY TO ALLOW THAT TO HAPPEN

	// TODO create a delete board ? But that raises the issue of adding special roles for users and complicates
	// the project for no reason
}
