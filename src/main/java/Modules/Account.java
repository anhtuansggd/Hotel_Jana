package Modules;
public class Account {
    private String id;
    private AccountType type;
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
    private String getPassword() {
        return password;
    }

    private void setPassword(String password) {
        this.password = password;
    }
}