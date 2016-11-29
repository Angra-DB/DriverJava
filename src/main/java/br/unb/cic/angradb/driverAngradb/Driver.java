package br.unb.cic.angradb.driverAngradb;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class Driver {

	private Socket client;
	private String host;
	private short port;

	public Driver(String host, short port) throws Exception {
		this.host = host;
		this.port = port;
		connect();
	}

	public void createDatabase(String dbName) throws IOException {
		if (client == null){
			System.out.println("No DB connection");
		}
		PrintWriter out = new PrintWriter(client.getOutputStream(), true);
		InputStreamReader in = new InputStreamReader(client.getInputStream());
		BufferedReader reader = new BufferedReader(in);

		out.println("create_db " + dbName);
		System.out.println(reader.readLine());

	}

	private void connect() throws Exception {
		System.out.print("Trying to connect to " + host + "....");

		client = new Socket(host, port);
		System.out.println(" Connected to " + host + ":" + port + " .");
	}
	
	public void connectToDataBase(String dbName) throws IOException {
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

	public short getPort() {
		return port;
	}

	public void setPort(short port) {
		this.port = port;
	}

}
