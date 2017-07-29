/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arbër Suka
 */
@Entity
@Table(name = "Gjinia")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Gjinia.findAll", query = "SELECT g FROM Gjinia g")
    , @NamedQuery(name = "Gjinia.findByGjiniaID", query = "SELECT g FROM Gjinia g WHERE g.gjiniaID = :gjiniaID")
    , @NamedQuery(name = "Gjinia.findByLloji", query = "SELECT g FROM Gjinia g WHERE g.lloji = :lloji")})
public class Gjinia implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Gjinia_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer gjiniaID;
    @Basic(optional = false)
    @Column(name = "Lloji")
    private String lloji;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "gjiniaid")
    private Collection<Studenti> studentiCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "gjiniaid")
    private Collection<Adminstrata> adminstrataCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "gjiniaid")
    private Collection<Profesori> profesoriCollection;

    public Gjinia() {
    }

    public Gjinia(Integer gjiniaID) {
        this.gjiniaID = gjiniaID;
    }

    public Gjinia(Integer gjiniaID, String lloji) {
        this.gjiniaID = gjiniaID;
        this.lloji = lloji;
    }

    public Integer getGjiniaID() {
        return gjiniaID;
    }

    public void setGjiniaID(Integer gjiniaID) {
        this.gjiniaID = gjiniaID;
    }

    public String getLloji() {
        return lloji;
    }

    public void setLloji(String lloji) {
        this.lloji = lloji;
    }

    @XmlTransient
    public Collection<Studenti> getStudentiCollection() {
        return studentiCollection;
    }

    public void setStudentiCollection(Collection<Studenti> studentiCollection) {
        this.studentiCollection = studentiCollection;
    }

    @XmlTransient
    public Collection<Adminstrata> getAdminstrataCollection() {
        return adminstrataCollection;
    }

    public void setAdminstrataCollection(Collection<Adminstrata> adminstrataCollection) {
        this.adminstrataCollection = adminstrataCollection;
    }

    @XmlTransient
    public Collection<Profesori> getProfesoriCollection() {
        return profesoriCollection;
    }

    public void setProfesoriCollection(Collection<Profesori> profesoriCollection) {
        this.profesoriCollection = profesoriCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (gjiniaID != null ? gjiniaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Gjinia)) {
            return false;
        }
        Gjinia other = (Gjinia) object;
        if ((this.gjiniaID == null && other.gjiniaID != null) || (this.gjiniaID != null && !this.gjiniaID.equals(other.gjiniaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Gjinia[ gjiniaID=" + gjiniaID + " ]";
    }
    
}
