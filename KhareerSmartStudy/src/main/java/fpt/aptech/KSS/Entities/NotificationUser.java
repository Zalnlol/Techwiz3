/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fpt.aptech.KSS.Entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jthie
 */
@Entity
@Table(name = "notification_user")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NotificationUser.findAll", query = "SELECT n FROM NotificationUser n"),
    @NamedQuery(name = "NotificationUser.findById", query = "SELECT n FROM NotificationUser n WHERE n.id = :id"),
    @NamedQuery(name = "NotificationUser.findByCreateDate", query = "SELECT n FROM NotificationUser n WHERE n.createDate = :createDate")})
public class NotificationUser implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "create_date")
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @JoinColumn(name = "id_notification", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Notification idNotification;
    @JoinColumn(name = "id_user", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Account idUser;

    public NotificationUser() {
    }


    public NotificationUser(Integer id) {
        this.id = id;
    }

    public NotificationUser(Integer id, Date createDate) {
        this.id = id;
        this.createDate = createDate;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Notification getIdNotification() {
        return idNotification;
    }

    public void setIdNotification(Notification idNotification) {
        this.idNotification = idNotification;
    }

    public Account getIdUser() {
        return idUser;
    }

    public void setIdUser(Account idUser) {
        this.idUser = idUser;
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
        if (!(object instanceof NotificationUser)) {
            return false;
        }
        NotificationUser other = (NotificationUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KSS.Entities.NotificationUser[ id=" + id + " ]";
    }
    
}
