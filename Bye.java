import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Bye extends Application {
	
	public Bye(Stage stage)
	{
		start(stage);
	}

	public void start(Stage stage)
	{
		Text a,b;
		GridPane grd;
		Button c;
		a=new Text("Transaction Over!");
		a.setFont(Font.font("Calibri",FontWeight.BOLD,50));
		a.setFill(Color.CORAL);
		a.setUnderline(true);
		
		b=new Text("Thank you for banking with us......");
		b.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		b.setFill(Color.CORAL);
		
		c=new Button("OK");
		c.setPrefSize(200, 50);
		
		grd=new GridPane();
		grd.setVgap(30);
		grd.setHgap(10);
		grd.setPadding(new Insets(100,0,0,300));
		Image img=new Image(User.class.getResourceAsStream("female-billionaires-statistics.jpg"));
		BackgroundImage bI=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		grd.setBackground(new Background(bI));
		
		GridPane.setConstraints(a, 0, 2, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(a);
		GridPane.setConstraints(b, 0, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(b);
		GridPane.setConstraints(c, 0, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(c);
		
		c.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Welcome w=new Welcome(stage);
			}
		});
		
		Scene scene=new Scene(grd,1200,800);
		stage.setScene(scene);
		stage.show();
	}

}
//class ThreadA extends Thread
//{
//	public ThreadA(Stage stage)
//	{
//	this.stage=stage;	
//	}
//	Stage stage;
//	
//	public void run()
//	{
//		Bye b=new Bye();
//		b.start(stage);
//		try {
//			Thread.sleep(5000);
//		} catch (InterruptedException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	}
//}
//class ThreadB extends Thread
//{
//	public ThreadB(Stage stage)
//	{
//	this.stage=stage;	
//	}
//	Stage stage;
//	public void run()
//	{
//		Welcome w=new Welcome(stage);
//	}
//}
