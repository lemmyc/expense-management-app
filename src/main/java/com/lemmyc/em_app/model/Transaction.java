package com.lemmyc.em_app.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type; //INCOME or EXPENSE
    private Double amount;
    private String description;
    private LocalDate date;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

}
