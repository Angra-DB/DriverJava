package br.unb.cic.angradb.driverAngradb;

public class Main {

	public static void main(String[] args) throws Exception {
		Driver driver = new Driver("127.0.0.1", 1234);
		
		driver.getTcpConnection();
		driver.createDatabase("angraDb");
		driver.connectToDatabase("angraDb");

	}

}
