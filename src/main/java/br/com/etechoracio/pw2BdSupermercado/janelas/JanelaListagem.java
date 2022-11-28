package br.com.etechoracio.pw2BdSupermercado.janelas;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.GridLayout;
import javax.swing.JButton;

public class JanelaListagem {

	private JFrame frame;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JanelaListagem window = new JanelaListagem();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public JanelaListagem() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		this.frame = new JFrame();
		this.frame.setBounds(100, 100, 450, 300);
		this.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.frame.getContentPane().setLayout(new GridLayout(1, 0, 0, 0));
		
		this.btnNewButton_1 = new JButton("New button");
		this.frame.getContentPane().add(this.btnNewButton_1);
		
		this.btnNewButton = new JButton("New button");
		this.frame.getContentPane().add(this.btnNewButton);
	}

}
