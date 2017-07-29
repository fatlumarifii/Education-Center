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
@Table(name = "Grupi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Grupi.findAll", query = "SELECT g FROM Grupi g")
    , @NamedQuery(name = "Grupi.findByGrupiID", query = "SELECT g FROM Grupi g WHERE g.grupiID = :grupiID")
    , @NamedQuery(name = "Grupi.findByNrStudenteveGrup", query = "SELECT g FROM Grupi g WHERE g.nrStudenteveGrup = :nrStudenteveGrup")
    , @NamedQuery(name = "Grupi.findByEmriGrupit", query = "SELECT g FROM Grupi g WHERE g.emriGrupit = :emriGrupit")})
public class Grupi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Grupi_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer grupiID;
    @Basic(optional = false)
    @Column(name = "Nr_Studenteve_Grup")
    private int nrStudenteveGrup;
    @Basic(optional = false)
    @Column(name = "EmriGrupit")
    private String emriGrupit;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "grupID")
    private Collection<Studenti> studentiCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "grupiID")
    private Collection<Orari> orariCollection;

    public Grupi() {
    }

    public Grupi(Integer grupiID) {
        this.grupiID = grupiID;
    }

    public Grupi(Integer grupiID, int nrStudenteveGrup, String emriGrupit) {
        this.grupiID = grupiID;
        this.nrStudenteveGrup = nrStudenteveGrup;
        this.emriGrupit = emriGrupit;
    }

    public Integer getGrupiID() {
        return grupiID;
    }

    public void setGrupiID(Integer grupiID) {
        this.grupiID = grupiID;
    }

    public int getNrStudenteveGrup() {
        return nrStudenteveGrup;
    }

    public void setNrStudenteveGrup(int nrStudenteveGrup) {
        this.nrStudenteveGrup = nrStudenteveGrup;
    }

    public String getEmriGrupit() {
        return emriGrupit;
    }

    public void setEmriGrupit(String emriGrupit) {
        this.emriGrupit = emriGrupit;
    }

    @XmlTransient
    public Collection<Studenti> getStudentiCollection() {
        return studentiCollection;
    }

    public void setStudentiCollection(Collection<Studenti> studentiCollection) {
        this.studentiCollection = studentiCollection;
    }

    @XmlTransient
    public Collection<Orari> getOrariCollection() {
        return orariCollection;
    }

    public void setOrariCollection(Collection<Orari> orariCollection) {
        this.orariCollection = orariCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (grupiID != null ? grupiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Grupi)) {
            return false;
        }
        Grupi other = (Grupi) object;
        if ((this.grupiID == null && other.grupiID != null) || (this.grupiID != null && !this.grupiID.equals(other.grupiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Grupi[ grupiID=" + grupiID + " ]";
    }
    
}
