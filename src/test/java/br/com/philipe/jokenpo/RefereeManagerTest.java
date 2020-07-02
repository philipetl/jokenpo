package br.com.philipe.jokenpo;

import static org.junit.jupiter.api.Assertions.assertEquals;

import javax.naming.directory.InvalidAttributesException;

import org.junit.jupiter.api.BeforeEach;
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

	@BeforeEach
	private void loadPlayers() throws InvalidAttributesException {
		playerManager.clear();
		playerManager.registerPlayer(new Player("Leonard", "PEDRA"));
		playerManager.registerPlayer(new Player("Howard", "TESOURA"));
		playerManager.registerPlayer(new Player("Raj", "TESOURA"));
		playerManager.registerPlayer(new Player("Sheldon", "SPOCK"));
	}

	@Test
	void shouldNotAssessWhenThereIsNoPlayers() {
		playerManager.clear();
		
		String expected = "There is no players to assess.";
		String actual = refereeManager.assess();
		
		assertEquals(playerManager.getPlayers().isEmpty(), true);
		assertEquals(expected, actual);
	}
}