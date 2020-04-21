package practice.simpleNote;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class SimpleNoteApplication {

	public static void main(String[] args) {
		SpringApplication.run(SimpleNoteApplication.class, args);
	}

	//TODO think this over, does DELETE throw exceptions ?

	//TODO IMPORTANT: SHOULD THE USER BE ABLE TO ADD EMPTY NOTES WITH JUST A TITLE ?
	//TODO PROBABLY YES, FIND A WAY TO ALLOW THAT TO HAPPEN
}
