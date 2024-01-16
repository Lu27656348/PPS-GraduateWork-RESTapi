package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "users")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class User {
    @Id
    private String userDNI;

    @Column(name = "userpassword")
    private String userPassword;
    @Column(name = "userfirstname")
    private String userFirstName;
    @Column(name = "userlastname")
    private String userLastName;
    @Column(name = "useremail")
    private String userEmail;
    @Column(name = "useremailalt")
    private String userEmailAlt;
    @Column(name = "userphone")
    private String userPhone;

    public User(String userDNI, String userPassword, String userFirstName, String userLastName, String userEmailUcab) {
        this.userDNI = userDNI;
        this.userPassword = userPassword;
        this.userFirstName = userFirstName;
        this.userLastName = userLastName;
        this.userEmail = userEmailUcab;
    }

}
