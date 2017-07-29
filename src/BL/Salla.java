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
@Table(name = "Salla")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Salla.findAll", query = "SELECT s FROM Salla s")
    , @NamedQuery(name = "Salla.findBySallaID", query = "SELECT s FROM Salla s WHERE s.sallaID = :sallaID")
    , @NamedQuery(name = "Salla.findByEmertimi", query = "SELECT s FROM Salla s WHERE s.emertimi = :emertimi")
    , @NamedQuery(name = "Salla.findByNrKapaciteteve", query = "SELECT s FROM Salla s WHERE s.nrKapaciteteve = :nrKapaciteteve")
    , @NamedQuery(name = "Salla.findByPershkrim", query = "SELECT s FROM Salla s WHERE s.pershkrim = :pershkrim")})
public class Salla implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "SallaID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer sallaID;
    @Basic(optional = false)
    @Column(name = "Emertimi")
    private String emertimi;
    @Basic(optional = false)
    @Column(name = "Nr_Kapaciteteve")
    private int nrKapaciteteve;
    @Column(name = "Pershkrim")
    private String pershkrim;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "sallaID")
    private Collection<Orari> orariCollection;

    public Salla() {
    }

    public Salla(Integer sallaID) {
        this.sallaID = sallaID;
    }

    public Salla(Integer sallaID, String emertimi, int nrKapaciteteve) {
        this.sallaID = sallaID;
        this.emertimi = emertimi;
        this.nrKapaciteteve = nrKapaciteteve;
    }

    public Integer getSallaID() {
        return sallaID;
    }

    public void setSallaID(Integer sallaID) {
        this.sallaID = sallaID;
    }

    public String getEmertimi() {
        return emertimi;
    }

    public void setEmertimi(String emertimi) {
        this.emertimi = emertimi;
    }

    public int getNrKapaciteteve() {
        return nrKapaciteteve;
    }

    public void setNrKapaciteteve(int nrKapaciteteve) {
        this.nrKapaciteteve = nrKapaciteteve;
    }

    public String getPershkrim() {
        return pershkrim;
    }

    public void setPershkrim(String pershkrim) {
        this.pershkrim = pershkrim;
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
        hash += (sallaID != null ? sallaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Salla)) {
            return false;
        }
        Salla other = (Salla) object;
        if ((this.sallaID == null && other.sallaID != null) || (this.sallaID != null && !this.sallaID.equals(other.sallaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Salla[ sallaID=" + sallaID + " ]";
    }
    
}
