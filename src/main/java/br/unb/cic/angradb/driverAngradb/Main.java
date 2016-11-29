package br.unb.cic.angradb.driverAngradb;

public class Main {

	public static void main(String[] args) throws Exception {
		Driver driver = new Driver("127.0.0.1", (short) 1234);
		driver.createDatabase(".net");
		driver.connectToDataBase(".net");

	}

}
