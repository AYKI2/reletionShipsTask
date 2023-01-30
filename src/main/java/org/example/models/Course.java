package org.example.models;

import lombok.*;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @SequenceGenerator(name = "generator_sequence_course", allocationSize = 1)
    private Long id;
    @Column(name = "course_name")
    private String courseName;

    private int duration;
    @Column(name = "created_at")
    private LocalDate createdAt;
    @Column(name = "image_link")
    private String imageLink;
    private String description;

    @OneToMany(cascade = ALL, mappedBy = "course")
    private List<Lesson> lessons;
    @ManyToMany(mappedBy = "courses",cascade = {REFRESH, PERSIST, DETACH, MERGE})
    private List<Instructor> instructors;

    public Course(String courseName, int duration, LocalDate createdAt, String imageLink, String description) {
        this.courseName = courseName;
        this.duration = duration;
        this.createdAt = createdAt;
        this.imageLink = imageLink;
        this.description = description;
    }
    @Override
    public String toString() {
        return "\nCourse:" +
                "\nid = " + id +
                "\ncourseName = " + courseName +
                "\nduration = " + duration +
                "\ncreatAt = " + createdAt +
                "\nimagineLink = '" + imageLink +
                "\ndescription = '" + description +
                "\n~~~~~~~~~~~~~~~~~~~~~~~~~~";
    }
}
