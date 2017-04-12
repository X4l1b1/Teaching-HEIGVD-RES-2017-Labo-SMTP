package Model;

public class Message {
    private String      subject;
    private String      from;
    private String      body;
    private String[]    to =   new String[0];
    private String[]    cc =   new String[0]:
    private String[]    bcc =  new String[0];

    public String getFrom(){
        return from;
    }

    public void setfrom(String s){
        this.from = s;
    }

    public String[] getTo(){
        return to;
    }

    public void setTo(String[] ss){
        this.to = ss;
    }

    public String getBody(){
        return body;
    }

    public void setBody(String s){
        this.body = s;
    }

    public String[] getCc(){
        return cc;
    }

    public void setCc(String[] ss){
        this.cc = ss;
    }

    public String getSubject(){
        return subject;
    }

    public void setSubjet(String s){
        this.subject = s;
    }

    public String[] getBcc(){
        return bcc;
    }

    public void setBcc(String[] ss){
        this.bcc = ss;
    }


}
