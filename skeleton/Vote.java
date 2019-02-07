import java.time.format.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.Locale;
import java.util.Objects;

public class Vote {
	private int congress;
	private int session;
	private int year;
	private int number;
	
	// Use this to extract different components of date/time: see documentation
	private LocalDateTime dateTime;

	private static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d, yyyy,  hh:mm a", Locale.US);

	public Vote(int congress, int session, int year, int number, String dateTime) {
		this.congress = congress;
		this.session = session;
		this.year = year;
		this.number = number;

		this.dateTime = LocalDateTime.parse(dateTime, formatter);
	}

	// **EVERYTIME** you plan to create sets or maps of user-defined classes (cough, cough)
	// you need to **OVERRIDE** the default equals() and hashCode().
	//
	// Furthermore, any two objects that are deemed equal, **MUST** generate the same hashcode.
	// Below, you have an example of this programming pattern. Don't change any of the two methods below, but understand them.
	public boolean equals(Object otherObject) {
		if(!(otherObject instanceof Vote)) {
			return false;
		}
		
		Vote otherVote = (Vote) otherObject;

		return (congress == otherVote.congress && session == otherVote.session && year == otherVote.year && number == otherVote.number);
	}
	
	// Ditto above.
	public int hashCode() {
		return Objects.hash(congress, session, year, number);
	}
	
	// When Java needs to convert any object to string, the toString() method is called.
	// This one returns **ALL** the Vote's attributes in a comma-separated list. Convenient, no?
	public String toString() {
		return congress + ", " + session + ", " + year + ", " + number + ", " + getDate();
	}
	
	private String getDate() {
		return "'" + dateTime.getYear() + "-" + dateTime.getMonthValue() + "-" + dateTime.getDayOfMonth() + "'";
	}
}
