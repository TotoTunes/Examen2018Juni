package model;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Thomas Vanden Bossche
 * @date 14 mei. 2018
 * @project Afstandsbediening
 * @purpose Interface voor class IDModule
 *
 */

public interface ISubject
{
	void removeObserver(User user) throws IOException, SQLException;

	void addObserver(User user) throws IOException, SQLException;

	void updateObserver(double frequency, User user) throws SQLException, IOException;

	void notifyAll(ArrayList<User> arrayList) throws SQLException, IOException;

	String openGate(User user) throws SQLException, IOException;
}
