
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
