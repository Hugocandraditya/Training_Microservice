package com.example.book_store.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Table(name = "BOOK")
@Entity
@Data
@ToString
public class BookModel {
    @Id
    @Column
    private Long id;
    @Column
    private String judul;
    @Column
    private String penulis;
    @Column
    private Integer tahunTerbit;
}
