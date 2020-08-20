package dev.noname.entity;

import dev.noname.model.CurrentAccount;

public class Account extends AbstractEntity<Integer> implements CurrentAccount {
    private static final long serialVersionUID = -1126297213009700684L;

    private String firstName;
    private String email;
    private String phone;
    private String password;

    public String getName() {
        return firstName;
    }

    public void setName(String name) {
        this.firstName = name;
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

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    @Override
    public String getDescription() { return firstName + "(" + email + ")"; }

    @Override
    public String toString() {
        return String.format("Account [id=%s, name=%s, email=%s, phone=%s]",
                getId(), firstName, email, phone);
    }
}
