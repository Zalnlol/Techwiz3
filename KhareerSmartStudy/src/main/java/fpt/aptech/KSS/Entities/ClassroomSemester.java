/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jthie
 */
@Entity
@Table(name = "classroom_semester")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ClassroomSemester.findAll", query = "SELECT c FROM ClassroomSemester c"),
    @NamedQuery(name = "ClassroomSemester.findById", query = "SELECT c FROM ClassroomSemester c WHERE c.id = :id")})
public class ClassroomSemester implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @JoinColumn(name = "id_classroom", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Classroom idClassroom;
    @JoinColumn(name = "id_semester", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Semester idSemester;

    public ClassroomSemester() {
    }

    public ClassroomSemester(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Classroom getIdClassroom() {
        return idClassroom;
    }

    public void setIdClassroom(Classroom idClassroom) {
        this.idClassroom = idClassroom;
    }

    public Semester getIdSemester() {
        return idSemester;
    }

    public void setIdSemester(Semester idSemester) {
        this.idSemester = idSemester;
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
        if (!(object instanceof ClassroomSemester)) {
            return false;
        }
        ClassroomSemester other = (ClassroomSemester) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KSS.Entities.ClassroomSemester[ id=" + id + " ]";
    }
    
}
