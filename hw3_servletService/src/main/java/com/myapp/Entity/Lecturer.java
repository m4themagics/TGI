package com.myapp.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Lecturer extends BaseEntity {
    @Basic
    @Column(name = "specialty", length = 50)
    private String specialty;

    @OneToMany(mappedBy = "courseLecturer",
            cascade = CascadeType.ALL,
            fetch = FetchType.LAZY,
            orphanRemoval = true
    )
    private Set<Course> lecturerCourses = new HashSet<>();

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Lecturer lecturer = (Lecturer) o;

        if (getId() != lecturer.getId()) return false;
        if (!Objects.equals(getName(), lecturer.getName())) return false;
        return Objects.equals(specialty, lecturer.specialty);
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (specialty != null ? specialty.hashCode() : 0);
        return result;
    }
}
