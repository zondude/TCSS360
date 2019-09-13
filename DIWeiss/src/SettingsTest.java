
public class SettingsTest {

	//public static void main(String[] args) {
	public static void man(String[] args) {
		Settings thing = new Settings();
		thing.email = "Testing123@gmail.com";
		thing.firstName = "Billy";
		thing.Export();
		thing.email = "Error";
		thing.firstName = "Error";
		thing.Import();
		System.out.println(thing.firstName + "\n" + thing.email + "\n");
	}

}
