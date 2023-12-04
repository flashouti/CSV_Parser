package alexey.yurkov.ulearnProject.db.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Theme {

    @Id
    private int themeID;

    @Column
    private int exerciseScore;

    @Column
    private int activityScore;

    @Column
    private int practiceScore;

    @Column
    private int homeworkScore;

    @OneToMany(mappedBy = "theme", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Task> tasks;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId")
    private Mark mark;

}
