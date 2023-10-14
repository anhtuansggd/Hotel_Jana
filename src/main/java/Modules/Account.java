package Modules;
public class Account {
    private String id;
    private String password;

    protected void resetPassword(){};

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