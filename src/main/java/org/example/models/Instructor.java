package org.example.models;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "instructors")
public class Instructor {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_sequence_ins")
    @SequenceGenerator(name = "generator_sequence_ins", sequenceName = "generator_id", allocationSize = 1)
    private Long id;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;

    private String email;
    @Column(name = "phone_number")
    private String phoneNumber;
    @ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
    private List<Course> courses;

    public Instructor(String firstName, String lastName, String email, String phoneNumber) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    @Override
    public String toString() {
        return "\nInstructor: " +
                "\nid = " + id +
                "\nfirstname = " + firstName +
                "\nlastName = " + lastName +
                "\nemail = " + email +
                "\nphoneNumber = " + phoneNumber +
                "\n~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
