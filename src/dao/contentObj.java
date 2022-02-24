package dao;

public class contentObj{
    private String id, uindex, text;
	
    public contentObj(String uid, String uindex, String utext) {
        this.id =uid;
        this.uindex =uindex;
        this.text =utext;
    	}
	
    public String getId() {return this.id;}
    public String getUindex() {return this.uindex;}
    public String getText() {return this.text;}
}
