package networkingproject1;


//This is the UDP Receiver		
import java.net.*;
import java.io.*;
import java.util.*;

public class UDPReceiver  implements Runnable{
    
    public UDPReceiver() {
        
    } 
    
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

            System.out.println ("Packet received at " + new Date( ));
            // Display packet information
            InetAddress remote_addr = packet.getAddress();
            System.out.println ("Sender: " + remote_addr.getHostAddress( ) );
            System.out.println ("from Port: " + packet.getPort());

            // Display packet contents, by reading from byte array
            ByteArrayInputStream bin = new ByteArrayInputStream (packet.getData());

            // Display only up to the length of the original UDP packet
            for (int i=0; i < packet.getLength(); i++)  {
                int data = bin.read();
		if (data == -1)
                    break;
		else
                    System.out.print ( (char) data) ;
            }
			
            socket.close( );
	}
	catch (IOException e) 	{
            System.out.println ("Error - " + e);
	}
   }
} //end of class definition