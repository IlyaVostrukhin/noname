package dev.noname.service;

import dev.noname.entity.Account;

public interface AuthService {
    boolean isExist(String login, String password);

    Account getAccount(String login, String password);

    Account register(String login, String email, String password, String surName,
                     String firstName, String lastName, String phone, String city,
                     Integer postCode, String address);
}
