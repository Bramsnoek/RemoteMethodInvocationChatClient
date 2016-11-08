package com.company.server;

import com.company.shared.IMessageController;
import com.company.shared.MessageController;
import com.company.util.ConsoleColor;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

/**
 * Coded by Ferry on 8-11-2016.
 */
public class ChatServer {
	private static final int portNumber = 1099;
	private static final String bindingName = "chatClient";
	private Registry registry;
	private static IMessageController messageController;

	public ChatServer() {
		try {
			messageController = new MessageController();
			System.out.println(ConsoleColor.ANSI_GREEN + "Server: messageController created.");
		} catch (RemoteException e) {
			System.out.println(ConsoleColor.ANSI_RED + "Server: Cannot create car production");
			System.out.println(ConsoleColor.ANSI_RED + "Server: Remote Exception: " + e.getMessage());
		}

		try {
			registry = LocateRegistry.createRegistry(portNumber);
			System.out.println(ConsoleColor.ANSI_GREEN + "Server: Registry created on port number: " + portNumber + ".");
		} catch (RemoteException e) {
			System.out.println(ConsoleColor.ANSI_RED + "Server: Cannot create registry");
			System.out.println(ConsoleColor.ANSI_RED + "Server: Remote Exception: " + e.getMessage());
		}

		try {
			registry.rebind(bindingName, messageController);
			System.out.println(ConsoleColor.ANSI_GREEN + "Server: Bound messageController to registry");
		} catch (RemoteException e) {
			System.out.println(ConsoleColor.ANSI_RED + "Server: Cannot bind messageController");
			System.out.println(ConsoleColor.ANSI_RED + "Server: Remote Exception: " + e.getMessage());
		}
	}

	public static void main(String[] args) {
		System.out.println(ConsoleColor.ANSI_GREEN + "Server started using registry.");
		ChatServer chatServer = new ChatServer();
	}
}
