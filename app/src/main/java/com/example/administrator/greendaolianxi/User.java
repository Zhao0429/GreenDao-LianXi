package com.example.administrator.greendaolianxi;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;


@Entity
public class User {
    @Id(autoincrement = true)
    private Long id;
    private String name;
    private String page;
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getPage() {
        return this.page;
    }
    public void setPage(String page) {
        this.page = page;
    }
   
    @Generated(hash = 1303070916)
    public User(Long id, String name, String page) {
        this.id = id;
        this.name = name;
        this.page = page;
    }
    @Generated(hash = 586692638)
    public User() {
    }
}

