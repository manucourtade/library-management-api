package com.biblioteca.ejercicios_practica.model;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table(name = "Books")
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Data
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, length = 100)
    private String title;

    @Column(unique = true)
    private String isbn;

    @Column
    private Integer stock;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_category") // FK
    private Category category;

    @ManyToMany
    @JoinTable(
            name = "book_author", // Nombre de la entidad intermedia
            joinColumns = @JoinColumn(name = "book_id"),
            inverseJoinColumns = @JoinColumn(name = "author_id")
    )
    @ToString.Exclude //Exclui ToString y equals hashcode (metodos que vienen con @Data de Lombok)
    @EqualsAndHashCode.Exclude // ya que me generaban errores
    private List<Author> authors;

}
