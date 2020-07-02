package br.com.philipe.jokenpo.entity;

public class Player implements Cloneable {

	private String name;
	private String move;

	private int wins;
	private int loses;
	private int draws;

	public Player() {

	}

	public Player(String name, String move) {
		this.name = name;
		this.move = move.trim().toUpperCase();
		this.zero();
	}

	public Player(String name) {
		this.name = name;
		this.zero();
	}

	private void zero() {
		this.wins = 0;
		this.loses = 0;
		this.draws = 0;
	}

	public void win() {
		this.wins++;
	}

	public void loses() {
		this.loses++;
	}

	public void draws() {
		this.draws++;
	}

	public String getName() {
		return name;
	}

	public String getMove() {
		return move;
	}

	public int getWins() {
		return this.wins;
	}

	public int getLoses() {
		return this.loses;
	}

	public int getDraws() {
		return this.draws;
	}

	@Override
	public boolean equals(Object obj) {
		if (!(obj instanceof Player))
			return false;
		Player otherPlayer = (Player) obj;

		return otherPlayer.getName().equals(this.name) && otherPlayer.getMove().equals(this.move);
	}

	@Override
	public String toString() {
//		return this.name + "[" + this.move.name() + "]\nw:\t"+ this.wins
//				+ "\nloses:\t" + this.loses
//				+ "\ndraws:\t" + this.draws;
		return this.name;
	}

	public Player clone() {
		Player player = null;
		try {
			player = (Player) super.clone();
		} catch(Exception e) {}
		return player;
	}
}
