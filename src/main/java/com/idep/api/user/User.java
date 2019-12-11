package com.idep.api.user;

import javax.persistence.*;

import com.idep.api.message.Message;
import com.idep.api.object.Object;
import com.idep.api.transaction.Transaction;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;


@Entity
@Table(name="users")
@Getter
@Setter
@NoArgsConstructor

public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String forename;

    private String surname;

    private String mail;

    private float balance;

    private boolean admin;

    @JsonIgnore
    @OneToMany(mappedBy = "sender", fetch = FetchType.LAZY)
    private List<Message> sentMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "receiver", fetch = FetchType.LAZY)
    private List<Message> receivedMessages;

    @JsonIgnore
    @OneToMany(mappedBy = "owner", fetch = FetchType.LAZY)
    private List<Object> ownedObjects;

    @JsonIgnore
    @OneToMany(mappedBy = "borrower", fetch = FetchType.LAZY)
    private List<Transaction> userBorrower;

    @JsonIgnore
    @OneToMany(mappedBy = "seller", fetch = FetchType.LAZY)
    private List<Transaction> userSeller;

}
