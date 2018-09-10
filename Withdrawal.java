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

public class Withdrawal extends Application {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		launch(args);
	}
	public Withdrawal()
	{
		
	}
	public Withdrawal(String id,Stage stage)
	{
		this.id=id;
		try {
			start(stage);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	String id;
	Text eId,w,title;
	TextField teId,tw;
	Button ok,cancel;
	GridPane grd;
	Connection con;
	PreparedStatement pst,qst;
	public void start(Stage stage)throws Exception
	{
		title=new Text("Cash Withdrawal");
		title.setFont(Font.font("Calibri",FontWeight.BOLD,50));
		title.setFill(Color.CORAL);
		title.setUnderline(true);
		
		eId=new Text("Account Id:");
		eId.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		eId.setFill(Color.CORAL);
		
		w=new Text("Enter Amount to be Withdrawn:");
		w.setFont(Font.font("Calibri",FontWeight.BOLD,30));
		w.setFill(Color.CORAL);
		
		teId=new TextField();
		teId.setAlignment(Pos.CENTER);
		teId.setEditable(false);
		
		tw=new TextField();
		tw.setPromptText("Enter Amount in multiples of 100");
		tw.setPrefSize(100, 50);
		tw.setAlignment(Pos.CENTER);
		
		ok=new Button("OK");
		ok.setPrefSize(100, 50);
		
		cancel=new Button("Cancel");
		cancel.setPrefSize(100, 50);
		
		grd=new GridPane();
		grd.setHgap(10);
		grd.setVgap(20);
		grd.setPadding(new Insets(100,0,0,300));
		grd.setGridLinesVisible(false);
		grd.setBorder(new Border(new BorderStroke(Color.CORAL, 
	            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, BorderWidths.FULL)));
		
		Image img=new Image(User.class.getResourceAsStream("female-billionaires-statistics.jpg"));
		BackgroundImage bI=new BackgroundImage(img, BackgroundRepeat.REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, BackgroundSize.DEFAULT);
		grd.setBackground(new Background(bI));
		
		GridPane.setConstraints(title, 0, 0, 4, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(title);
		GridPane.setConstraints(eId, 1, 1, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(eId);
		GridPane.setConstraints(teId, 1, 2, 1, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(teId);
		GridPane.setConstraints(w, 1, 3, 1, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(w);
		GridPane.setConstraints(tw, 1, 5, 1, 2, HPos.CENTER, VPos.CENTER, null, null, new Insets(10));
		grd.getChildren().add(tw);
		
		
		HBox h=new HBox();
		h.setSpacing(100);
		h.getChildren().addAll(ok,cancel);
		GridPane.setConstraints(h, 1, 7, 2, 1, HPos.CENTER, VPos.CENTER, null, null, new Insets(10,20,10,50));
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
		
		cancel.addEventHandler(MouseEvent.MOUSE_ENTERED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				cancel.setEffect(shadow);
			}
		});
		
		cancel.addEventHandler(MouseEvent.MOUSE_EXITED,new EventHandler<MouseEvent>(){
			public void handle(MouseEvent e)
			{
				cancel.setEffect(null);
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
					pst=con.prepareStatement("select * from Balance where Id=?");
					pst.setString(1, id);
					ResultSet rst=pst.executeQuery();
					if(rst.next())
					{
						int i=rst.getInt("balance");
						int j=Integer.parseInt(tw.getText());
						if(i>j)
						{
							qst=con.prepareStatement("update Balance set balance=? where Id=?");
							qst.setInt(1, i-j);
							qst.setString(2, id);
							qst.executeUpdate();
							Alert alert1=new Alert(AlertType.CONFIRMATION);
							alert1.setTitle("ABCD Bank Server");
							alert1.setContentText("Transaction Successful! Please Collect your Cash.");
							alert1.show();
							Bye b=new Bye(stage);
						}
						else
						{
							Alert alert1=new Alert(AlertType.ERROR);
							alert1.setTitle("ABCD Bank Server");
							alert1.setContentText("Insufficient Funds!");
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
		
		cancel.setOnAction(new EventHandler<ActionEvent>() {
			
			@Override
			public void handle(ActionEvent event) {
				// TODO Auto-generated method stub
				Bye b=new Bye(stage);
			}
		});
		
		teId.setText(id);
		
		Scene scene=new Scene(grd,1200,800);
		stage.setScene(scene);
		stage.show();
		
		
	}

}
