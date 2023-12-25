package alexey.yurkov.ulearnProject.db.entities;

import com.vk.api.sdk.objects.base.Sex;
import com.vk.api.sdk.objects.stats.Country;
import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Student {

    @Id
    private String ulearnID;

    @Column
    private String firstName;

    @Column
    private String lastName;

    @Column
    private String email;

    @Column
    private String bdate;

    @Column
    private String education;

    @Column
    private Integer followersQuantity;

    @Column
    private Sex sex;

    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "groupId")
    private StudentGroup group;

    @OneToMany(mappedBy = "student", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Mark> marks;

}
