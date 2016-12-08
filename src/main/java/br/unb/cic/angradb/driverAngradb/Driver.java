package br.unb.cic.angradb.driverAngradb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Driver {

	private static final Logger LOGGER = Logger.getLogger(Driver.class.getName());

	private Socket client;
	private String host;
	private int port;

	public Driver(String host, int port) throws Exception {
		this.host = host;
		this.port = port;
	}

	/**
	 * Creates a database and get a response.
	 *
	 * @param Name
	 *            of database.
	 */
	public void createDatabase(String dbName) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("create_db " + dbName);

		if (reader.readLine().equals("{ok}")) {
			LOGGER.log(Level.INFO, "\nDatabase " + dbName + " created with success.\n");
		} else {
			LOGGER.log(Level.SEVERE, "\nCould not create the database!\n" + dbName);
		}
	}

	/**
	 * Get a TCP connection with angraDb.
	 *
	 */
	public void getTcpConnection() throws Exception {
		LOGGER.log(Level.INFO, "\nTrying to connect to " + host + ":" + port + "....\n");

		try {
			client = new Socket(host, port);
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "\nCould not create a TCP connection due to the following exception:\n", e);
			throw new Exception();
		}
		LOGGER.log(Level.INFO, "\nConnected to " + host + ":" + port + ".\n");
	}

	/**
	 * Connect with a specific database.
	 *
	 * @param Name
	 *            of database to connect with.
	 */
	public void connectToDatabase(String dbName) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("connect " + dbName);
		LOGGER.log(Level.INFO, "\n" + reader.readLine() + "\n");
	}

	public String save(String document) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("save " + document);

		String key = reader.readLine();

		LOGGER.log(Level.INFO, "\n" + key + "\n");
		return key.replaceAll("\"", "");
	}

	public void lookup(String key) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("lookup " + key);

		LOGGER.log(Level.INFO, "\n" + reader.readLine() + "\n");
	}

	public String update(String key, String newDocument) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("update " + key + " " + newDocument);

		LOGGER.log(Level.INFO, "\n" + reader.readLine() + "\n");
		return key;
	}

	public void delete(String key) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("delete " + key);

		LOGGER.log(Level.INFO, "\n" + reader.readLine() + "\n");
	}

	public Socket getClient() {
		return client;
	}

	public void setClient(Socket client) {
		this.client = client;
	}

	public String getHost() {
		return host;
	}

	public void setHost(String host) {
		this.host = host;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

}
