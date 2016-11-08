package com.company.shared;

import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.List;

/**
 * Coded by Ferry on 8-11-2016.
 */
public interface IMessageController extends Remote {
	List<Message> getMessages(User user) throws RemoteException;
	void sendMessage(Message message) throws RemoteException;
}
