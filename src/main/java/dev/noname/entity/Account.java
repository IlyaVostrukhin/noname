package dev.noname.entity;

public class Account extends AbstractEntity<Integer> {
    private static final long serialVersionUID = -1126297213009700684L;

    private String name;
    private String email;
    private String phone;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Override
    public String toString() {
        return String.format("Account [id=%s, name=%s, email=%s, phone=%s]",
                getId(), name, email, phone);
    }
}
