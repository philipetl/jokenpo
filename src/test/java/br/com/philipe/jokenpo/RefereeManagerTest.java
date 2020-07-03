package br.com.philipe.jokenpo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.naming.directory.InvalidAttributesException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import br.com.philipe.jokenpo.entity.Player;
import br.com.philipe.jokenpo.manager.PlayerManager;
import br.com.philipe.jokenpo.manager.RefereeManager;

@SpringBootTest
class RefereeManagerTest {

	@Autowired
	PlayerManager playerManager;

	@Autowired
	RefereeManager refereeManager;

	@Test
	void shouldNotAssessWhenThereIsNoPlayers() {
		playerManager.restart();

		String expected = "There is no players to assess.";
		String actual = refereeManager.assess();

		assertEquals(playerManager.getPlayers().isEmpty(), true);
		assertEquals(expected, actual);
	}

	@Test
	void shouldHaveNoWinner() throws InvalidAttributesException {
		playerManager.restart();

		playerManager.registerPlayer(new Player("Leonard", "PEDRA"));
		playerManager.registerPlayer(new Player("Howard", "TESOURA"));
		playerManager.registerPlayer(new Player("Raj", "TESOURA"));
		playerManager.registerPlayer(new Player("Sheldon", "SPOCK"));
		playerManager.registerPlayer(new Player("Penny", "PAPEL"));

		String expected = "No winner";
		String actual = refereeManager.assess();

		assertEquals(expected, actual);
	}

	@Test
	void playerWithSpockMoveShouldWin() throws InvalidAttributesException {
		String expected = "Winner: Sheldon";

		playerManager.restart();

		playerManager.registerPlayer(new Player("Leonard", "PEDRA"));
		playerManager.registerPlayer(new Player("Howard", "TESOURA"));
		playerManager.registerPlayer(new Player("Raj", "TESOURA"));
		playerManager.registerPlayer(new Player("Sheldon", "SPOCK"));

		String actual = refereeManager.assess();

		assertEquals(expected, actual);
	}

	@Test
	void playerWithScissorsMoveShouldWin() throws InvalidAttributesException {
		String expected = "Winner: Penny";

		playerManager.restart();

		playerManager.registerPlayer(new Player("Leonard", "PAPEL"));
		playerManager.registerPlayer(new Player("Raj", "PAPEL"));
		playerManager.registerPlayer(new Player("Penny", "TESOURA"));

		String actual = refereeManager.assess();

		assertEquals(expected, actual);
	}

	@Test
	void playerWithRockMoveShouldWin() throws InvalidAttributesException {
		String expected = "Winner: Penny";

		playerManager.restart();

		playerManager.registerPlayer(new Player("Leonard", "TESOURA"));
		playerManager.registerPlayer(new Player("Raj", "LAGARTO"));
		playerManager.registerPlayer(new Player("Penny", "PEDRA"));

		String actual = refereeManager.assess();

		assertEquals(expected, actual);
	}

	@Test
	void playerWithPapperMoveShouldWin() throws InvalidAttributesException {
		String expected = "Winner: Penny";

		playerManager.restart();

		playerManager.registerPlayer(new Player("Leonard", "PEDRA"));
		playerManager.registerPlayer(new Player("Raj", "SPOCK"));
		playerManager.registerPlayer(new Player("Penny", "PAPEL"));

		String actual = refereeManager.assess();

		assertEquals(expected, actual);
	}
}