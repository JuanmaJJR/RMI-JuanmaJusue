package ServidorNegador;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;

import INegador.INegador;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.rmi.AccessException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.awt.event.ActionEvent;

public class ServidorNegadorVisual implements INegador {

	private JFrame frame;
	private JTextField txtNombre;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ServidorNegadorVisual window = new ServidorNegadorVisual();
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
	public ServidorNegadorVisual() {
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
		
		JLabel lblServidorNegadorVisual = new JLabel("Servidor Negador");
		lblServidorNegadorVisual.setFont(new Font("Tahoma", Font.PLAIN, 18));
		lblServidorNegadorVisual.setBounds(137, 11, 148, 22);
		frame.getContentPane().add(lblServidorNegadorVisual);
		
		JLabel lblNombre = new JLabel("Nombre:");
		lblNombre.setBounds(98, 124, 46, 14);
		frame.getContentPane().add(lblNombre);
		
		txtNombre = new JTextField();
		txtNombre.setBounds(199, 121, 86, 20);
		frame.getContentPane().add(txtNombre);
		txtNombre.setColumns(10);
		
		JButton btnAceptar = new JButton("Aceptar");
		btnAceptar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					buscar();
				} catch (AccessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (RemoteException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}

			
			
		});
		btnAceptar.setBounds(164, 227, 89, 23);
		frame.getContentPane().add(btnAceptar);
	}
	private void buscar() throws AccessException, RemoteException {
		// TODO Auto-generated method stub
		System.out.println("Intentando exportar el objeto...");
		INegador n=(INegador)new ServidorNegadorVisual();
		UnicastRemoteObject.exportObject(n);


		//Buscamos el registro para registrar el objeto remoto exportado



		System.out.println("Objeto exportado \n buscando el registro de RMI...");
		Registry r= LocateRegistry.getRegistry(Registry.REGISTRY_PORT);
		r.rebind(txtNombre.getText(),n);
		System.out.println("Objeto registrado \n Esperando conexiones...");
		
	}
	
	public int niega(int numero) throws RemoteException
	{
		System.out.println("Se pidio negar" +numero);
		return -numero;
	}


}
