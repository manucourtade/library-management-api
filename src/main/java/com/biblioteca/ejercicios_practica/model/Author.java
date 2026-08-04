package com.biblioteca.ejercicios_practica.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "authors")
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany (mappedBy = "authors")
    @ToString.Exclude
    @EqualsAndHashCode.Exclude
    private List<Book> books;
}
