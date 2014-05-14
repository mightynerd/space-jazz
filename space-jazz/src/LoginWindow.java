import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class LoginWindow extends JFrame implements ActionListener
{
	JTextField tfUserName;
	JTextField tfPasswd;
	
	JLabel lblUserName;
	JLabel lblPasswd;
	
	JButton btnSignIn;
	JButton btnRegister;
	
	GridBagConstraints c;
	
	public LoginWindow()
	{
		tfUserName = new JTextField();
		tfPasswd = new JTextField();
		
		lblUserName = new JLabel("Username:");
		lblPasswd = new JLabel("Password:");
		
		btnSignIn = new JButton("Sign in");
		btnRegister = new JButton("Register");
		
		c = new GridBagConstraints();
		
		setupWindow();
		addComponents();
	}
	
	void setupWindow()
	{
		setVisible(true);
		setLayout(new GridBagLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(200, 150);
		setLocation(400, 200);
		setResizable(false);
		setTitle("Sign in");
		c.insets = new Insets(3, 3, 3, 3);
		c.fill = GridBagConstraints.BOTH;
		
		tfUserName.addActionListener(this);
		tfPasswd.addActionListener(this);
		btnSignIn.addActionListener(this);
		btnRegister.addActionListener(this);
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
	}

	@Override
	public void actionPerformed(ActionEvent arg0)
	{
		
	}
	
	public static void main(String[] args)
	{
		LoginWindow l = new LoginWindow();
	}
}
