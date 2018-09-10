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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert.AlertType;
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
import javafx.scene.text.FontSmoothingType;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Pin extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public Pin(String id, Bal b,Stage stage)
	{
		i=0;
		this.id=id;
		System.out.println(id);
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Pin(String id, Withdrawal w,Stage stage)
	{
		i=1;
		this.id=id;
		System.out.println(id);
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Pin(String id, Transfer t,Stage stage)
	{
		i=2;
		this.id=id;
		System.out.println(id);
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public Pin(String id, Show s,Stage stage)
	{
		i=3;
		this.id=id;
		System.out.println(id);
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String id;
	int i;
	Text pin;
	PasswordField pw;
	Button ok;
	GridPane grd;
	Connection con;
	PreparedStatement pst;
	public void start(Stage stage)throws Exception
	{
		pin=new Text("Enter 4-digit PIN");
		pin.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		pin.setFill(Color.CORAL);
		pin.setUnderline(true);
		pin.setFontSmoothingType(FontSmoothingType.LCD);
		
		pw=new PasswordField();
		pw.setAlignment(Pos.CENTER);
		pw.setPrefSize(100, 50);
		
		ok=new Button("OK");
		ok.setPrefSize(100, 50);
		
		grd=new GridPane();
		grd.setHgap(10);
		grd.setVgap(40);
		grd.setPadding(new Insets(100,0,0,400));
		grd.setGridLinesVisible(false);
		
		GridPane.setConstraints(pin, 1, 0, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(pin);
		GridPane.setConstraints(pw, 1, 1, 2, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(pw);
		GridPane.setConstraints(ok, 1, 3, 2, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(ok); 
		
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
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","tommy");
			System.out.println("Connected!");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		Image img=new Image(User.class.getResourceAsStream("female-billionaires-statistics.jpg"));
		BackgroundImage bI=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		grd.setBackground(new Background(bI));
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					pst=con.prepareStatement("select pin from Pin where Id=?");
					pst.setString(1, id);
					ResultSet rst=pst.executeQuery();
					if(rst.next())
					{
						String pin=String.valueOf(rst.getInt("pin"));
						if(pin.equals(pw.getText()))
						{
							if(i==0)
							{
								Bal b=new Bal(id,stage);
							}
							if(i==2)
							{
								Transfer b=new Transfer(id,stage);
							}
							if(i==1)
							{
								Withdrawal b=new Withdrawal(id,stage);
							}
							if(i==3)
							{
								Show b=new Show(id,stage);
							}
						}
						else
						{
							Alert alert1=new Alert(AlertType.WARNING);
							alert1.setTitle("ABCD Bank Server");
							alert1.setContentText("Wrong PIN! Try Again.");
							alert1.show();
							Bye b=new Bye(stage);
						}
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
				
			}
		});
		
		Scene scene=new Scene(grd,1200,800);
		stage.setScene(scene);
		stage.show();
		
		
	}

}
