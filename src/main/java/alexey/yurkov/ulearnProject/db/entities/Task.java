package alexey.yurkov.ulearnProject.db.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
public class Task {

    @Id
    private long id;

    @Column
    private String name;

    @Enumerated(EnumType.STRING)
    private TaskType taskType;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "theme_id")
    private Theme theme;
}
