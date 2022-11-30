package uk.ac.tees.mad.w9519946.cravingsnearby.Model_Classes;

public class Users_data {

    //Variables
    String User_Name;
    String Email;
    String Restaurant_User_Info;
    String Password;
    String id_User;
    String profile_Pic;
    String last_Message;

    //Constructors
    public Users_data(){}

    public Users_data(String User_Name, String Email, String Password, String id_User, String profile_Pic, String last_Message) {
        this.User_Name = User_Name;
        this.Email = Email;
        this.Password = Password;
        this.id_User = id_User;
        this.profile_Pic = profile_Pic;
        this.last_Message = last_Message;
    }

    //Constructor Register
    public Users_data(String User_Name, String Email, String Password) {
        this.User_Name = User_Name;
        this.Email = Email;
        this.Password = Password;
        this.id_User = id_User;
        this.profile_Pic = profile_Pic;
        this.last_Message = last_Message;
    }

    //Setter and Getter

    public String getUser_Name() {
        return User_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public String geteMail() {
        return Email;
    }

    public void seteMail(String Email) {
        this.Email = Email;
    }

    public String getPassWord() {
        return Password;
    }

    public void setPassWord(String passWord) {
        this.Password = passWord;
    }

    public String getId_User() {
        return id_User;
    }

    public void setId_User(String id_User) {
        this.id_User = id_User;
    }

    public String getProfile_Pic() {
        return profile_Pic;
    }

    public void setProfile_Pic(String profile_Pic) {
        this.profile_Pic = profile_Pic;
    }

    public String getLast_Message() {
        return last_Message;
    }

    public void setLast_Message(String last_Message) {
        this.last_Message = last_Message;
    }

    public String getRestaurant_User_Info() {
        return Restaurant_User_Info;
    }

    public void setRestaurant_User_Info(String restaurant_User_Info) {
        Restaurant_User_Info = restaurant_User_Info;
    }
}
