
package main;

import classes.Store;
import controller.Controller;
import javafx.application.Application;
import javafx.stage.Stage;
import view.StoreView;

public class Main extends Application {
	public static void main(String[] args) {
		launch(args);
	}

	/**
	 * Starting application
	 */
	@Override
	public void start(Stage primaryStage) throws Exception {
		Store model = new Store();
		StoreView view = new StoreView(primaryStage);
		Controller controller = new Controller(model, view);
		controller.cancelYellowError();
	}
}
