package br.com.philipe.jokenpo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Map;

import javax.naming.directory.InvalidAttributesException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.philipe.jokenpo.entity.Player;
import br.com.philipe.jokenpo.manager.PlayerManager;

@SpringBootTest
class PlayerManagerTest {

	@Autowired
	PlayerManager playerManager;

	@BeforeEach
	private void loadPlayers() throws InvalidAttributesException {
		playerManager.restart();
		playerManager.registerPlayer(new Player("Leonard", "PEDRA"));
		playerManager.registerPlayer(new Player("Howard", "TESOURA"));
		playerManager.registerPlayer(new Player("Raj", "TESOURA"));
		playerManager.registerPlayer(new Player("Sheldon", "SPOCK"));
	}

	@Test
	void shouldRegisterPlayerIfNotRegistered() {
		Player newPlayer = new Player("Penny", "PEDRA");

		Map<String, Player> expected = playerManager.getPlayers();
		expected.put(newPlayer.getName(), newPlayer);

		try {
			playerManager.registerPlayer(newPlayer);
		} catch (InvalidAttributesException e) {
			e.printStackTrace();
		}

		Map<String, Player> actual = playerManager.getPlayers();
		assertEquals(expected, actual);
	}

	@Test
	void shouldThrowExceptionWhenPlayerAlreadyRegistered() {
		Player newPlayer = new Player("Sheldon", "PEDRA");

		Map<String, Player> expected = playerManager.getPlayers();

		try {
			playerManager.registerPlayer(newPlayer);
		} catch (Exception e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertTrue(e.getMessage().contentEquals("Player already registered."));
		}

		assertEquals(expected, playerManager.getPlayers());
	}

	@Test
	void shouldThrowExceptionWhenPlayerHasInvalidAttribute() {
		Player newPlayer = new Player("Bernadette");

		Map<String, Player> expected = playerManager.getPlayers();

		try {
			playerManager.registerPlayer(newPlayer);
		} catch (Exception e) {
			assertTrue(e instanceof InvalidAttributesException);
			assertTrue(e.getMessage().contentEquals("Invalid attribute."));
		}

		assertEquals(expected, playerManager.getPlayers());
	}

	@Test
	void shouldUpdatePlayerIfRegistered() {
		Player player = new Player("Raj", "PAPEL");

		Map<String, Player> expected = playerManager.getPlayers();
		expected.put(player.getName(), player);

		try {
			playerManager.updatePlayer(player);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		Map<String, Player> actual = playerManager.getPlayers();
		assertEquals(expected, actual);
	}

	@Test
	void shouldThrowExceptionWhenUpdatePlayerNotRegistered() {
		Player player = new Player("Penny", "TESOURA");

		Map<String, Player> expected = playerManager.getPlayers();

		try {
			playerManager.updatePlayer(player);
		} catch (IllegalArgumentException e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertTrue(e.getMessage().contentEquals("Player not found."));
		}

		Map<String, Player> actual = playerManager.getPlayers();
		assertEquals(expected, actual);
	}

	@Test
	void shouldDeletePlayerIfRegistered() {
		Player player = new Player("Raj");

		Map<String, Player> expected = playerManager.getPlayers();
		expected.remove(player.getName());

		try {
			playerManager.deletePlayer(player);
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
		}

		Map<String, Player> actual = playerManager.getPlayers();
		assertEquals(expected, actual);
	}

	@Test
	void shouldThrowExceptionWhenDeletePlayerNotRegistered() {
		Player player = new Player("Penny");

		Map<String, Player> expected = playerManager.getPlayers();

		try {
			playerManager.deletePlayer(player);
		} catch (IllegalArgumentException e) {
			assertTrue(e instanceof IllegalArgumentException);
			assertTrue(e.getMessage().contentEquals("Player not found."));
		}

		Map<String, Player> actual = playerManager.getPlayers();
		assertEquals(expected, actual);
	}
}