package br.com.philipe.jokenpo.controller;

import java.util.List;
import java.util.Map;

import javax.naming.directory.InvalidAttributesException;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import br.com.philipe.jokenpo.entity.Player;
import br.com.philipe.jokenpo.manager.PlayerManager;
import br.com.philipe.jokenpo.manager.RefereeManager;

@Controller
public class GameController {

	@Autowired
	PlayerManager playerManager;

	@Autowired
	RefereeManager refereeManager;

	@GetMapping("/play")
	@ResponseBody
	public String play() {
		return refereeManager.assess();
	}

	@GetMapping("/player")
	@ResponseBody
	public Map<String, Player> get() {
		return playerManager.getPlayers();
	}

	@GetMapping("/players")
	@ResponseBody
	public Map<String, Player> getAll() {
		return playerManager.getPlayers();
	}

	@PostMapping("/player")
	@ResponseBody
	public void create(@RequestBody Player player, HttpServletResponse response) {
		try {
			playerManager.registerPlayer(player);
		} catch (IllegalArgumentException e) {
			response.setStatus(409);
		} catch (InvalidAttributesException e) {
			response.setStatus(400);
		}
	}

	@PostMapping("/players")
	@ResponseBody
	public void create(@RequestBody List<Player> players, HttpServletResponse response) {
		try {
			playerManager.registerPlayers(players);
		} catch (IllegalArgumentException e) {
			response.setStatus(409);
		} catch (InvalidAttributesException e) {
			response.setStatus(400);
		}
	}

	@PutMapping("/player")
	@ResponseBody
	public void update(@RequestBody Player player, HttpServletResponse response) {
		try {
			playerManager.updatePlayer(player);
		} catch (IllegalArgumentException e) {
			response.setStatus(404);
		}
	}

	@DeleteMapping("/player")
	@ResponseBody
	public void delete(@RequestBody Player player, HttpServletResponse response) {
		try {
			playerManager.deletePlayer(player);
		} catch (IllegalArgumentException e) {
			response.setStatus(404);
		}
	}
}
