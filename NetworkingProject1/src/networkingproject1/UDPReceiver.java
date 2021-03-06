/*
    This class creates the UDPReceiver that receives
    messages from a UDPSender. It also handles printing the 
    received message to the GUI and the received message
    file, as well as printing to the GUI where the message 
    came from.
*/

package networkingproject1;

//This is the UDP Receiver		
import java.net.*;
import java.io.*;
import java.util.*;

public class UDPReceiver extends Thread { 
    
    private String message = "";
    private String sender = "";
    
    //create an empty constructor for the receiver object
    public UDPReceiver() {
        
    } 
    
    //override the run method with the given UPDReceiver code
    @Override
    public void run () {
        
        try{
            
            // Create a datagram socket, bound to the specific port 2000
            DatagramSocket socket = new DatagramSocket(2000);

            System.out.println ("Bound to local port " + socket.getLocalPort());

            // Create a datagram packet, containing a maximum buffer of 256 bytes
            DatagramPacket packet = new DatagramPacket( new byte[256], 256 );

            // Receive a packet - remember by default this is a blocking operation
            socket.receive(packet);

            System.out.println ("Packet received at " + new Date());
            // Display packet information
            InetAddress remote_addr = packet.getAddress();
            sender = remote_addr.getHostAddress();
            System.out.println ("Sender: " + sender );
            System.out.println ("from Port: " + packet.getPort());

            // Display packet contents, by reading from byte array
            ByteArrayInputStream bin = new ByteArrayInputStream (packet.getData());

            // Display only up to the length of the original UDP packet
            for (int i=0; i < packet.getLength(); i++)  {
                int data = bin.read();
		if (data == -1)
                    break;
                else {
                    System.out.print ( (char) data) ;
                    message += (char) data;
                }
                    
            }
			
            socket.close( );
	}
	catch (IOException e) 	{
            System.out.println ("Error - " + e);
	}
        
        //print the incoming message to the received file and gui
        TextInterface.incomingTextBox.setText(message);
        TextInterface.writeInputToFile(message);
        
        //print the sender and date to the gui
        TextInterface.senderTextBox.setText(sender);
   }
} 