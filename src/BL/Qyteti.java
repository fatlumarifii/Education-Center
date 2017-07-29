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
@Table(name = "Qyteti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Qyteti.findAll", query = "SELECT q FROM Qyteti q")
    , @NamedQuery(name = "Qyteti.findByQytetiID", query = "SELECT q FROM Qyteti q WHERE q.qytetiID = :qytetiID")
    , @NamedQuery(name = "Qyteti.findByEmriQytetit", query = "SELECT q FROM Qyteti q WHERE q.emriQytetit = :emriQytetit")
    , @NamedQuery(name = "Qyteti.findByZipKodi", query = "SELECT q FROM Qyteti q WHERE q.zipKodi = :zipKodi")})
public class Qyteti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Qyteti_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer qytetiID;
    @Basic(optional = false)
    @Column(name = "Emri_Qytetit")
    private String emriQytetit;
    @Basic(optional = false)
    @Column(name = "ZipKodi")
    private int zipKodi;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "qytetiid")
    private Collection<Studenti> studentiCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "qytetiid")
    private Collection<Adminstrata> adminstrataCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "qytetiid")
    private Collection<Profesori> profesoriCollection;

    public Qyteti() {
    }

    public Qyteti(Integer qytetiID) {
        this.qytetiID = qytetiID;
    }

    public Qyteti(Integer qytetiID, String emriQytetit, int zipKodi) {
        this.qytetiID = qytetiID;
        this.emriQytetit = emriQytetit;
        this.zipKodi = zipKodi;
    }

    public Integer getQytetiID() {
        return qytetiID;
    }

    public void setQytetiID(Integer qytetiID) {
        this.qytetiID = qytetiID;
    }

    public String getEmriQytetit() {
        return emriQytetit;
    }

    public void setEmriQytetit(String emriQytetit) {
        this.emriQytetit = emriQytetit;
    }

    public int getZipKodi() {
        return zipKodi;
    }

    public void setZipKodi(int zipKodi) {
        this.zipKodi = zipKodi;
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
        hash += (qytetiID != null ? qytetiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Qyteti)) {
            return false;
        }
        Qyteti other = (Qyteti) object;
        if ((this.qytetiID == null && other.qytetiID != null) || (this.qytetiID != null && !this.qytetiID.equals(other.qytetiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Qyteti[ qytetiID=" + qytetiID + " ]";
    }
    
}
