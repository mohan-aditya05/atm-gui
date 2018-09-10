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

public class Welcome extends Application 
{

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public Welcome()
	{
		
	}
	public Welcome(Stage stage)
	{
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	Text lblB,lblW,lblI;
	TextField txtA;
	Button ok;
	Connection con;
	PreparedStatement pst;
	GridPane grd;
	
	public void start(Stage stage)throws Exception
	{
		lblB=new Text("ABCD Bank");
		lblB.setFont(Font.font("Arial",FontWeight.BLACK,40));
		lblB.setFill(Color.CORAL);
		
		lblW=new Text("WELCOME!");
		lblW.setFont(Font.font("Arial",FontWeight.BLACK,30));
		lblW.setFill(Color.CORAL);
		
		lblI=new Text("Insert Card");
		lblI.setFont(Font.font("Arial",FontWeight.BLACK,30));
		lblI.setFill(Color.CORAL);
		
		txtA=new TextField();
		txtA.setPromptText("Enter 16 digit Account No.");
		
		ok=new Button("OK");
		ok.setPrefSize(100, 50);
		
		grd=new GridPane();
		grd.setVgap(40);
		grd.setHgap(5);
		grd.setGridLinesVisible(false);
		grd.setPadding(new Insets(100,0,0,300));
		
		GridPane.setConstraints(lblB, 1, 0, 1, 1, HPos.CENTER, VPos.CENTER, null	, null, new Insets(10));
		grd.getChildren().add(lblB);
		
		GridPane.setMargin(lblW, new Insets(10));
		grd.add(lblW, 1, 1);
		
		GridPane.setMargin(lblI, new Insets(10));
		grd.add(lblI, 1, 2);
		
		GridPane.setMargin(txtA, new Insets(10));
		grd.add(txtA, 1, 3);
		
		GridPane.setMargin(ok, new Insets(10));
		grd.add(ok, 1, 4);
		
		ok.setFocusTraversable(true);
		
		Image img=new Image(Welcome.class.getResourceAsStream("female-billionaires-statistics.jpg"));
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
		
		try{
			Class.forName("com.mysql.jdbc.Driver");
			con=DriverManager.getConnection("jdbc:mysql://localhost:3306/java","root","tommy");
			System.out.println("Connected!");
		}
		catch(ClassNotFoundException e)
		{
			e.printStackTrace();
		}
		
		ok.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				try {
					pst=con.prepareStatement("select * from AccountDetails where Id=?");
					pst.setString(1,txtA.getText());
					ResultSet rst=pst.executeQuery();
					if(rst.next())
					{
						User u=new User(txtA.getText(),stage);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		});
		
		Scene scene=new Scene(grd,1000,800);
		stage.setScene(scene);
		stage.show();
		
	}

}
