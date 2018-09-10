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
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.input.MouseEvent;
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
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

public class Transfer extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public Transfer()
	{
		
	}
	public Transfer(String id,Stage stage)
	{
		System.out.println("In Transfer");
		this.id=id;
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String id;
	Text eA,eId,title;
	TextField teA,teId;
	Button ok,refresh;
	GridPane grd;
	Connection con;
	PreparedStatement pst,qst,fst,ast;
	public void start(Stage stage) throws Exception
	{
		title=new Text("Transfer Money");
		title.setFont(Font.font("Calibri",FontWeight.BOLD,50));
		title.setFill(Color.CORAL);
		title.setUnderline(true);
		
		eA=new Text("Enter Amount to be Transferred:");
		eA.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		eA.setFill(Color.CORAL);
		
		eId=new Text("Enter Account Id:");
		eId.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		eId.setFill(Color.CORAL);
		
		teA=new TextField();
		teA.setEditable(true);
		
		teId=new TextField();
		teId.setEditable(true);
		
		ok=new Button("OK");
		ok.setPrefSize(100, 50);
		
		refresh=new Button("Refresh");
		refresh.setPrefSize(100, 50);
		
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
		GridPane.setConstraints(eA, 1, 1, 2, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(eA);
		GridPane.setConstraints(teA, 1, 3, 2, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(teA);
		GridPane.setConstraints(eId, 1, 5, 2, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(eId);
		GridPane.setConstraints(teId, 1, 7, 2, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(teId);
		
		HBox h=new HBox();
		h.setSpacing(100);
		h.getChildren().addAll(ok,refresh);
		GridPane.setConstraints(h, 1, 9, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10,20,10,50));
		grd.getChildren().add(h);
		
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
		
		refresh.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				refresh.setEffect(shadow);
			}
		});
		
		refresh.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				refresh.setEffect(null);
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
				try{
					pst=con.prepareStatement("select * from Balance where Id=?");
					pst.setString(1, id);
					ResultSet rst=pst.executeQuery();
					if(rst.next())
					{
						int i=Integer.parseInt(teA.getText());
						int j=rst.getInt("balance");
						if(i<=j)
						{
							qst=con.prepareStatement("select * from Balance where Id=?");
							qst.setString(1, teId.getText());
							ResultSet sst=qst.executeQuery();
							if(sst.next())
							{
								int k=sst.getInt("Balance");
								fst=con.prepareStatement("update Balance set balance=? where Id=?");
								fst.setInt(1, k+i);
								fst.setString(2,teId.getText());
								ast=con.prepareStatement("update Balance set balance=? where Id=?");
								ast.setInt(1,j-i);
								ast.setString(2, id);
								fst.executeUpdate();
								ast.executeUpdate();
								Alert alert=new Alert(AlertType.CONFIRMATION);
								alert.setTitle("ABCD Bank Server");
								alert.setContentText("Transaction Successful!");
								alert.show();
								Bye b=new Bye(stage);
							}
							else
							{
								Alert alert=new Alert(AlertType.ERROR);
								alert.setTitle("ABCD Bank Server");
								alert.setContentText("No Account Found!");
								alert.show();
								Bye b=new Bye(stage);
							}
						}
						else
						{
							Alert alert1=new Alert(AlertType.WARNING);
							alert1.setTitle("ABCD Bank Server");
							alert1.setContentText("Insufficient Funds!");
							alert1.show();
							Bye b=new Bye(stage);
						}
					}
				}
				catch(SQLException e)
				{
					e.printStackTrace();
				}
			}
		});
		
		refresh.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				teA.clear();
				teId.clear();
			}
		});
		
		Scene scene=new Scene(grd,1200,800);
		stage.setScene(scene);
		stage.show();
		
		
		
		
		
	}
}
