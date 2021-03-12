/**
 * Command interface
 * @authors 
 * Omer Sananes - 207644980||
 *Vlad Karasove - 320937014
 */
package command;

import java.io.FileNotFoundException;
import java.io.IOException;

public interface Command {
	/**
	 * excute command actions
	 * 
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	void excute() throws FileNotFoundException, IOException;
}
