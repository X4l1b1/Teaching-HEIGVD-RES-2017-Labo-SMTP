package Model;

import java.util.List;
import java.util.ArrayList;
import java.util.stream.Collectors;

import Model.Message;
import Model.Person;


public class Prank {
    private Person sender;
    private List<Person> victims = new ArrayList<>();
    private List<Person> witness = new ArrayList<>();
    private String message;

    public Prank(Person sender, List<Person> victims, Person witness, Message message){
        this.sender = sender;
        this.victims = victims;
        this.witness = witness;
        this.message = message;
    }

    public Message generateMessage(){
        Message msg = new Message();

        msg.setBody(this.message + "\r\n" + sender.getFirstName());

        String[] to = victims.stream().map(p->p.getAddress()).collect(Collectors.toList()).toArray(new String[]{});
        msg.setCc(cc);

        msg.setFrom(sender.getAddress());

        return msg;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person p){
        this.sender = p;
    }

    public String getMessage(){
        return this.message;
    }

    public void setMessage(String s){
        this.message = s;
    }

    public void addWitness(List<Person> ps){
        witness.addAll(ps);
    }

    public void addVictims(List<Person> ps){
        victims.addAll(ps);
    }

    public List<Person> getVictims() {
        return receivers;
    }

    public List<Person> getWitness() {
        return witness;
    }


}
