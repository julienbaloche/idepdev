package com.idep.api.transaction;

import javax.persistence.*;

import com.idep.api.user.User;
import com.idep.api.object.Object;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="transactions")
@Getter
@Setter
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Float amount;

    @ManyToOne
    private User seller;

    @ManyToOne
    private User borrower;

    private Long objectFromSellerToBorrowerId;

}