package com.example.demo.model;

import lombok.*;

import javax.persistence.*;

@Entity
@Table(name = "customer")
@Getter
@Setter
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private int age;

    @Column(name = "address")
    private String address;
}
