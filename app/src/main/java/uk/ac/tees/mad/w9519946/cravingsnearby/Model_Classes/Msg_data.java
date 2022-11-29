package uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes;

public class Msg_data {
    //Variables
    String msg;
    Long time_Stamp;
    String uID;

    //Constructors
    public Msg_data() {
    }
    public Msg_data(String msg, Long time_Stamp, String uID) {
        this.msg = msg;
        this.time_Stamp = time_Stamp;
        this.uID = uID;
    }
    public Msg_data(String msg, String uID) {
        this.msg = msg;
        this.uID = uID;
    }

    //Getter and Setter Setup
    public String getMsg() {
        return msg;
    }
    public void setMsg(String msg) {
        this.msg = msg;
    }
    public Long getTime_Stamp() {
        return time_Stamp;
    }
    public void setTime_Stamp(Long time_Stamp) {
        this.time_Stamp = time_Stamp;
    }
    public String getuID() {
        return uID;
    }
    public void setuID(String uID) {
        this.uID = uID;
    }
}
