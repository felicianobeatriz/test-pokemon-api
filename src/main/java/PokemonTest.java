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
	/**
	 * Validando:
	 *  - Status Code : 200
	 *  - Pokemons : 964
	 *  - Pokemons listados : 20
	 *  - Listas com mais pokemons : True
	 *  - Listas anteriores : False
	 * */

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
	
	/**
	 * Validando:
	 * 	- Status code : 200
	 *  - Nome, especie, experiencia e habilidades
	 **/
	@Test
	public void getFirstPokemon() {
		given()
			.log().all()
		.when()
			.get("pokemon/1")
		.then()	
			.statusCode(200)
			.body("name", is("bulbasaur"))
			.body("species.name", is("bulbasaur"))
			.body("base_experience", is(64))
			.body("abilities.ability.name", contains("chlorophyll", "overgrow"))
		;
	}
	
	/**
	 * Validando:
	 * 	- Status code : 200
	 *  - Retorno : != vazio
	 **/
	@Test
	public void getRandomPokemon() {
		Random random = new Random();
		given()
			.log().all()
		.when()
			.get("pokemon/"+random.nextInt(964))
		.then()	
			.statusCode(200)
			.body(is(not(nullValue())));
		;
	}
	
	/**
	 * Validando:
	 * 	- Status code : 200
	 *  - Nome, especie, experiencia e habilidades
	 **/
	@Test
	public void getLastPokemon() {
		given()
			.log().all()
		.when()
			.get("pokemon/10157")
		.then()	
			.statusCode(200)
			.body("name", is("necrozma-ultra"))
			.body("species.name", is("necrozma"))
			.body("base_experience", is(339))
			.body("abilities.ability.name", contains("neuroforce"))
		;
	}
	
	/**
	 * Validando:
	 * 	- Status code : 200
	 *  - Count  : 9
	 *  - Resultados : 9
	 **/
	@Test
	public void habitatOfPokemons() {
		given()
		.log().all()
	.when()
		.get("pokemon-habitat")
	.then()	
		.statusCode(200)
		.body("count", is(9))
		.body("results", hasSize(9))
	;
	}
	
	/**
	 * Validando:
	 * 	- Status code : 200
	 *  - Count  : 807
	 **/
	@Test
	public void specieOfPokemons() {
		given()
			.log().all()
		.when()
			.get("pokemon-species")
		.then()	
			.statusCode(200)
			.body("count", is(807))
		;
	}
}
