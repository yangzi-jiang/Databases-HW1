public class VoteCast {
	private Vote vote;
	private Senator senator;
	private char option;

	public VoteCast(Vote vote, Senator senator, char option) {
		this.vote = vote;
		this.senator = senator;
		this.option = option;
	}

	// You want to change this for two reasons:
	// 1) This is a bad joke.
	// 2) You need to fetch the appropriate attributes from Vote and Senator in order to generate
	//    a nice, comma-separate list of attributes that describe a tuple of your 'voted' relation.
	public String toString() {
		return "Why can't bicycles stand up on their own? They are two tired.";
	}
}