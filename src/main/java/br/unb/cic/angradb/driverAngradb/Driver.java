package br.unb.cic.angradb.driverAngradb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Driver {

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
     * @param Name of database.
     */
	public void createDatabase(String dbName) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("create_db " + dbName);
		
		if(reader.readLine().equals("{ok}")){
			System.out.println("Database " + dbName + " created with success");
		}
		else{
			System.out.println("Could not create the database " + dbName);
		}
	}
	
	/**
     * Get a tcp connection with angraDb.
     *
     */
	public void getTcpConnection() throws Exception {
		System.out.println("Trying to connect to " + host + "....");
		
		try{
			client = new Socket(host, port);
		}catch(Exception e){
			System.out.println("Could not create a tcp connection!");
			throw new Exception();
		}
		
		System.out.println("Connected to " + host + ":" + port + " .");
	}
	
	/**
     * Connect with a specific database.
     *
     * @param Name of database to connect with.
     */
	public void connectToDatabase(String dbName) throws IOException {
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("connect " + dbName);
		System.out.println(reader.readLine());		
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
