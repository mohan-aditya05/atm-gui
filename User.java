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

public class User extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	
	public User(String id,Stage stage)
	{
		System.out.println(id);
		this.id=id;
		System.out.println(this.id);
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String id;
	Button bal,with,trans,show,exit;
	Text user,welcome;
	TextField userName;
	GridPane grd;
	Connection con;
	PreparedStatement pst;
	public void start(Stage stage) throws Exception
	{
		bal=new Button("Balance Enquiry");
		with=new Button("Cash Withdrawal");
		trans=new Button("Transfer to other Account");
		show=new Button("Show A/C Details");
		exit=new Button("Exit");
		bal.setPrefSize(300	, 40);
		with.setPrefSize(300	, 40);
		trans.setPrefSize(300	, 40);
		show.setPrefSize(300	, 40);
		exit.setPrefSize(300	, 40);
		
		user=new Text("Consumer Portal");
		user.setFont(Font.font("Arial",FontWeight.BLACK,40));
		user.setFill(Color.CORAL);
		user.setUnderline(true);
		
		welcome=new Text("Welcome:");
		welcome.setFont(Font.font("Arial",FontWeight.BLACK,20));
		welcome.setFill(Color.CORAL);
		
		userName=new TextField();
		userName.setEditable(false);
		
		grd=new GridPane();
		grd.setHgap(10);
		grd.setVgap(20);
		grd.setPadding(new Insets(100,0,0,300));
		grd.setGridLinesVisible(false);
		
		Image img=new Image(User.class.getResourceAsStream("female-billionaires-statistics.jpg"));
		BackgroundImage bI=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		grd.setBackground(new Background(bI));
		
		GridPane.setConstraints(user, 1, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(user);
		GridPane.setConstraints(welcome,0,1,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(welcome);
		GridPane.setConstraints(userName,1,1,1,1,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(userName);
		GridPane.setConstraints(bal,1,3,1,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(bal);
		GridPane.setConstraints(with,1,5,1,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(with);
		GridPane.setConstraints(trans,1,7,1,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(trans);
		GridPane.setConstraints(show,1,9,1,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(show);
		GridPane.setConstraints(exit,1,11,1,2,HPos.CENTER,VPos.CENTER,null,null,new Insets(10));
		grd.getChildren().add(exit);
		
		DropShadow shadow=new DropShadow();
		shadow.setOffsetY(5.0);
	    shadow.setOffsetX(5.0);
	    shadow.setColor(Color.BLACK);
		bal.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				bal.setEffect(shadow);
			}
		});
		
		bal.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				bal.setEffect(null);
			}
		
		});
		
		trans.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				trans.setEffect(shadow);
			}
		});
		
		trans.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				trans.setEffect(null);
			}
		
		});
		with.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				with.setEffect(shadow);
			}
		});
		
		with.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				with.setEffect(null);
			}
		
		});
		
		show.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				show.setEffect(shadow);
			}
		});
		
		show.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				show.setEffect(null);
			}
		
		});
		exit.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				exit.setEffect(shadow);
			}
		});
		
		exit.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				exit.setEffect(null);
			}
		
		});
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","tommy");
			System.out.println("Connected!");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		try{
			pst=con.prepareStatement("select Name from AccountDetails where Id=?");
			pst.setString(1, id);
			ResultSet rst=pst.executeQuery();
			if(rst.next())
			{
				String name=rst.getString("Name");
				System.out.println(name);
				userName.setText(name);
			}
		}
		catch(SQLException e)
		{
			e.printStackTrace();
		}
		
		bal.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Bal b=new Bal();
				Pin p=new Pin(id,b,stage);
			}
		});
		
		with.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Withdrawal w=new Withdrawal();
				Pin p=new Pin(id,w,stage);
			}
		});
		
		trans.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Transfer t=new Transfer();
				Pin p=new Pin(id,t,stage);
			}
		});
		
		show.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Show s=new Show();
				Pin p=new Pin(id,s,stage);
			}
		});
		exit.setOnAction(new EventHandler<ActionEvent>() {
			
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
