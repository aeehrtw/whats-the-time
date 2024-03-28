package com.sid.whatsthetime;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class WhatsthetimeApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(WhatsthetimeApplication.class);

	public static void main(String[] args) {

		SpringApplication.run(WhatsthetimeApplication.class, args).close();
	}

	@Bean
	CommandLineRunner runner() {


		/////// Change the time here to test
		String timeString = "10:10";

		Time time = new Time(timeString);
		TimeTeller timeTeller = new TimeTeller();
		System.out.println(timeTeller.tellTheTime(time));




		//////// Alternatively,,, Uncomment the below lines to run as a command line utility

//		while (true) {
//			System.out.println("Enter the time string, or Q or q to Quit");
//			Scanner scan = new Scanner(System.in);
//			String input = scan.nextLine();
//
//			if ( input.contains("Q") || input.contains("q") ) {
//				break;
//			} else {
//				try {
//					Time time = new Time(input.trim());
//					TimeTeller timeTeller = new TimeTeller();
//					System.out.println(timeTeller.tellTheTime(time));
//				} catch (IllegalArgumentException e) {
//					System.out.println("Invalid input. Try again. " +
//							"\nExpected formats are hh:mm or h:mm with valid hours(hh/h) and minute(mm) values\n");
//				}
//			}
//		}

        return args -> {};
    }

}
