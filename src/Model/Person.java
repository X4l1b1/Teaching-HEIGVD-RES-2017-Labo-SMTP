package Model;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Person {
    private String address;
    private String firstName;
    private String lastName;

    public Person(String address, String firstName, String lastName){
        this.address = address;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Person(String address){
        this.address = address;
        Pattern pattern = Pattern.compile("(.*)\\.(.*)@");
        Matcher matcher = pattern.matcher(address);
        boolean found = matcher.find();
        if(found){
            this.firstName = matcher.group(1):
            firstName = firstName.substring(0, 1).toUpperCase() + firstName.substring(1);
            this.lastName = matcher.group(2);
            lastName = lastName.substring(0, 1).toUpperCase() + lastName.substring(1);
        }
    }

    public String getAddress(){
        return address;
    }

    public void setAddress(String s){
        this.address = s;
    }

    public String toString(){
        return address;
    }

    public String getFirstName(){
        return firstName;
    }

    public void setFirstName(String s){
        this.firstName = s;
    }

    public String getLastName(){
        return lastName;
    }

    public void setLastName(String s){
        this.lastName = s;
    }

}
