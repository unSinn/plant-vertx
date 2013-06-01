package ch.ma3.plant;

import org.vertx.java.core.Vertx;
import ch.ma3.plant.verticles.PlantVerticle;

public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Vertx vertx = Vertx.newVertx("localhost");

		PlantVerticle v = new PlantVerticle();
		v.setVertx(vertx);
		v.start();
	}

}
