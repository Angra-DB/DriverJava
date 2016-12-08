package br.unb.cic.angradb.driverAngradb;

public class Main {

	public static void main(String[] args) throws Exception {
		String key;
		Driver driver = new Driver("127.0.0.1", 1234);
		
		driver.getTcpConnection();
		driver.createDatabase("angraDb");
		driver.connectToDatabase("angraDb");
		key = driver.save("Old Document.");
		driver.lookup(key);
		driver.update(key, "New Document!");
		driver.lookup(key);
		driver.delete(key);
		driver.lookup(key);
	}

}
