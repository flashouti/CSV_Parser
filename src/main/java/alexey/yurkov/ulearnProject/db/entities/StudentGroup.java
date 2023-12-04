package alexey.yurkov.ulearnProject.db.entities;


import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer groupId;

    @Column
    private String groupName;

    @OneToMany(mappedBy = "group", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private List<Student> students;

}
