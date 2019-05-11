package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Group;
import javafx.scene.ImageCursor;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.paint.ImagePattern;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Line;
import javafx.scene.text.Text;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {
	int nums = 0;
	private int duration = 5;
	private int score = 0;
	private int fails = 0;
	private int randomnumber;
	private Button pause;
	private Button resume;
	private Text scoreLabel;
	private Text scoreText;
	private Text failLabel;
	private Text failText;
	private List<FruitInterfaceFactory> myArray = new ArrayList<>();
	private List<FruitInterfaceFactory> cuttedarray = new ArrayList<>();
	private FruitFactory fruitFactory = new FruitFactory();
	private Group group;
	private Screen screen;
	private Rectangle2D bounds;
	private Circle cursor;
	private Scene scene;
	private Line l;
	private AnimationTimer keepChecking;
	int k = 0;
	Image splitimg = new Image(
			"file:///C:/Users/HP/eclipse-workspace/FruitNinjaF-master/src/Assets/split%20strawberry.png");

	@Override
	public void start(Stage primaryStage) throws InterruptedException {

		primaryStage.setTitle("Test");

		// Labels for showing if the score or number of fails has increased
		scoreLabel = new Text("Score: ");
		scoreLabel.setFill(Color.WHITE);
		scoreLabel.setStyle("-fx-font: 24 arial;");
		scoreLabel.setLayoutX(100);
		scoreLabel.setLayoutY(100);

		scoreText = new Text("");
		scoreText.setFill(Color.WHITE);
		scoreText.setStyle("-fx-font: 24 arial;");
		scoreText.setLayoutX(190);
		scoreText.setLayoutY(100);

		failLabel = new Text("Fails: ");
		failLabel.setFill(Color.WHITE);
		failLabel.setStyle("-fx-font: 24 arial;");
		failLabel.setLayoutX(100);
		failLabel.setLayoutY(150);

		failText = new Text("");
		failText.setFill(Color.WHITE);
		failText.setStyle("-fx-font: 24 arial;");
		failText.setLayoutX(190);
		failText.setLayoutY(150);

		pause = new Button("Pause");
		resume = new Button("Resume");
		resume.setLayoutX(50);

		screen = Screen.getPrimary();
		bounds = screen.getVisualBounds();
		l = new Line(0, bounds.getMaxY(), bounds.getMaxX(), bounds.getMaxY());
		l.setStroke(Color.AQUA);
		cursor = new Circle(1, 1, 1, Color.GREEN);

		group = new Group();

		group.getChildren().addAll(pause, resume, l, scoreText, failText, scoreLabel, failLabel);

		scene = new Scene(group, bounds.getWidth(), bounds.getHeight());

		scene.setFill(Color.BLACK);
		primaryStage.setScene(scene);
		primaryStage.show();
		Image imagecur = new Image("Assets/blade.png");
		scene.setCursor(new ImageCursor(imagecur));
		fruitFactory.setBounds(bounds);

		Image image4 = new Image("Assets/background.png");
		scene.setFill(new ImagePattern(image4));

		swipe(); // Starts checking if the fruits are sliced

		Timeline timeline = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
			@Override
			public void handle(Event event) {

				Random random = new Random();
				int max = 10;
				int min = 1;
				randomnumber = random.nextInt((max - min) + 1) + min;
				for (int i = 0; i < myArray.size(); i++) {
					group.getChildren().remove(((Strawberry) myArray.get(i)).circle);
				}
				myArray.clear();
				for (int i = 0; i <3 /* randomnumber*/ ; i++) {
					myArray.add(fruitFactory.getFruit("strawberry"));
					Circle cirles = ((Strawberry) myArray.get(i)).circle;
					group.getChildren().addAll(cirles);
					myArray.get(i).move();
				}

			}
		}), new KeyFrame(Duration.seconds(5)));
		timeline.setCycleCount(Timeline.INDEFINITE);
		timeline.play();

		Timeline timeline2 = new Timeline(new KeyFrame(Duration.ZERO, new EventHandler() {
			@Override
			public void handle(Event event) {

				for (int i = 0; i < cuttedarray.size(); i++) {
					System.out.println(l.getLayoutX());
					System.out.println("center " + ((Strawberry) cuttedarray.get(i)).circle.getCenterX());
					System.out.println("scale" + ((Strawberry) cuttedarray.get(i)).circle.getLayoutX());
					if (((Strawberry) cuttedarray.get(i)).getImage()==splitimg&&((Strawberry) cuttedarray.get(i)).getImage()!=splitimg && ( cuttedarray.get(i).getEnd_x()-  ((Strawberry) cuttedarray.get(i)).circle.getCenterX()) == 400 || (int) cuttedarray.get(i).getEnd_x() == (int) ((Strawberry) cuttedarray.get(i)).circle.getCenterX()) {
						group.getChildren().remove(((Strawberry) cuttedarray.get(i)).circle);
						cuttedarray.remove(cuttedarray.get(i));					
					

					}
				}
					for(int i1 = 0 ; i1 < myArray.size(); i1++)
					{
						if(((Strawberry) myArray.get(i1)).getImage()!=splitimg && ( myArray.get(i1).getEnd_x()-  ((Strawberry) myArray.get(i1)).circle.getCenterX()) == 400 || (int) myArray.get(i1).getEnd_x() == (int) ((Strawberry) myArray.get(i1)).circle.getCenterX()) {
							group.getChildren().remove(((Strawberry) myArray.get(i1)).circle);
							myArray.remove(i1);
							fails++;
							failText.setText("" + fails);
					}
				}

			}
			
		}),new KeyFrame(Duration.millis(10)));timeline2.setCycleCount(Timeline.INDEFINITE);timeline2.play();

	pause.setOnAction(e->

	{

		scene.setOnMouseDragged(null);
		timeline.pause();
		for (int i = 0; i < myArray.size(); i++)
			myArray.get(i).stopMoving();

	});

	resume.setOnAction(e->
	{
		if (timeline.getStatus().toString().equals("PAUSED")) {
			swipe();
			timeline.play();
			for (int i = 0; i < myArray.size(); i++)
				myArray.get(i).resume();
		} else {
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.setTitle("Note");
			alert.setHeaderText("Already running");
			alert.setContentText("Game is already running");
			alert.showAndWait();
		}
	});
	}
	// BackgroundImage backgroundImage = new BackgroundImage(image4);

	public static void main(String[] args) {
		launch(args);
	}

	public void swipe() {

		scene.setOnMouseDragged(new EventHandler<MouseEvent>() {
			@Override
			public void handle(MouseEvent event) {
				cursor.setCenterX(event.getSceneX());
				cursor.setCenterY(event.getSceneY());

				for (int i = 0; i < myArray.size(); i++) {

					if (cursor.getBoundsInParent()
							.intersects(((Strawberry) myArray.get(i)).circle.getBoundsInParent())) {
					//	group.getChildren().remove(((Strawberry) myArray.get(i)).circle);
						((Strawberry) myArray.get(i)).setImage(splitimg);
						cuttedarray.add(myArray.remove(i));
						//myArray.remove(i);
						score++;
						scoreText.setText("" + score);
					}
					
				}

			}
		});
	}

}