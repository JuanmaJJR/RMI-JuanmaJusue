package INegador;

import java.rmi.*;
public interface INegador extends Remote
{
	public int niega(int numero) throws RemoteException;
}
