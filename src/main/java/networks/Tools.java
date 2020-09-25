package networks;

import java.io.*;
import java.net.Socket;

public class Tools {

    private static void closeSocket(Socket socket) {
        System.out.println("Closed connection " + socket.getInetAddress() + " " + socket.getPort());
        try {
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void closeStreams(DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        try {
            dataInputStream.close();
            dataOutputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void closeSocketConnection(Socket socket, DataInputStream dataInputStream, DataOutputStream dataOutputStream) {
        closeSocket(socket);
        closeStreams(dataInputStream, dataOutputStream);
    }

    public static byte[] createPacket(String fileName, Settings settings) {
        if(settings.equals(Settings.SERVICE)) {
            return fileName.getBytes();
        }
        else {
            return buildDataPacket(fileName);
        }
    }

    private static byte[] buildDataPacket(String fileName) {
        return new byte[0];
    }

    public enum Settings {
        SERVICE,
        DATA
    }

    public static void sendBytes(DataOutputStream out, Packet packet) {
        try {
            out.write(packet.getPacketLength());
            out.write(packet.getBytes());
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static byte[] getBytes(DataInputStream in) {
        try {
            int packetLength = in.read();
            return in.readNBytes(packetLength);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
