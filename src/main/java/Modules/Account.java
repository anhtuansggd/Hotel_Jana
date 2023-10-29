package Modules;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HexFormat;

public class Account {
    private String id;
    private AccountType type;
    private String username;
    private String password;
    private String name;
    private Race race;

    public enum AccountType {
        MANAGER,
        RECEPTIONIST,
        GUEST
    }

    public enum Race {
        FRANKENSTEIN,
        HUMAN,
        INVISIBLE_HUMAN,
        MUMMY,
        VAMPIRE,
        WEREWOLF,
        WITCH,
        ZOMBIE
    }

    public Account(String id, AccountType type, String username, String password, String name, Race race) {
        this.id = id;
        this.type = type;
        this.username = username;
        this.password = password;
        this.name = name;
        this.race = race;
    }

    protected void resetPassword(){};

    public AccountType getAccountType() {
        return type;
    }

    public String getId() {
        return id;
    }


    /**
     * Tuan:
     * Decide again about this logic
     *
     */
//    public void setId(String id) {
//        this.id = id;
//    }


    /**
     * Tuan:
     * Decide again about this logic
     *
     */
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    //Below are new getter/setter


    public void setId(String id) {
        this.id = id;
    }

    public AccountType getType() {
        return type;
    }

    public void setType(AccountType type) {
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Race getRace() {
        return race;
    }

    public void setRace(Race race) {
        this.race = race;
    }

    public static String hashPassword(String password){
        String hashedPassword = null;
        try{
            //160 bit - 40 bytes
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));
            hashedPassword = HexFormat.of().formatHex(bytes);
        }catch (NoSuchAlgorithmException e){
            e.printStackTrace();
        }
        return hashedPassword;
    }
}