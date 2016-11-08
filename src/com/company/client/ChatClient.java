package com.company.client;

import com.company.shared.IMessageController;
import com.company.shared.Message;
import com.company.shared.User;

import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Coded by Ferry on 8-11-2016.
 */
public class ChatClient {
    private static String ipAddress;
    private static int portNumber;
    private static String bindingName;
    private static IMessageController messageController;
    private static User loggedInUser;
    private static List<Message> sentMessages = new ArrayList<>();

    public static void main(String[] args)
    {
        bindingName = "chatClient";
        portNumber = 1099;
        ipAddress = "145.93.52.185";
        loggedInUser = new User(1, "Ferry");

        try {
            Registry registry = LocateRegistry.getRegistry(ipAddress, portNumber);

            messageController = (IMessageController) registry.lookup(bindingName);

        } catch(RemoteException | NotBoundException ex) {
            ex.printStackTrace();
        }

        System.out.println("Hey There!" + "\n"
                + "Enter a number!" + "\n"
                + "1: Show Users sent messages" + "\n"
                + "2: Show Users received messages" + "\n"
                + "3: Sent a message!"
                + "Enter your choice: ");

        while (true) {
                Scanner scanner = new Scanner(System.in);
                int num = scanner.nextInt();
                switch (num) {
                    case 1:
                        if(sentMessages.size() == 0){
                            System.out.println("There are no sent messages!");
                            break;
                        }

                        for(Message message : sentMessages){
                            System.out.println(message.toString());
                        }
                        break;
                    case 2:
                        try {
                            for(Message message : messageController.getMessages(loggedInUser)){
                                System.out.println(message.toString() + "\n");
                            }
                        } catch (NullPointerException | RemoteException ex2 ){
                            System.out.println("There are no received messages!");
                        }

                        break;
                    case 3:
                        System.out.println("Enter your message and then the name of the receiving user :");
                        scanner.nextLine();
                        String message = scanner.nextLine();
                        String userName = scanner.nextLine();

                        ArrayList<User> receivers = new ArrayList<>();
                        receivers.add(new User(2, userName));

                        Message toBeSentMessage = new Message(loggedInUser, receivers, message);

                        System.out.println("Message sent: \n" + toBeSentMessage.toString());

                        try
                        {
                            messageController.sendMessage(toBeSentMessage);
                        } catch (RemoteException e)
                        {
                            System.out.println("Sending message failed!");
                        }

                        sentMessages.add(toBeSentMessage);
                        break;
                    default:
                        System.out.println("Please enter a valid choice!");
                        break;
                }
        }
    }
}
