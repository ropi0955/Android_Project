package hu.nje.tienda.services;

public class UserDatasService {

    // TODO README
    /* User adatai adatbázisból kiolvasva (Alján getter tutorial)
     Ez az egész CSAK bejelentkezés után rakja bele a változókba a user adatait
     FONTOS!: a user jelszava az adatbázisban dekódolva van szóval ha később lesz olyan
     funkció amikor a jelszavat is lehet változtatni akkor a decoderrel kell játszadozni
     amit a "SecurityService"-ben lehet megtalálni és meghívni.
     */

    public String user_email_address;
    public String user_last_name;
    public String user_first_name;
    public String user_city;
    public String user_street;
    public String user_street_number;
    public String user_name;
    public String phone_number;
    public String birthday;

    // Getters és Setters
    public String getUser_email_address() {
        return user_email_address;
    }

    public void setUser_email_address(String user_email_address) {
            this.user_email_address = user_email_address;
    }

    public String getUser_last_name() {
        return user_last_name;
    }

    public void setUser_last_name(String user_last_name) {
            this.user_last_name = user_last_name;
    }

    public String getUser_first_name() {
        return user_first_name;
    }

    public void setUser_first_name(String user_first_name) {
            this.user_first_name = user_first_name;
    }

    public String getUser_city() {
        return user_city;
    }

    public void setUser_city(String user_city) {
            this.user_city = user_city;
    }

    public String getUser_street() {
        return user_street;
    }

    public void setUser_street(String user_street) {
            this.user_street = user_street;
    }

    public String getUser_street_number() {
        return user_street_number;
    }

    public void setUser_street_number(String user_street_number) {
            this.user_street_number = user_street_number;
    }

    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
            this.user_name = user_name;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String user_phone_number) {
        this.phone_number = user_phone_number;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String user_birthday) {
        this.birthday = user_birthday;
    }

    public void setDatas(String userEmailAddress) {
    }


    /* A User adatainak megjelenítése Activity-ben
    *
    * //Először is adjuk hozzá ezt a Service-t hogy elérje a kívánt classunk:
    *   public UserDatasService usrv = new UserDatasService();
    *
    * //és akkor innen már meglehet hívni bármelyik adatot pl:
    *   String user_username = usrv.getUser_name();
    * // és a user_username változóba belerakja az felhasználó user nevét
    *
    * */

}
