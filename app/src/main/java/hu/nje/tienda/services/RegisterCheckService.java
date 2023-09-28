package hu.nje.tienda.services;

import android.text.TextUtils;

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
    public boolean checkValueIsNull(String email, String last_name, String first_name, String city_name, String street_name, String street_number, String user_name, String password, String repass){
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(last_name) || TextUtils.isEmpty(first_name)
                || TextUtils.isEmpty(city_name) || TextUtils.isEmpty(street_name) || TextUtils.isEmpty(street_number)
                || TextUtils.isEmpty(user_name) || TextUtils.isEmpty(password) || TextUtils.isEmpty(repass)){
            return false;
        }else{
            return true;
        }
    }

    // Ellenőrzi a jelszó hosszúságát (min. 8 karakter), ellenőrzi, hogy a jelszó tartalmaz-e betűt, számot és speciális karaktert
    public boolean securePassCheck(String password){
        if (password.length() >= 8 && Character.isUpperCase(password.charAt(0))) {
            System.out.println("Több 8 hosszúságú");
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

}