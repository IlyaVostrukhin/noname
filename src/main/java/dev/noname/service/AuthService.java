package dev.noname.service;

public interface AuthService {
    boolean isExist(String login, String password);

    void register(String name, String email, String phone, String password);
}
