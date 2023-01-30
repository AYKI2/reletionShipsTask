package org.example.models;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import jakarta.persistence.*;
import java.util.List;

import static jakarta.persistence.CascadeType.*;

@Entity
@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "lessons")
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @Column(name = "video_link")
    private String videoLink;

    @ManyToOne(cascade = {MERGE, REFRESH, DETACH, PERSIST})
    private Course course;
    @OneToMany(cascade = {DETACH,REMOVE},fetch = FetchType.EAGER)
    private List<Task> tasks;

    public Lesson(String name, String videoLink) {
        this.name = name;
        this.videoLink = videoLink;
    }
    @Override
    public String toString() {
        return "\nLesson:" +
                "\nid = " + id +
                "\nname = " + name +
                "\nvideoLink = " + videoLink +
                "\n~~~~~~~~~~~~~~~~~~~~~";
    }
}
