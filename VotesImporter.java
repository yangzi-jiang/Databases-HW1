import java.util.*;

import java.io.FileInputStream;
import java.io.IOException;

import java.io.InputStream;
import java.io.PrintWriter;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathFactory;

import javax.xml.xpath.XPathConstants;

import javax.xml.xpath.XPathExpressionException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.xml.sax.SAXException;

import org.xml.sax.InputSource;

/**
 * Convert the Senate's votes in XML format into SQL commands that will create
 * and populate a database.
 * 
 * @author Hammurabi Mendes
 */
public class VotesImporter {
	// Which kind of data structures you should create?
	// **THIS IS THE MOST CRUCIAL CHOICE IN THIS ASSIGNMENT**
	HashSet<Senator> senators;
	HashSet<Vote> votes;
	HashSet<VoteCast> voteCasts;

	public VotesImporter() {
		senators = new HashSet<Senator>();
		votes = new HashSet<Vote>();
		voteCasts = new HashSet<VoteCast>();
	}

	/*
	 * Parses an xml file to obtain senator, vote, and voteCast information
	 * and store the informatoin into their respective hashset.
	 * 
	 * @params none
	 * 
	 * @returns none
	 */
	public void parseXmlFile(String xmlFile) {
		XPath xpath = XPathFactory.newInstance().newXPath();
		DocumentBuilderFactory factory;
		DocumentBuilder builder;
		Document xmlDocument = null;
		NodeList senatorNodeList = null;
		NodeList voteNodeList = null;
		InputStream inputStream = null;
		Senator senatorObj;
		Vote voteObj;
		VoteCast voteCastObj;

		try {
			inputStream = new FileInputStream(xmlFile);
		} catch (IOException exception) {
			System.err.println("Error opening input file.");
			exception.printStackTrace();
			return;
		}

		try {
			factory = DocumentBuilderFactory.newInstance();
			builder = factory.newDocumentBuilder();
			xmlDocument = builder.parse(xmlFile);

			String senatorExp = "/roll_call_vote/members/member";
			String voteExp = "/roll_call_vote";
			String voteCastExp = "/roll_call_vote/members/member/vote_cast";

			// Parse senators
			senatorNodeList = (NodeList) xpath.compile(senatorExp).evaluate(xmlDocument, XPathConstants.NODESET);
			// Parse votes
			voteNodeList = (NodeList) xpath.compile(voteExp).evaluate(xmlDocument, XPathConstants.NODESET);

			Element vote = (Element) voteNodeList.item(0);
			int voteNum = Integer.parseInt(vote.getElementsByTagName("vote_number").item(0).getTextContent());
			int congressNum = Integer.parseInt(vote.getElementsByTagName("congress").item(0).getTextContent());
			int session = Integer.parseInt(vote.getElementsByTagName("session").item(0).getTextContent());
			int congressYear = Integer.parseInt(vote.getElementsByTagName("congress_year").item(0).getTextContent());
			String voteDate = vote.getElementsByTagName("vote_date").item(0).getTextContent();
			voteObj = new Vote(congressNum, session, congressYear, voteNum, voteDate);
			votes.add(voteObj);

			for (int i = 0; i < senatorNodeList.getLength(); i++) {
				Element senator = (Element) senatorNodeList.item(i);
				String id = senator.getElementsByTagName("lis_member_id").item(0).getTextContent();
				String lastName = senator.getElementsByTagName("last_name").item(0).getTextContent();
				String firstName = senator.getElementsByTagName("first_name").item(0).getTextContent();
				String state = senator.getElementsByTagName("state").item(0).getTextContent();
				String party = senator.getElementsByTagName("party").item(0).getTextContent();
				char option = convertVote(senator.getElementsByTagName("vote_cast").item(0).getTextContent());
				senatorObj = new Senator(id, lastName, firstName, party, state);
				if(!senators.contains(senatorObj)){
					senators.add(senatorObj);
				}
				voteCastObj = new VoteCast(voteObj, senatorObj, option);
				voteCasts.add(voteCastObj);
			}

		} catch (ParserConfigurationException | SAXException | IOException | XPathExpressionException e) {
			e.printStackTrace();
		}

		// now we have an inputStream! we need to parse it... hmmm

	}

	// You probably want this.
	private char convertVote(String vote) {
		if (vote.equals("Yea")) {
			return 'Y';
		}

		if (vote.equals("Nay")) {
			return 'N';
		}

		return 'A';
	}
	/*
	 * Parses all xml files in the directory XML
	 * 
	 * @params none
	 * 
	 * @returns none
	 */
	public void parseXmlFiles() {
		for (int i = 1; i <= 274; i++) {
			parseXmlFile("XML/vote" + i + ".xml");
		}
	}

	/*
	 * Generates SQL to insert values into the database
	 * 
	 * @params none
	 * 
	 * @returns none
	 */
	public void generateSQL() {
		try {
			PrintWriter printWriter = new PrintWriter("SenatorVotesSchema.sql");

			// TODO: Complete Here

			// To write something to the file use printWriter.println("foo bar");

			// For each senator you found:
			// Create a SQL query to insert the senator into your relation
			// NOTE: Java will automatically call the toString() method when you concatenate
			// a senator object to a string,
			// effectively getting a string representation of the object.

			for (Senator s : senators) {
				printWriter.println("INSERT INTO Senator VALUES (" + s + ");");
			}

			// For each vote you found:
			// Create a SQL query to insert the vote into your relation
			// NOTE: Java will automatically call the toString() method when you concatenate
			// a vote object to a string
			// effectively getting a string representation of the object.

			for (Vote v : votes) {
				printWriter.println("INSERT INTO Vote VALUES (" + v + ");");
			}

			// For each vote cast you found:
			// Create a SQL query to insert the vote cast into your relation
			// NOTE: Java will automatically call the toString() method when you concatenate
			// a votecast object to a string
			// effectively getting a string representation of the object.

			for (VoteCast vc : voteCasts) {
				printWriter.println("INSERT INTO VoteCast VALUES (" + vc + ");");
			}

			printWriter.close();
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
