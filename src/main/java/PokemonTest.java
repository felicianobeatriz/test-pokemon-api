import static io.restassured.RestAssured.given;

import java.util.Random;
import static org.hamcrest.Matchers.*;
import org.junit.BeforeClass;
import org.junit.Test;

import io.restassured.RestAssured;


public class PokemonTest {
	
	@BeforeClass
	public static void initalConfiguration() {
		RestAssured.baseURI = "https://pokeapi.co/";
		RestAssured.basePath = "api/v2/";
	}

	@Test
	public void getAllPokemon()
	{

		given()
			.log().all()
		.when()
			.get("pokemon")
		.then()	
			.statusCode(200)
			.body("count", is(964))
			.body("next", is(not(nullValue())))
			.body("previous", is(nullValue()))
			.body("results", hasSize(20))
		;
	}
	
	@Test
	public void getFirstPokemon() {
		given()
			.log().all()
		.when()
			.get("pokemon/1")
		.then()	
			.statusCode(200);
		;
	}
	
	@Test
	public void getRandomPokemon() {
		Random random = new Random();
		given()
			.log().all()
		.when()
			.get("pokemon/"+random.nextInt(964))
		.then()	
			.statusCode(200);
		;
	}
	
	@Test
	public void getFirstPikachu() {
		given()
			.log().all()
		.when()
			.get("pokemon/1")
		.then()	
			.statusCode(200);
		;
	}
	
	@Test
	public void habitatOfPokemons() {
		given()
			.log().all()
		.when()
			.get("pokemon/1")
		.then()	
			.statusCode(200);
		;
	}
	
	@Test
	public void specieOfPokemons() {
		given()
			.log().all()
		.when()
			.get("pokemon/1")
		.then()	
			.statusCode(200);
		;
	}
}
