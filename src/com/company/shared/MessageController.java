package com.company.shared;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;
import java.util.List;



/**
 * Coded by Ferry on 8-11-2016.
 */
public class MessageController extends UnicastRemoteObject implements IMessageController {
	private List<Message> allMessages;

	public MessageController() throws RemoteException{
		this.allMessages = new ArrayList<>();
	}

	@Override
	public List<Message> getMessages(User user) {
		if(allMessages.size() == 0){
			return null;
		}

		List<Message> messages = new ArrayList<>();
		for(Message message : allMessages){
			for(User receiveUser : message.getReceivers()){
				if(receiveUser.getName().equals(user.getName())){
					messages.add(message);
				}
			}
		}

		if(messages.size() == 0){
			return null;
		}

		return messages;



	}

	@Override
	public void sendMessage(Message message) {
		allMessages.add(message);
	}
}
