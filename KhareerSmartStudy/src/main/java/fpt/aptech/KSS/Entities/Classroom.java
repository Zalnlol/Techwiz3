/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jthie
 */
@Entity
@Table(name = "classroom")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Classroom.findAll", query = "SELECT c FROM Classroom c"),
    @NamedQuery(name = "Classroom.findById", query = "SELECT c FROM Classroom c WHERE c.id = :id"),
    @NamedQuery(name = "Classroom.findByName", query = "SELECT c FROM Classroom c WHERE c.name = :name"),
    @NamedQuery(name = "Classroom.findByDuration", query = "SELECT c FROM Classroom c WHERE c.duration = :duration")})
public class Classroom implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 150)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 30)
    @Column(name = "duration")
    private String duration;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClassroom")
    private List<ClassroomUser> classroomUserList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClassroom")
    private List<ClassroomSemester> classroomSemesterList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClassroom")
    private List<Exam> examList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "idClassroom")
    private List<Schedule> scheduleList;

    public Classroom() {
    }

    public Classroom(Integer id) {
        this.id = id;
    }

    public Classroom(Integer id, String name, String duration) {
        this.id = id;
        this.name = name;
        this.duration = duration;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    @XmlTransient
    public List<ClassroomUser> getClassroomUserList() {
        return classroomUserList;
    }

    public void setClassroomUserList(List<ClassroomUser> classroomUserList) {
        this.classroomUserList = classroomUserList;
    }

    @XmlTransient
    public List<ClassroomSemester> getClassroomSemesterList() {
        return classroomSemesterList;
    }

    public void setClassroomSemesterList(List<ClassroomSemester> classroomSemesterList) {
        this.classroomSemesterList = classroomSemesterList;
    }

    @XmlTransient
    public List<Exam> getExamList() {
        return examList;
    }

    public void setExamList(List<Exam> examList) {
        this.examList = examList;
    }

    @XmlTransient
    public List<Schedule> getScheduleList() {
        return scheduleList;
    }

    public void setScheduleList(List<Schedule> scheduleList) {
        this.scheduleList = scheduleList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Classroom)) {
            return false;
        }
        Classroom other = (Classroom) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KSS.Entities.Classroom[ id=" + id + " ]";
    }
    
}
