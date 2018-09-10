import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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

public class Bal extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	Text id,bal;
	TextField tid,tbal;
	Button ok;
	GridPane grd;
	Connection con;
	PreparedStatement pst;
	String sid;
	public Bal()
	{
		
	}
	public Bal(String id,Stage stage)
	{	
		System.out.println("In Balance");
		this.sid=id;
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void start(Stage stage) throws Exception
	{
		id=new Text("Account Id:");
		id.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		id.setFill(Color.CORAL);
		
		bal=new Text("Avail. Balance");
		bal.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		bal.setFill(Color.CORAL);
		
		tid=new TextField();
		tid.setPrefSize(200,50);
		tid.setEditable(false);
		
		tbal=new TextField();
		tbal.setPrefSize(200,50);
		tbal.setEditable(false);
		
		ok=new Button("OK");
		ok.setPrefSize(100,50);
		
		grd=new GridPane();
		grd.setHgap(10);
		grd.setVgap(20);
		grd.setPadding(new Insets(200,0,0,300));
		
		Image img=new Image(User.class.getResourceAsStream("female-billionaires-statistics.jpg"));
		BackgroundImage bI=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		grd.setBackground(new Background(bI));
		
		DropShadow shadow=new DropShadow();
		shadow.setOffsetY(5.0);
	    shadow.setOffsetX(5.0);
	    shadow.setColor(Color.BLACK);
		ok.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				ok.setEffect(shadow);
			}
		});
		
		ok.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				ok.setEffect(null);
			}
		
		});
		
		tid.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				tid.setEffect(shadow);
			}
		});
		
		tid.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				tid.setEffect(null);
			}
		
		});
		
		tbal.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				tbal.setEffect(shadow);
			}
		});
		
		tbal.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				tbal.setEffect(null);
			}
		
		});
		
		GridPane.setConstraints(id, 0, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(id);
		GridPane.setConstraints(tid, 2, 0, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tid);
		GridPane.setConstraints(bal, 0, 1, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(bal);
		GridPane.setConstraints(tbal, 2,1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tbal);
		GridPane.setConstraints(ok, 1, 2, 3, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(ok);
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","tommy");
			System.out.println("Connected!");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		tid.setAlignment(Pos.CENTER);
		tid.setText(sid);
		
		try{
			pst=con.prepareStatement("select balance from Balance where Id=?");
			pst.setString(1, sid);
			ResultSet rst=pst.executeQuery();
			if(rst.next())
			{
				tbal.setAlignment(Pos.CENTER);
				tbal.setText("Rs."+Integer.toString(rst.getInt("balance")));
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Bye b=new Bye(stage);
			}
		});
		
		Scene scene=new Scene(grd,1200,800);
		stage.setScene(scene);
		stage.show();
		
	}

}
