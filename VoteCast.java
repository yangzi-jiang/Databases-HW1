public class VoteCast {
	private Vote vote;
	private Senator senator;
	private char option;

	public VoteCast(Vote vote, Senator senator, char option) {
		this.vote = vote;
		this.senator = senator;
		this.option = option;
	}

	/*
	Creates a string that is easily adapted to a SQL query

	@params none

	@returns a string representing the tuple of Vote
	*/
	public String toString() {
		return quote(senator.getId()) + ", " + quote(vote.getNumber()) + ", " + "'" + option + "'";
	}

	private String quote(String text) {
		return "'" + text + "'";
	}
}