public class Senator {
	private String id;
	private String lastName;
	private String firstName;
	private String party;
	private String state;

	public Senator(String id, String lastName, String firstName, String party, String state) {
		this.id = id;
		this.lastName = lastName;
		this.firstName = firstName;
		this.party = party;
		this.state = state;
	}

	// **EVERYTIME** you plan to create sets or maps of user-defined classes (cough, cough)
	// you need to **OVERRIDE** the default equals() and hashCode().
	//
	// Furthermore, any two objects that are deemed equal, **MUST** generate the same hashcode.
	// Below, you have an example of this programming pattern. Don't change any of the two methods below, but understand them.
	public boolean equals(Object otherObject) {
		if(!(otherObject instanceof Senator)) {
			return false;
		}

		Senator otherSenator = (Senator) otherObject;

		return id.equals(otherSenator.id);
	}

	// Ditto above.
	public int hashCode() {
		return id.hashCode();
	}

	// When Java needs to convert any object to string, the toString() method is called.
	// This one returns **ALL** the Senator's attributes in a comma-separated list. Convenient, no?
	public String toString() {
		return quote(id) + ", " + quote(lastName) + ", " + quote(firstName) + ", " + quote(party) + ", " + quote(state);
	}

	private String quote(String text) {
		return "'" + text + "'";
	}
}
