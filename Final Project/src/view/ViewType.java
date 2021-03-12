/**
 * Interface for all views.
 * @authors 
 *  Omer Sananes - 207644980
 *	Vlad Karasove - 320937014
 */
package view;

import javafx.scene.Scene;

public interface ViewType {
	/**
	 * Scene init
	 */
	Scene SceneInit();

	/**
	 * Reset the view from screen
	 */
	void cleanScreen();
}
