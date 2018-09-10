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
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.BorderStrokeStyle;
import javafx.scene.layout.BorderWidths;
import javafx.scene.layout.CornerRadii;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Show extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public Show()
	{
		
	}
	public Show(String id,Stage stage)
	{
		this.sid=id;
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String sid;
	Text title,id,name,rmn,bName,ifsc;
	TextArea add;
	TextField tId,tName,tRmn,tBname,tIfsc;
	Button ok;
	GridPane grd;
	Connection con;
	PreparedStatement pst;
	public void start(Stage stage) throws Exception
	{
		title=new Text("Account Details");
		title.setFont(Font.font("Calibri",FontWeight.BOLD,50));
		title.setFill(Color.CORAL);
		title.setUnderline(true);
		
		id=new Text("Account Id:");
		id.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		id.setFill(Color.CORAL);
		
		name=new Text("Name:");
		name.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		name.setFill(Color.CORAL);
		
		rmn=new Text("Reg. Mobile");
		rmn.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		rmn.setFill(Color.CORAL);
		
		bName=new Text("Branch Name:");
		bName.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		bName.setFill(Color.CORAL);
		
		ifsc=new Text("IFSC:");
		ifsc.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		ifsc.setFill(Color.CORAL);
		
		tId=new TextField();
		tId.setEditable(false);
		
		tName=new TextField();
		tName.setEditable(false);
		
		tRmn=new TextField();
		tRmn.setEditable(false);
		
		tBname=new TextField();
		tBname.setEditable(false);
		
		tIfsc=new TextField();
		tIfsc.setEditable(false);
		
		ok=new Button("OK");
		ok.setPrefSize(100, 50);
		
		add=new TextArea();
		add.setEditable(false);
		
		grd=new GridPane();
		grd.setVgap(20);
		grd.setHgap(10);
		grd.setPadding(new Insets(100,0,0,300));
		grd.setGridLinesVisible(false);
		grd.setBorder(new Border(new BorderStroke(Color.CORAL, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
		
		Image img=new Image(User.class.getResourceAsStream("female-billionaires-statistics.jpg"));
		BackgroundImage bI=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		grd.setBackground(new Background(bI));
		
		GridPane.setConstraints(title, 0, 0, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(title);
		GridPane.setConstraints(id, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(id);
		GridPane.setConstraints(name, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(name);
		GridPane.setConstraints(rmn, 1, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(rmn);
		GridPane.setConstraints(bName, 1, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(bName);
		GridPane.setConstraints(ifsc, 1, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(ifsc);
		GridPane.setConstraints(tId, 2, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tId);
		GridPane.setConstraints(tName, 2, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tName);
		GridPane.setConstraints(tRmn, 2, 3, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tRmn);
		GridPane.setConstraints(tBname, 2, 4, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tBname);
		GridPane.setConstraints(tIfsc, 2, 5, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tIfsc);
		GridPane.setConstraints(ok, 2, 6, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
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
		
		try{
			pst=con.prepareStatement("select * from AccountDetails where Id=?");
			pst.setString(1, sid);
			ResultSet rst=pst.executeQuery();
			if(rst.next())
			{
				tId.setText(sid);
				tBname.setText(rst.getString("bName"));
				tIfsc.setText(rst.getString("ifsc"));
				tName.setText(rst.getString("Name"));
				tRmn.setText(String.valueOf(rst.getInt("Rmn")));
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
