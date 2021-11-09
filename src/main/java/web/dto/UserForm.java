package web.dto;

import java.util.Date;

public class UserForm {
    private String nickname;
    private String email;
    private String password;
    private Date registrationDate;


    public String getPassword() {return password;}

    public void setPassword(String password) {this.password = password;}

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Date getRegistrationDate() {
        return registrationDate;
    }

    public void setRegistrationDate(Date registrationDate) {
        this.registrationDate = registrationDate;
    }
}
