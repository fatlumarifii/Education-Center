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
@Table(name = "Lenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Lenda.findAll", query = "SELECT l FROM Lenda l")
    , @NamedQuery(name = "Lenda.findByLendaID", query = "SELECT l FROM Lenda l WHERE l.lendaID = :lendaID")
    , @NamedQuery(name = "Lenda.findByEmriILendes", query = "SELECT l FROM Lenda l WHERE l.emriILendes = :emriILendes")
    , @NamedQuery(name = "Lenda.findByProfesoriID", query = "SELECT l FROM Lenda l WHERE l.profesoriID = :profesoriID")
    , @NamedQuery(name = "Lenda.findByKredit", query = "SELECT l FROM Lenda l WHERE l.kredit = :kredit")})
public class Lenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "LendaID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer lendaID;
    @Basic(optional = false)
    @Column(name = "EmriILendes")
    private String emriILendes;
    @Basic(optional = false)
    @Column(name = "ProfesoriID")
    private int profesoriID;
    @Basic(optional = false)
    @Column(name = "Kredit")
    private double kredit;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "lendaID")
    private Collection<Nota> notaCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "lendaID")
    private Collection<FaturaDetaje> faturaDetajeCollection;
    @OneToMany(mappedBy = "lendaID")
    private Collection<ProfesorLenda> profesorLendaCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "lendaID")
    private Collection<Orari> orariCollection;

    public Lenda() {
    }

    public Lenda(Integer lendaID) {
        this.lendaID = lendaID;
    }

    public Lenda(Integer lendaID, String emriILendes, int profesoriID, double kredit) {
        this.lendaID = lendaID;
        this.emriILendes = emriILendes;
        this.profesoriID = profesoriID;
        this.kredit = kredit;
    }

    public Integer getLendaID() {
        return lendaID;
    }

    public void setLendaID(Integer lendaID) {
        this.lendaID = lendaID;
    }

    public String getEmriILendes() {
        return emriILendes;
    }

    public void setEmriILendes(String emriILendes) {
        this.emriILendes = emriILendes;
    }

    public int getProfesoriID() {
        return profesoriID;
    }

    public void setProfesoriID(int profesoriID) {
        this.profesoriID = profesoriID;
    }

    public double getKredit() {
        return kredit;
    }

    public void setKredit(double kredit) {
        this.kredit = kredit;
    }

    @XmlTransient
    public Collection<Nota> getNotaCollection() {
        return notaCollection;
    }

    public void setNotaCollection(Collection<Nota> notaCollection) {
        this.notaCollection = notaCollection;
    }

    @XmlTransient
    public Collection<FaturaDetaje> getFaturaDetajeCollection() {
        return faturaDetajeCollection;
    }

    public void setFaturaDetajeCollection(Collection<FaturaDetaje> faturaDetajeCollection) {
        this.faturaDetajeCollection = faturaDetajeCollection;
    }

    @XmlTransient
    public Collection<ProfesorLenda> getProfesorLendaCollection() {
        return profesorLendaCollection;
    }

    public void setProfesorLendaCollection(Collection<ProfesorLenda> profesorLendaCollection) {
        this.profesorLendaCollection = profesorLendaCollection;
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
        hash += (lendaID != null ? lendaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Lenda)) {
            return false;
        }
        Lenda other = (Lenda) object;
        if ((this.lendaID == null && other.lendaID != null) || (this.lendaID != null && !this.lendaID.equals(other.lendaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Lenda[ lendaID=" + lendaID + " ]";
    }
    
}
