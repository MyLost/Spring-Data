package com.example.demo.models;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.ToString;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@AllArgsConstructor
@Data
@ToString
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique = true)
    private String username;

    private Integer age;

    @OneToMany(mappedBy = "user", targetEntity = Account.class, cascade = CascadeType.ALL)
    private List<Account> accounts;

    public User() {
        this.accounts = new ArrayList<>();
    }

    public User(String username, Integer age, Account account) {
        this.username = username;
        this.age = age;
        this.accounts.add(account);
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Account> getAccounts() {
        return accounts;
    }

    public void setAccounts(List<Account> accounts) {
        this.accounts = accounts;
    }

    public void addAccounts(Account account) {
        this.accounts.add(account);
    }
}
