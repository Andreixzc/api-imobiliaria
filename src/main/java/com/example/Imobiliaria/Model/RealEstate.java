package com.example.Imobiliaria.Model;


import java.time.LocalDate;
import java.util.UUID;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@Setter
@Getter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "real_estate")
public class RealEstate {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    private boolean sold;

    @Column(nullable = true)
    private float value;

    @Column(nullable = true)
    private int size;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate createdAt;

    @Column(nullable = true)
    @DateTimeFormat(pattern = "dd-MM-yyyy")
    private LocalDate updatedAt;

    @OneToOne(cascade = CascadeType.ALL)
    // @JoinColumn(name = "addressId", referencedColumnName = "id")
    @JoinColumn(name = "address_id", referencedColumnName = "id", nullable = false)
    private Address address;

    @ManyToOne
    // @JoinColumn(name = "categoryId")
    @JoinColumn(name = "category_id", referencedColumnName = "id", nullable = false)
    private Category category;
   


}
