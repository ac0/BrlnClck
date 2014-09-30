package prs.ansh.bc.driver;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import prs.ansh.bc.BerlinClock;

public class Main {

	public static void main(String... args) throws IOException {
		try (AnnotationConfigApplicationContext ctxt = new AnnotationConfigApplicationContext(AppConfig.class)) {

			BerlinClock berlinClock = ctxt.getBean(BerlinClock.class);

			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

			String hhMMSs = br.readLine();
			while (hhMMSs != null && !(hhMMSs = hhMMSs.trim()).isEmpty()) {
				try {
					System.out.println(berlinClock.getDisplayLine(hhMMSs));
				} catch (Exception e) {
					System.out.println("Error: " + e.getClass().getName());
				}
				hhMMSs = br.readLine();
			}

		}
	}
}
