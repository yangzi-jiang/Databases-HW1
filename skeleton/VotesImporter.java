import java.util.*;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import javax.xml.xpath.XPathConstants;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;

import org.xml.sax.InputSource;

/**
 *  Convert the Senate's votes in XML format into SQL commands that will create and populate a database.
 * 
 * @author Hammurabi Mendes
 */
public class VotesImporter {
	// Which kind of data structures you should create?
	// **THIS IS THE MOST CRUCIAL CHOICE IN THIS ASSIGNMENT**
	
	public VotesImporter() {
		// Initialize your object-level data structures here
	}

	public void parseXmlFile(String xmlFile) {
		XPath xpath = XPathFactory.newInstance().newXPath();

		InputStream inputStream = null;

		try {
			inputStream = new FileInputStream(xmlFile);
		} catch (IOException exception) {
			System.err.println("Error opening input file.");
			exception.printStackTrace();
			return;
		}
		
		// TODO: Complete Here
	}

	// You probably want this.
	private char convertVote(String vote) {
		if(vote.equals("Yea")) {
			return 'Y';
		}

		if(vote.equals("Nay")) {
			return 'N';
		}

		return 'A';
	}

	public void parseXmlFiles() {
		for(int i = 1; i <= 274; i++) {
			parseXmlFile("XML/vote" + i + ".xml");
		}		
	}

	public void generateSQL() {
		try {
			PrintWriter printWriter = new PrintWriter ("SenatorVotesSchema.sql");
			
			// TODO: Complete Here
			
			// To write something to the file use printWriter.println("foo bar");
			
			// For each senator you found:
			//   Create a SQL query to insert the senator into your relation
			//   NOTE: Java will automatically call the toString() method when you concatenate a senator object to a string,
			//         effectively getting a string representation of the object.

			// For each vote you found:
			//   Create a SQL query to insert the vote into your relation
			//   NOTE: Java will automatically call the toString() method when you concatenate a vote object to a string
			//         effectively getting a string representation of the object.

			// For each vote cast you found:
			//   Create a SQL query to insert the vote cast into your relation
			//   NOTE: Java will automatically call the toString() method when you concatenate a votecast object to a string
			//         effectively getting a string representation of the object.

			printWriter.close ();       
		} catch (IOException exception) {
			System.err.println("Error creating the output file.");
			return;
		}
	}

	public static void main(String[] args) {
		VotesImporter votesImporter = new VotesImporter();

		votesImporter.parseXmlFiles();

		votesImporter.generateSQL();
	}
}
