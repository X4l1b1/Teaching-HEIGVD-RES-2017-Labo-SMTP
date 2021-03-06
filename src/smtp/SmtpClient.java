package smtp;

import Model.Message;
import Model.Person;

import java.io.*;
import java.net.Socket;
import java.net.InetAddress;

import java.util.logging.Logger;


public class SmtpClient {

    private static final Logger LOG = Logger.getLogger(SmtpClient.class.getName());

    private final String smtpServerAddress;
    private final int serverPort = 25;

    private Socket socket;
    private PrintWriter writer;
    private BufferedReader reader;

    public SmtpClient(String smtpServerAddress, int port){
        this.smtpServerAddress = serverAddress;
        this.serverPort = port;
    }

    public void sendMessage(Message m) throws IOException {
        LOG.info("Sending message via SMTP";
        Socket sock = new Socket(smtpServerAddress, serverPort);
        writer = new PrintWriter(new OutputStreamWriter(socket.getOutputStream(), "UTF-8"), true);
        reader = new BufferedReader(new InputStreamReader(socket.getInputStream(), "UTF-8"));
        String line = reader.readline();
        LOG.info(line);
        writer.printf("EHLO localhost\r\n");
        line = reader.readLine();
        LOG.info(line);
        if(!line.startsWith("250")){
            throw new IOException("SMTP Connection Error :" + line);
        }
        while(line.startsWith("250-")){
            line = reader.readLine();
            LOG.info(line);
        }
        writer.write("MAIL FROM:");
        writer.write(message.getFrom());
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);

        for(String s : message.getTo()){
            writer.write("RCPT TO:");
            writer.write(s);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for(String s : message.getCc()){
            writer.write("RCPT TO:");
            writer.write(s);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        for(String s : message.getBcc()){
            writer.write("RCPT TO:");
            writer.write(s);
            writer.write("\r\n");
            writer.flush();
            line = reader.readLine();
            LOG.info(line);
        }

        writer.write("DATA");
        writer.write("\r\n");
        writer.flush();
        line = reader.readLine();
        LOG.info(line);
        writer.write("Content-Type : text/plain; charset=\"utf-8\"\r\n");
        writer.write("From:" + message.getFrom() + "\r\n");

        writer.write("To: " + message.getTo()[0]);
        for(int i = 1; i < message.getTo().length; i++){
            writer.write(", " + message.getTo()[i]);
        }
        writer.write("\r\n");

        writer.write("To: " + message.getCc()[0]);
        for(int i = 1; i < message.getCc().length; i++){
            writer.write(", " + message.getCc()[i]);
        }
        writer.write("\r\n");

        writer.flush();
        LOG.inf(message.getBody());
        writer.write(message.getBody());
        writer.write("\r\n");
        writer.write(".");
        writer.write("\r\n");
        writer.flush();

        line = reader.readLine();
        LOG.info(line);

        writer.write("QUIT\r\n");
        writer.flush();
        reader.close();
        writer.close();
        socket.close();

    }
