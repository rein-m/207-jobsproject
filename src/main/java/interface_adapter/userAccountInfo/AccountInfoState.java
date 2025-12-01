package interface_adapter.userAccountInfo;

public class AccountInfoState {

    private String userIdentifier;
    private String location;
    private String email;
    private String phone;
//    private _ resumes;

    public String getLocation() {return location;}
    public String getEmail() {return email;}
    public String getPhone() {return phone;}
    public String getUserIdentifier() {return userIdentifier;}
//    public _ getResumes() {return resumes;}
    public void setLocation(String location) {this.location = location;}
    public void setEmail(String email) {this.email = email;}
    public void setPhone(String phone) {this.phone = phone;}
    public void setUserIdentifier(String userIdentifier) {this.userIdentifier = userIdentifier;}
//    public void setResumes(_ resumes) {this.resumes = resumes;}


}
