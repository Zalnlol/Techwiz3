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
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jthie
 */
@Entity
@Table(name = "mark")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Mark.findAll", query = "SELECT m FROM Mark m"),
    @NamedQuery(name = "Mark.findById", query = "SELECT m FROM Mark m WHERE m.id = :id"),
    @NamedQuery(name = "Mark.findByMark", query = "SELECT m FROM Mark m WHERE m.mark = :mark"),
    @NamedQuery(name = "Mark.findByRemark", query = "SELECT m FROM Mark m WHERE m.remark = :remark")})
public class Mark implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @NotNull
    @Column(name = "mark")
    private int mark;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 500)
    @Column(name = "remark")
    private String remark;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account idUser;
    @JoinColumn(name = "id_course", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Course idCourse;
    @JoinColumn(name = "id_exam", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Exam idExam;

    public Mark() {
    }

    public Mark(Integer id) {
        this.id = id;
    }

    public Mark(Integer id, int mark, String remark) {
        this.id = id;
        this.mark = mark;
        this.remark = remark;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public int getMark() {
        return mark;
    }

    public void setMark(int mark) {
        this.mark = mark;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Account getIdUser() {
        return idUser;
    }

    public void setIdUser(Account idUser) {
        this.idUser = idUser;
    }

    public Course getIdCourse() {
        return idCourse;
    }

    public void setIdCourse(Course idCourse) {
        this.idCourse = idCourse;
    }

    public Exam getIdExam() {
        return idExam;
    }

    public void setIdExam(Exam idExam) {
        this.idExam = idExam;
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
        if (!(object instanceof Mark)) {
            return false;
        }
        Mark other = (Mark) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KSS.Entities.Mark[ id=" + id + " ]";
    }
    
}
