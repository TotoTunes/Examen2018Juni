package view;

import java.io.IOException;
import java.sql.SQLException;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

import javax.swing.JComponent;
import javax.swing.JOptionPane;
import model.IDModule;
import model.User;
import utilities.Generator;

import org.apache.logging.log4j.Logger;
import org.junit.validator.PublicClassValidator;
import org.apache.logging.log4j.LogManager;

/**
 * @author Thomas Vanden Bossche
 * @date 14 mei. 2018
 * @project Afstandsbediening
 * @purpose Test afstandsbediening
 *
 */

@SuppressWarnings("serial")
public class AfstandsbedieningTest extends JComponent {

	// a. Random een aantal gebruikers worden aangemaakt en geregistreerd
	// (rechthebbenden).
	// Bewaren in de database mag maar moet niet.

	// b. De-activeer random een gebruiker.

	// c. Activeer random een gebruiker

	// d. Stuur een signaal naar de beheerder om de poort te openen.
	// d.i. vanuit een geregistreerde gebruiker
	// d.ii. vanuit een niet geregistreerde gebruiker

	// mooie exit uit het programma maken 

	public final static Logger LOGGER = LogManager.getLogger(AfstandsbedieningTest.class.getName());
	static IDModule module;

	public static void main(String[] args) throws IOException, SQLException {

		int r = 1;
		Executor executor = Executors.newCachedThreadPool();

		module = new IDModule();
		try {
			do {

				String a = JOptionPane.showInputDialog("Geef je keuze in: " + "\n1. Willekeurige users maken"
						+ "\n2. User (de)activeren " + "\n3. Frequentie poort veranderen "
						+ "\n4. Nieuwe User invoeren " + "\n5. Poort openen " + "\n6. Toon alle users"
						+ "\n7. Update alle afstandsbedieningen");
				int keuze = Integer.parseInt(a);

				switch (keuze) {

				// aanmaken van willekeurige users
				case 1:
					usersMaken();
				case 2:
					activeOrDeactive();
					break;

				// frequentie van de poort veranderen
				case 3:
					changeFrequency();
					break;
				// gebruiker toevoegen
				case 4:
					userToevoegen();
					break;
				// Poort openen
				case 5:
					poortOpenen();
					break;
				// alle users tonen
				case 6:
					JOptionPane.showMessageDialog(null, module.allToString());

					break;
				// frequentie van alle users updaten
				case 7:
					updateAll();
					break;
				default:
					System.out.println("Fout");
					break;
				}

			} while (r < 2);
		} catch (Exception e) {
			LOGGER.trace(e);
		}

	}

	static void usersMaken() throws IOException, SQLException {
		StringBuffer alles = new StringBuffer();
		for (int i = 0; i < 20; ++i) {
			User aUser = Generator.GenerateUsers(module.getPermittedFrequency());

			module.addObserver(aUser);

			alles.append(aUser.toString());
		}
		JOptionPane.showMessageDialog(null, alles);
		LOGGER.info("Er zijn 20 willekeurige gebruikers aangemaakt, er zitten nu " + module.getUserList().size()
				+ " in de database." + System.lineSeparator());
	}

	static void activeOrDeactive() throws IOException, SQLException {
		String achternaam = JOptionPane.showInputDialog("Geef een naam in: ");
		int g = Integer.parseInt(JOptionPane.showInputDialog(module.GetSpecificUser(achternaam)
				+ "\n Geef het nummer in van de persoon die je wilt (de)activeren\n EXIT =0"));
		if (g > 0) {
			User userToChange = module.GetSpecificUser(g - 1, module.getSearch());
			if (userToChange.isAcces() == true) {
				module.removeObserver(userToChange);
				JOptionPane.showMessageDialog(null, "De gebruiker heeft geen toegang meer");
				LOGGER.info("Volgende gebruiker is gedeactiveerd: " + userToChange.toString() + System.lineSeparator());

			} else {
				module.addObserver(userToChange);
				JOptionPane.showMessageDialog(null, "De gebruiker heeft nu toegang");
				LOGGER.info("Volgende gebruiker is geactiveerd: " + userToChange.toString() + System.lineSeparator());
			}
		}
	}

	static void changeFrequency() {
		module.setPermittedFrequency(Generator.Randomfrequency());
		JOptionPane.showMessageDialog(null, "Frequentie poort aangepast naar " + module.getPermittedFrequency());
		LOGGER.info(
				"De poort heeft deze frequentie gekregen: " + module.getPermittedFrequency() + System.lineSeparator());
	}

	static void userToevoegen() throws IOException, SQLException {
		String lastname = JOptionPane.showInputDialog("Geef de achternaam in");
		String firstname = JOptionPane.showInputDialog("Geef de voornaam in");
		module.addObserver(new User(true, module.getPermittedFrequency(), lastname, firstname, module));
		LOGGER.info("Volgende gebruiker is toegevoegd: " + firstname + " " + lastname + System.lineSeparator());
	}

	static void poortOpenen() throws SQLException, IOException {
		String achternaam1 = JOptionPane.showInputDialog("Geef een naam in: ");
		int g1 = Integer.parseInt(JOptionPane.showInputDialog(module.GetSpecificUser(achternaam1)
				+ "\n Geef het nummer in van de persoon die je wilt verwijderen\n EXIT =0"));
		if (g1 > 0) {
			User aUser = module.GetSpecificUser(g1 - 1, module.getSearch());
			module.openGate(aUser);
			LOGGER.info("Volgende gebruiker was aan de poort: " + aUser.toString() + System.lineSeparator());

		} // uit db

	}

	static void updateAll() throws SQLException, IOException {
		module.notifyAll(module.getUserList());
		JOptionPane.showMessageDialog(null, "Alle gebruikers met toegang hebben een nieuwe frequentie");
		LOGGER.info("Alle gebruikers met toegang hebben een nieuwe frequentie gekregen: "
				+ module.getPermittedFrequency() + System.lineSeparator());
	}

}
