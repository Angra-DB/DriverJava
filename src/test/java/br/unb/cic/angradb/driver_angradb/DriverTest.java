package br.unb.cic.angradb.driver_angradb;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import br.unb.cic.angradb.driverAngradb.Driver;
import org.junit.Assert;

public class DriverTest {
	
	private Driver driver;
	private String host = "127.0.0.1";
	private int port = 1234;
	
	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	
	@Before
	public void setUpStreams() {
	    System.setOut(new PrintStream(outContent));
	}
	
	@After
	public void cleanUpStreams() {
	    System.setOut(null);
	}

	@Before
	public void setUp() throws Exception{
		driver = new Driver(host, port);
	}
	
	@Test
	public void mustGetATcpConnection() throws Exception {
		//Execute
		driver.getTcpConnection();
		//Verify
		Assert.assertNotNull(driver.getClient());
		Assert.assertEquals(driver.getClient().getPort(), port);
		Assert.assertEquals(driver.getClient().getLocalAddress().getHostAddress(), host);
	}
	
	@Test(expected = Exception.class) 
	public void mustNotGetATcpConnection() throws Exception {
		//Setup
		driver = new Driver(host, 90000);
		//Execute
		driver.getTcpConnection();
	}
	
	@Test
	public void mustCreateADatabase() throws Exception {
		//Setup
		String dbName = "angraDb";
		
		String outputExpected = "Trying to connect to 127.0.0.1....\n"
		+ "Connected to 127.0.0.1:1234 .\n"
		+ "Database angraDb created with success\n";
		
		//Execute
		driver.getTcpConnection();
		driver.createDatabase(dbName);
		//Verify
	    Assert.assertEquals(outputExpected, outContent.toString());
	}

}
