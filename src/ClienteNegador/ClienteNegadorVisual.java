package ClienteNegador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import INegador.INegador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class ClienteNegadorVisual {

	private JFrame frame;
	private JTextField txtUrl;
	private JTextField txtNumero;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClienteNegadorVisual window = new ClienteNegadorVisual();
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
	public ClienteNegadorVisual() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JLabel lblClienteNegador = new JLabel("Cliente Negador");
		lblClienteNegador.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblClienteNegador.setBounds(142, 11, 125, 22);
		frame.getContentPane().add(lblClienteNegador);
		
		JLabel lblNewLabel = new JLabel("URL del objeto:");
		lblNewLabel.setBounds(72, 93, 74, 14);
		frame.getContentPane().add(lblNewLabel);
		
		txtUrl = new JTextField();
		txtUrl.setBounds(167, 90, 116, 20);
		frame.getContentPane().add(txtUrl);
		txtUrl.setColumns(10);
		
		JLabel lblNmeroANegar = new JLabel("N\u00FAmero a negar:");
		lblNmeroANegar.setBounds(72, 157, 91, 14);
		frame.getContentPane().add(lblNmeroANegar);
		
		txtNumero = new JTextField();
		txtNumero.setBounds(167, 154, 116, 20);
		frame.getContentPane().add(txtNumero);
		txtNumero.setColumns(10);
		
		JButton btnNewButton = new JButton("Buscar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				lanegacion();
			}

			
		});
		btnNewButton.setBounds(167, 227, 89, 23);
		frame.getContentPane().add(btnNewButton);
	}
	
	
	private void lanegacion() {
		// TODO Auto-generated method stub
		INegador negador = null;
		int numero;
		numero=Integer.parseInt(txtNumero.getText());

		try
			{
		try {
			negador=(INegador)Naming.lookup(txtUrl.getText());
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			}
		catch(NotBoundException ex)
		{
			System.out.println("El objeto "+txtUrl.getText()+ "no existe en el servidor");
			return;
		}
		catch(MalformedURLException ex)
		{
			System.out.println("URL mal escrita: "+txtUrl.getText());
			return;
		}
		System.out.println("Objeto encontrado...");
		try {
			System.out.println("El negado de "+numero+ "es" + negador.niega(numero));
		} catch (RemoteException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
