package com.myapp.entity;

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
public class Course extends BaseEntity {
    @Basic
    @Column(name = "hours")
    private Integer hours;
    @Basic
    @Column(name = "lecturer_id")
    private Integer lecturerId;

    @ManyToMany(mappedBy = "studentCourses")
    private Set<Student> courseStudents = new HashSet<>();

    @ManyToOne
    @JoinColumn(name = "lecturer_id")
    private Lecturer courseLecturer;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Course course = (Course) o;

        if (getId() != course.getId()) return false;
        if (!Objects.equals(getName(), course.getName())) return false;
        if (!Objects.equals(hours, course.hours)) return false;
        return Objects.equals(lecturerId, course.lecturerId);
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + (getName() != null ? getName().hashCode() : 0);
        result = 31 * result + (hours != null ? hours.hashCode() : 0);
        result = 31 * result + (lecturerId != null ? lecturerId.hashCode() : 0);
        return result;
    }
}