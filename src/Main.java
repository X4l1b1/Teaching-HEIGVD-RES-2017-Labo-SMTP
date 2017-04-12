import config.ConfigManager;
import model.Prank;
import model.PrankGenerator;
import smtp.SmtpClient;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;

public class Main {
    public static void main(String... args){
        ConfigManager configs = new ConfigManager();
        PrankGenerator pg = new PrankGenerator();

        SmtpClient smtpClient = new SmtpClient(configs.getSmtpServerAddress(), configs.getSmtpServerPort());

        for (Prank p: pg.pranks())
            smtpClient.sendMessage(p.getMessage(), p.getSender(), p.getReceivers(), p.getWitness());

    }
}
