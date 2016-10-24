package GUI;

import Java.I_User;
import Java.UserFactory;
import SQL.Connect;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class RegisterUserGUI implements  ActionListener
{
    JPanel buttonPanel;
    JButton cancelButton, registerUserButton;
	JLabel userLabel,passLabel, emailLabel, addressLabel;
	JTextField userField,passField, emailField, addressField;
	static JFrame frame = new JFrame("Register Product Screen");

    public JPanel createContentPane()
	{
        //Make bottom JPanel to place buttonPanel on
        JPanel totalGUI = new JPanel();
        totalGUI.setLayout(null);

        //Make Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(10, 10);
        buttonPanel.setSize(295, 485);
        totalGUI.add(buttonPanel);

		//Make Labels
        userLabel = new JLabel("Username:");
        userLabel.setLocation(0, 0);
        userLabel.setSize(80, 30);
        buttonPanel.add(userLabel);
        
		passLabel = new JLabel("Password:");
		passLabel.setLocation(0, 40);
        passLabel.setSize(80, 30);
        buttonPanel.add(passLabel);
		
		emailLabel = new JLabel("email:");
		emailLabel.setLocation(0, 80);
        emailLabel.setSize(80, 30);
        buttonPanel.add(emailLabel);
        
        addressLabel = new JLabel("Address:");
		addressLabel.setLocation(0,120);
        addressLabel.setSize(80, 30);
        buttonPanel.add(addressLabel);

		//Make texts fields
        userField= new JTextField();
        userField.setLocation(90, 0);
        userField.setSize(180, 30);
        buttonPanel.add(userField);
        
        passField = new JTextField();
        passField.setLocation(90, 40);
        passField.setSize(180, 30);
        buttonPanel.add(passField);

        emailField = new JTextField();
        emailField.setLocation(90, 80);
        emailField.setSize(180, 30);
        buttonPanel.add(emailField);

        addressField = new JTextField();
        addressField.setLocation(90, 120);
        addressField.setSize(180, 30);
        buttonPanel.add(addressField);

		//Make Buttons
		cancelButton = new JButton("Cancel");
        cancelButton.setLocation(0, 160);
        cancelButton.setSize(135, 30);
        cancelButton.addActionListener(this);
        buttonPanel.add(cancelButton);
		
        registerUserButton = new JButton("Register");
        registerUserButton.setLocation(140, 160);
        registerUserButton.setSize(135, 30);
        registerUserButton.addActionListener(this);
        buttonPanel.add(registerUserButton);
        
        totalGUI.setVisible(true);
        return totalGUI;
    }

    public void actionPerformed(ActionEvent e)
	{
        if(e.getSource() == cancelButton)
        {
        	frame.dispose();
        }
        
        else if(e.getSource() == registerUserButton)
        {
			//do shit in here
        	if(userField.getText().equals("") ||passField.getText().equals("") ||emailField.getText().equals("") ||addressField.getText().equals("") ){
        		JOptionPane.showMessageDialog(null,"There is a null field" );
        		return;
        	}
        	
        	String userName = userField.getText();
        	String pass = passField.getText();
        	String email = emailField.getText();
        	String address = addressField.getText();
        		
    		
    		UserFactory userFactory = new UserFactory();
    		
    		I_User user = userFactory.createUser("customer");
    		
    		int id = 1;
    		int accesslvl =1;
    		
    		try{
    			
    			
    			//Java.Connect to database
    			   Connect con = new Connect();
        		   Connection mycon =  con.getconnection();
        		   Statement mystat = mycon.createStatement();
        		   
    			//Get ID for Java.user
    			ResultSet myRe = mystat.executeQuery("select * from users");
    			while (myRe.next()){
    				id++;
    			}
    			
    			//Insert into table
    			String sql = "INSERT into users (idusers ,username,accesslvl ,password,email,address) VALUES('" 
    					 + id  + "','" + userName + "','" + accesslvl+ "','" + pass + "','" + email + "','" + address 
    						+ "');" ;
    			mystat.executeUpdate(sql);
    			
    			//ResultSet myRe = mystat.executeQuery("select * from creationary.users");
    			
    		}
    		catch(Exception exc){
    			System.out.println("Error cant connect to database");
    		}
    		
    		user.setName(userName);;
    		user.setPassword(pass);
    		user.setEmail(email);
    		user.setAddress(address);
    		user.setID(id);
    		
        }
        
    }

    private static void createAndShowGUI()
	{
        //Create and set up the content pane.
    	RegisterUserGUI window = new RegisterUserGUI();
        frame.setContentPane(window.createContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(305, 250);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public static void start()
	{
        SwingUtilities.invokeLater(new Runnable() 
		{
            public void run() 
			{
                createAndShowGUI();
            }
        });
    }
    
   
}
