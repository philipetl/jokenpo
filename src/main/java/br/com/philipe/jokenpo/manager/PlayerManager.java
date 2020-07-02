package br.com.philipe.jokenpo.manager;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.naming.directory.InvalidAttributesException;

import br.com.philipe.jokenpo.entity.Player;

public class PlayerManager {

	private Map<String, Player> players = new HashMap<>();

	public void clear() {
		players.clear();
	}
	
	public Player getPlayer(String name) {
		return players.get(name).clone();
	}

	public Map<String, Player> getPlayers() {
		return this.clone(players);
	}
	
	public void registerPlayers(List<Player> players) throws InvalidAttributesException{
		for (Player player : players) {
			this.registerPlayer(player);
		}
	}
	
	public void registerPlayer(Player player) throws InvalidAttributesException {
		if (players.containsKey(player.getName())) {
			throw new IllegalArgumentException("Player already registered.");
		} else if (player.getName() == null || player.getMove() == null
				|| !RefereeManager.validateMove(player.getMove())) {
			throw new InvalidAttributesException("Invalid attribute.");
		}

		this.players.put(player.getName(), player);
	}

	public Player updatePlayer(Player player) {
		if (players.containsKey(player.getName())) {
			players.put(player.getName(), player);
			return player.clone();
		} else {
			throw new IllegalArgumentException("Player not found.");
		}
	}

	public Player deletePlayer(Player player) {
		if (players.containsKey(player.getName())) {
			return players.remove(player.getName());
		} else {
			throw new IllegalArgumentException("Player not found.");
		}
	}

	private <K, V> Map<K, V> clone(Map<K, V> original) {
		return original.entrySet().stream().collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));
	}
}
