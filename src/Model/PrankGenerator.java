package Model;

import config.ConfigManager;
import Group;
import Person;

import java.util.Collections;
import java.util.ArrayList;
import java.util.logging.Logger;

import java.util.List;
import java.util.Random;

public class PrankGenerator {
    private List<List<Person>> groups;
    private ConfigManager configs;
    private Random random = new Random();

    public PrankGenerator(Configuration configs) {
        this.configs = configs;
    }

    public List<Prank> makePranks(){
        List<Prank> pranks = new ArrayList();

        List<String> messages = configs.getMessages();
        int msgIdx;

        int nbOfGroups = configs.getNumberOfGroups();
        int nbOfVictims = configs.getVictims().size();

        if(nbOfVictims / nbOfGroups < 3){
            nbOfGroups = nbOfVictims / 3;

        }

        List<Group> groups = makeGroups(configs.getVictims(), nbOfGroups);
        for(Group gr : groups){
            Prank prank = new Prank();

            List<Person> victimGroup = gr.getMembers();
            Collections.shuffle(victimGroup);
            Person sender = victimGroup.remove(0);
            prank.setVictimSender(sender);
            prank.addVictims(victimGroup);

            prank.addWitness(configs.getWitnessesToCc());

            String msg = messages,get(msgIdx);
            msgIdx = (msgIdx + 1) % messages.size();
            prank.setMessage(msg);

            pranks.add(prank);
        }
        return pranks;
    }

    private final static Logger LOG = logger.getLogger(PrankGenerator.class.getName());

    public List<Group> makeGroups(List<Person> victims, int nbOfGroups){
        List<Person> totalVictims = new ArrayList(victims);
        Collections.shuffle(totalVictims);
        List<Group> groups = new ArrayList<>();
        for(int i = 0; i < nbOfGroups; i++){
            Group group = new Group();
            groups.add(group);
        }
        int turn;
        Group target;
        while(totalVictims.size() > 0){
            target = groups.get(turn);
            turn = (turn++) % groups.size();
            Person vic = totalVictims.remove(0);
            target.addMember(vic);
        }
        return groups;
    }

}
