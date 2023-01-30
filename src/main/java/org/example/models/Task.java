package org.example.models;

import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.Getter;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Getter @Setter
@NoArgsConstructor
@Table(name = "tasks")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "generator_sequence_task")
    @SequenceGenerator(name = "generator_sequence_task",sequenceName = "sequence_task", allocationSize = 1)
    private Long id;
    private String name;
    private LocalDate deadLine;
    private String task;

    public Task(String name, LocalDate deadLine, String task) {
        this.name = name;
        this.deadLine = deadLine;
        this.task = task;
    }

    @Override
    public String toString() {
        return "\nTask: " +
                "\nid = " + id +
                "\nname = " + name +
                "\ndeadLine = " + deadLine +
                "\ntask = " + task +
                "\n~~~~~~~~~~~~~~~~~~~~";
    }
}
