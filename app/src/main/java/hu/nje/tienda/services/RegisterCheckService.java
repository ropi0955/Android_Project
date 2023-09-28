package hu.nje.tienda.services;

import android.text.TextUtils;

import java.time.Year;
import java.util.Calendar;

import hu.nje.tienda.services.helpers.constants.Constants;

public class RegisterCheckService {

    // TODO README
    /* Regisztráláshoz szükséges ellenörzések */

    public Constants c = new Constants();

    // Az email tartalmaz-e @ karaktert
    public boolean containAtCharacter(String email){
        char[] ca= email.toCharArray();
        Boolean contain_at_character = false;
        for (int i = 0; i < email.length(); i++) {
            char x = email.charAt(i);
            if (c.atCharacter.contains(String.valueOf(ca[i]))) {
                contain_at_character = true;
            }
        }
        if (contain_at_character){
            return true;
        }else{
            return false;
        }
    }

    // Ha valamelyik input üres akkor figyelmeztet
    public boolean checkValueIsNull(String email, String last_name, String first_name, String city_name, String street_name, String street_number, String user_name, String password, String repass, String phone_number, String birthday){
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(last_name) || TextUtils.isEmpty(first_name)
                || TextUtils.isEmpty(city_name) || TextUtils.isEmpty(street_name) || TextUtils.isEmpty(street_number)
                || TextUtils.isEmpty(user_name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repass) || TextUtils.isEmpty(phone_number) || TextUtils.isEmpty(birthday)){
            return false;
        }else{
            return true;
        }
    }

    // Ellenőrzi a jelszó hosszúságát (min. 8 karakter), ellenőrzi, hogy a jelszó tartalmaz-e betűt, számot és speciális karaktert
    public boolean securePassCheck(String password){
        if (password.length() >= 8 && Character.isUpperCase(password.charAt(0))) {
            Boolean hasLetter = false;
            Boolean hasDigit = false;
            Boolean containsSpecialChar = false;
            char[] ch= password.toCharArray();
            for (int i = 0; i < password.length(); i++) {
                char x = password.charAt(i);
                if (Character.isLetter(x)) {
                    hasLetter = true;
                }
                else if (Character.isDigit(x)) {
                    hasDigit = true;
                }  else if(c.special_chars.contains(String.valueOf(ch[i]))){
                    containsSpecialChar = true;
                }
            }
            if (hasLetter && hasDigit && containsSpecialChar) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Ellenőrzi a telefonszám hosszúságát (csak 11), ellenőrzi, hogy a telefonszám tartalmaz-e betűt, számot és speciális karaktert
    public boolean securePhoneNumber(String phone_number){
        if (phone_number.length() == 11) {
            Boolean hasLetter = false;
            Boolean hasDigit = false;
            Boolean containsSpecialChar = false;
            String firstTwo = String.valueOf(phone_number.charAt(0)) + String.valueOf(phone_number.charAt(1));
            System.out.println("FIRSTTWO: " + firstTwo);
            char[] ch= phone_number.toCharArray();
            for (int i = 0; i < phone_number.length(); i++) {
                char x = phone_number.charAt(i);
                if (Character.isLetter(x)) {
                    hasLetter = true;
                }
                else if (Character.isDigit(x)) {
                    hasDigit = true;
                }  else if(c.special_chars.contains(String.valueOf(ch[i]))){
                    containsSpecialChar = true;
                }
            }
            if (!hasLetter && hasDigit && !containsSpecialChar && firstTwo.equals("06")) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }

    // Ellenőrzi a születési dátum helyes formátumát
    public boolean secureBirthday(String birthday){
        Boolean hasLetter = false;
        Integer hasDigit = 0;
        Integer hasSlash = 0;
        char[] ch= birthday.toCharArray();
        if (birthday.length() == 10) {
            for (int i = 0; i < birthday.length(); i++) {
                char x = birthday.charAt(i);
                if (Character.isLetter(x)) {
                    hasLetter = true;
                }
                else if (Character.isDigit(x)) {
                    hasDigit = hasDigit + 1;
                }  else if(c.slashCharacter.contains(String.valueOf(ch[i]))){
                    hasSlash = hasSlash + 1;
                }
            }
            if (!hasLetter && hasDigit == 8 && hasSlash == 2) {
                String[] parts = birthday.split("/");
                if (parts[0].length() == 4 && parts[1].length() == 2 && parts[2].length() == 2 && Integer.parseInt(parts[1]) <= 12 && Integer.parseInt(parts[2]) <= 31 ){
                    return true;
                }else{
                    return false;
                }
            } else {
                return false;
            }
        }else{
            return false;
        }
    }

    // Leellenőrzi, hogy elmúlt-e 16 éves
    public boolean over16(String birthday){
        int year = Calendar.getInstance().get(Calendar.YEAR);
        int limit_year = year - 16;
        if (Integer.parseInt(birthday.substring(0 ,4)) < limit_year){
            return true;
        }else{
            return false;
        }
    }

}