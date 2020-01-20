package com.idep.api.object;

import javax.persistence.*;

import com.idep.api.message.Message;
import com.idep.api.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;

@Entity
@Table(name="objects")
@Getter
@Setter
@NoArgsConstructor

public class Object {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String author;

    private String description;

    private String imageUrl;

    @Enumerated(EnumType.STRING)
    private ObjectCategory category ;



    private float price;

    @ManyToOne
    private User owner;

    private boolean isBorrowed;

}
