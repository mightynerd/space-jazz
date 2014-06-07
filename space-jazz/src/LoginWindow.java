import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JTextField;


public class LoginWindow extends JFrame implements ActionListener
{
	JTextField tfUserName;
	JPasswordField tfPasswd;
	
	JLabel lblUserName;
	JLabel lblPasswd;
	
	JButton btnSignIn;
	JButton btnRegister;
	
	JCheckBox chkFullscreen;
	
	GridBagConstraints c;
	
	public LoginWindow()
	{
		tfUserName = new JTextField();
		tfPasswd = new JPasswordField();
		
		lblUserName = new JLabel("Username:");
		lblPasswd = new JLabel("Password:");
		
		btnSignIn = new JButton("Sign in");
		btnRegister = new JButton("Register");
		
		chkFullscreen = new JCheckBox("Fullscreen");
		
		c = new GridBagConstraints();
		
		setupWindow();
		addComponents();
	}
	
	void setupWindow()
	{
		System.out.println("os.name:" + System.getProperty("os.name", "generic").toLowerCase());
		if (System.getProperty("os.name", "generic").toLowerCase().contains("win"))
		{
			//Running windows
			System.out.println("Running on Windows");
			setResizable(false);
		}
		else if (System.getProperty("os.name", "generic").toLowerCase().contains("nux"))
		{
			//Linux-like
			System.out.println("Running on Linux");
			//Must be resizable for some reason
			setResizable(true);
			//Fullscreen doesn't work
			chkFullscreen.setEnabled(false);
		}
		else if (System.getProperty("os.name", "generic").toLowerCase().contains("mac"))
		{
			//OSX
			System.out.println("Running on OS X");
			//Must be resizable for some reason
			setResizable(true);
			//Fullscreen doesn't work
			chkFullscreen.setEnabled(false);
		}
		else
		{
			System.out.println("Running on unknown/unsupported OS, using compability mode (everything enabled)");
		}
		
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 150);
		setLocation(400, 200);
		setTitle("Space-Jazz");
		//For the layout
		c.insets = new Insets(3, 3, 3, 3);
		c.fill = GridBagConstraints.BOTH;
		
		//ActionListeners on the textFields makes it possible to press return to sign in
		tfUserName.addActionListener(this);
		tfPasswd.addActionListener(this);
		btnSignIn.addActionListener(this);
		btnRegister.addActionListener(this);
		
		setVisible(true);
	}
	
	void addComponents()
	{
		c.gridx = 0;
		c.gridy = 0;
		add(lblUserName, c);
		
		c.gridx = 1;
		add(tfUserName, c);
		
		c.gridy = 1;
		c.gridx = 0;
		add(lblPasswd, c);
		
		c.gridx = 1;
		add(tfPasswd, c);
		
		c.gridy = 2;
		c.gridx = 0;
		add(btnSignIn, c);
		
		c.gridx = 1;
		add(btnRegister, c);
		
		c.gridx = 0;
		c.gridy = 3;
		add(chkFullscreen, c);
	}

	@Override
	public void actionPerformed(ActionEvent ae)
	{
		//Register button pressed
		if (ae.getSource().equals(btnRegister))
		{
			//For debug
			System.out.println("btnRegister");
			System.out.println("username: " + tfUserName.getText() + " password: " + String.valueOf(tfPasswd.getPassword()));
			
			//If there are no username or password it will create a message box
			if (tfUserName.getText().equals("") || String.valueOf(tfPasswd.getPassword()).equals(""))
			{
				System.out.println("Username or password empty!");
				JOptionPane.showMessageDialog(null, "You have to write both a password and a username!");
			}
			
			else
			{
				//File manager that can read and the users file
				FileManager f = new FileManager();
				//The list of users read from the file
				Users users = f.readFile();

				//If the user doesn't exists it adds it
				if (!users.userExists(tfUserName.getText()))
				{
					//Adds a new user to the list of users in users
					users.users.add(new User(tfUserName.getText(), String.valueOf(tfPasswd.getPassword())));
					//Rewrites the file
					f.writeFile(users);
					System.out.println("User added");
					JOptionPane.showMessageDialog(null, "Your account has been registered. You may now sign in.");
				}
				
				//If it exists it creates a message box
				else
				{
					JOptionPane.showMessageDialog(null, "User already exists!");
				}
			}
		}
		
		//Sign in
		else if (ae.getSource().equals(btnSignIn) || ae.getSource().equals(tfUserName) || ae.getSource().equals(tfPasswd))
		{
			//Debug
			System.out.println("btnSignIn");
			System.out.println("username: " + tfUserName.getText() + " password: " + String.valueOf(tfPasswd.getPassword()));
			
			//If there are no username or password it will create a message box
			if (tfUserName.getText().equals("") || String.valueOf(tfPasswd.getPassword()).equals(""))
			{
				System.out.println("Username or password empty");
				JOptionPane.showMessageDialog(null, "You have to write both a password and a username");
			}
			
			else
			{
				FileManager f = new FileManager();
				Users users = f.readFile();
				
				//Checks if the user exists and has the correct password
				if (users.correctPassword(tfUserName.getText(), String.valueOf(tfPasswd.getPassword())))
				{
					//Creates a new game
					MainWindow m = new MainWindow(users.getUser(tfUserName.getText()), chkFullscreen.isSelected());

					m.requestFocusInWindow();
					//Closes this screen
					dispose();
				}
				
				//If the username or password is invalid.
				else
				{
					JOptionPane.showMessageDialog(null, "Username or password incorrect");
				}
			}
		}
	}
	//Debug
	public static void main(String[] args)
	{
		LoginWindow l = new LoginWindow();
	}
}
