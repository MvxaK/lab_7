package org.cook.lab7.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "celebrities")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CelebrityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", length = 100)
    private String name;

    @Column(name = "surname", length = 100)
    private String surname;

    @Column(name = "pseudonyms", length = 1000)
    private List<String> pseudonyms;

    @Column(name = "birth_day_date")
    private LocalDate birthDay;

    @Column(name = "is_alive")
    private boolean alive;

    @ManyToOne
    @JoinColumn(name = "agency_id")
    private AgencyEntity agency;

    @ManyToMany(mappedBy = "celebrities")
    private List<FilmEntity> films;

}
