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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import com.fasterxml.jackson.annotation.JsonBackReference;
/**
 *
 * @author backs
 */
@Entity
@Table(name = "account_token")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AccountToken.findAll", query = "SELECT a FROM AccountToken a"),
    @NamedQuery(name = "AccountToken.findByToken", query = "SELECT a FROM AccountToken a WHERE a.token = :token")})
public class AccountToken implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 255)
    @Column(name = "token")
    private String token;
    @JoinColumn(name = "id", referencedColumnName = "id")
    @ManyToOne
    //@JsonBackReference
    private Account id;

    public AccountToken() {
    }

    public AccountToken(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Account getId() {
        return id;
    }

    public void setId(Account id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (token != null ? token.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AccountToken)) {
            return false;
        }
        AccountToken other = (AccountToken) object;
        if ((this.token == null && other.token != null) || (this.token != null && !this.token.equals(other.token))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "fpt.aptech.KSS.Entities.AccountToken[ token=" + token + " ]";
    }
    
}
