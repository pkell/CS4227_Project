package GUI;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class LogInScreenGUI extends Panel implements  ActionListener 
{
    JPanel buttonPanel;
    JButton exitButton, logInButton, registerButton;
	JLabel userNameLabel, passwordLabel;
	JTextField userNameTextField;
	JPasswordField passwordTextField;
	static JFrame frame = new JFrame("Log In Screen");

	public LogInScreenGUI(){
		this.panel = new JPanel();
		createAndShowGUI();
	}
	
    public JPanel createContentPane()
	{
        panel.setLayout(null);

        //Make Button Panel
        buttonPanel = new JPanel();
        buttonPanel.setLayout(null);
        buttonPanel.setLocation(10, 10);
        buttonPanel.setSize(295, 185);
        panel.add(buttonPanel);

		//Make Labels
		userNameLabel = new JLabel("Username:");
		userNameLabel.setLocation(0, 0);
        userNameLabel.setSize(80, 30);
        buttonPanel.add(userNameLabel);
		
		passwordLabel = new JLabel("Password:");
		passwordLabel.setLocation(0, 40);
        passwordLabel.setSize(80, 30);
        buttonPanel.add(passwordLabel);
		
		//Username and Password Fields
		userNameTextField = new JTextField();
		userNameTextField.setLocation(90, 0);
        userNameTextField.setSize(180, 30);
        buttonPanel.add(userNameTextField);
		
		passwordTextField = new JPasswordField();
		passwordTextField.setLocation(90, 40);
        passwordTextField.setSize(180, 30);
        buttonPanel.add(passwordTextField);
		
		//Make Buttons
		exitButton = new JButton("Exit");
        exitButton.setLocation(0, 80);
        exitButton.setSize(85, 30);
        exitButton.addActionListener(this);
        buttonPanel.add(exitButton);
		
        logInButton = new JButton("Log In");
        logInButton.setLocation(93, 80);
        logInButton.setSize(85, 30);
        logInButton.addActionListener(this);
        buttonPanel.add(logInButton);
        
        
        registerButton = new JButton("Register");
        registerButton.setLocation(185, 80);
        registerButton.setSize(90, 30);
        registerButton.addActionListener(this);
        buttonPanel.add(registerButton);
        
        panel.setVisible(true);
        return panel;
    }

    public void actionPerformed(ActionEvent e)
	{
        if(e.getSource() == exitButton)
        {
			System.exit(0);
			JOptionPane.showMessageDialog(null, "Register Course Director Window");
        }
        
        else if(e.getSource() == registerButton)
        {
        	panelMgr.getPanelFromFactory(3);
        }
		
        else if(e.getSource() == logInButton)
        {

			//declare variables for username and password
			String userName = userNameTextField.getText();
			String password = passwordTextField.getText();

			try
			{
    			if(help.canUserLogin(userName, password)){
    				help.getUserDetails(userName);
    				frame.setVisible(false);
    				panelMgr.getPanelFromFactory(2);
    				}
    			}

			catch(Exception exc)
			{
				System.out.println(exc.toString());
				System.exit(0);
			}
		}
        
    }

    private void createAndShowGUI()
	{
        //Create and set up the content pane.
        frame.setContentPane(this.createContentPane());

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(305, 165);
		frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    
    private void register(){}//Decorator Pattern to be implemented here
    
    @Override
    public JPanel sendToWindow()
    { 
        return this.panel;
    }
	
    @Override
    public void setPanelManager(PanelManager pm)
    {
	this.panelMgr = pm;
    }
}
