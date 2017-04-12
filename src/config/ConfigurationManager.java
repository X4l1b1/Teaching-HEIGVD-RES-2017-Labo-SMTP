package config;

import model.Message;
import model.Person;

import java.io.*;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

public class ConfigManager {
    private String smtpServerAddress;
    private int smtpServerPort;
    private int numberOfGroups;
    private Person witnessToCC;
    private List<Person> victims = new LinkedList<Person>();
    private List<Message> messages = new LinkedList<Message>();

    public ConfigManager(){
        Properties prop = new Properties();
        try {
            prop.load(new FileInputStream("config/config.properties"));
            smtpServerAddress = prop.getProperty("smtpServerAddress");
            smtpServerPort = Integer.parseInt(prop.getProperty("smtpServerPort"));
            numberOfGroups = Integer.parseInt(prop.getProperty("numberOfGroups"));
            witnessToCC = new Person(prop.getProperty("witnessToCC"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        BufferedReader victimsReader = null;
        BufferedReader messagesReader = null;

        try {
            victimsReader = new BufferedReader(new FileReader("config/victims.utf8"));
            String victim = "";
            while ((victim = victimsReader.readLine()) != null){
                victims.add(new Person(victim));
            }
            messagesReader = new BufferedReader(new FileReader("config/messages.utf8"));
            String line = "";
            String subject = "";
            String message = "";
            boolean isFirstLine = true;
            while ((line = messagesReader.readLine()) != null){
                if (line.equals("===")) { // fin d'un message
                    messages.add(new Message(subject, message)); // sauvegarde
                    isFirstLine = true; // reset
                    subject = "";
                    message = "";
                } else {
                    if (isFirstLine) {
                        subject = line.substring(8); // prend la ligne après le "Subject:"
                        isFirstLine = false;
                    } else {
                        message += "\n" + line;
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                assert victimsReader != null;
                victimsReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }

    public String getSmtpServerAddress() {
        return smtpServerAddress;
    }

    public int getSmtpServerPort() {
        return smtpServerPort;
    }

    public int getNumberOfGroups() {
        return numberOfGroups;
    }

    public Person getWitnessToCC() {
        return witnessToCC;
    }

    public List<Person> getVictims() {
        return victims;
    }

    public List<Message> getMessages() {
        return messages;
    }
}
