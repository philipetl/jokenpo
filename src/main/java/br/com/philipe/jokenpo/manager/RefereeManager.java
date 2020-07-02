package br.com.philipe.jokenpo.manager;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.philipe.jokenpo.entity.Player;

public class RefereeManager {

	@Autowired
	private PlayerManager playerManager;

	private static Map<String, HashMap<String, Integer>> rules = new HashMap<String, HashMap<String, Integer>>();

	public RefereeManager() {
		loadRules();
	}

	public String assess() {
		if(playerManager.getPlayers().isEmpty()) {
			return "There is no players to assess.";
		}
		
		List<Player> playersList = new ArrayList<Player>(playerManager.getPlayers().values());
		

		for (Player player : playersList) {
			List<Player> opponents = playersList.stream().filter(opponent -> opponent != player)
					.collect(Collectors.toList());

			for (Player opponent : opponents) {
				int score = rules.get(player.getMove()).get(opponent.getMove());

				if (score > 0) {
					player.win();
					opponent.loses();
				} else if (score < 0) {
					player.loses();
					opponent.win();
				} else {
					player.draws();
					opponent.draws();
				}
			}

		}

		List<Player> winner = playersList.stream().filter(p -> p.getWins() > 0 && p.getLoses() == 0)
				.collect(Collectors.toList());

		final String result;
		if (winner.isEmpty()) {
			result = "No winner";
		} else if (winner.size() == 1) {
			result = "Winner: " + winner.get(0).getName();
		} else {
			result = "Draw: " + winner.toString();
		}

		playerManager.clear();
		return result;
	}

	private void loadRules() {
		HashMap<String, Integer> pedra = new HashMap<>();
		pedra.put("PEDRA", 0);
		pedra.put("PAPEL", -1);
		pedra.put("TESOURA", 1);
		pedra.put("SPOCK", -1);
		pedra.put("LAGARTO", 1);
		rules.put("PEDRA", pedra);

		HashMap<String, Integer> papel = new HashMap<>();
		papel.put("PEDRA", 1);
		papel.put("PAPEL", 0);
		papel.put("TESOURA", -1);
		papel.put("SPOCK", 1);
		papel.put("LAGARTO", -1);
		rules.put("PAPEL", papel);

		HashMap<String, Integer> tesoura = new HashMap<>();
		tesoura.put("PEDRA", -1);
		tesoura.put("PAPEL", 1);
		tesoura.put("TESOURA", 0);
		tesoura.put("SPOCK", -1);
		tesoura.put("LAGARTO", 1);
		rules.put("TESOURA", tesoura);

		HashMap<String, Integer> lagarto = new HashMap<>();
		lagarto.put("PEDRA", -1);
		lagarto.put("PAPEL", 1);
		lagarto.put("TESOURA", -1);
		lagarto.put("SPOCK", 1);
		lagarto.put("LAGARTO", 0);
		rules.put("LAGARTO", lagarto);

		HashMap<String, Integer> spock = new HashMap<>();
		spock.put("PEDRA", 1);
		spock.put("PAPEL", -1);
		spock.put("TESOURA", 1);
		spock.put("SPOCK", 0);
		spock.put("LAGARTO", -1);
		rules.put("SPOCK", spock);
	}
	
	public static boolean validateMove(String move) {
		return rules.containsKey(move);
	}
}
