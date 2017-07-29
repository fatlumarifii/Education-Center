/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arbër Suka
 */
@Entity
@Table(name = "Tel_Profesori")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TelProfesori.findAll", query = "SELECT t FROM TelProfesori t")
    , @NamedQuery(name = "TelProfesori.findByNrTelefonitID", query = "SELECT t FROM TelProfesori t WHERE t.nrTelefonitID = :nrTelefonitID")
    , @NamedQuery(name = "TelProfesori.findByNrtel", query = "SELECT t FROM TelProfesori t WHERE t.nrtel = :nrtel")})
public class TelProfesori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NrTelefonit_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer nrTelefonitID;
    @Basic(optional = false)
    @Column(name = "Nr_tel")
    private String nrtel;
    @JoinColumn(name = "ProfesoriID", referencedColumnName = "ProfesoriID")
    @ManyToOne(optional = false)
    private Profesori profesoriID;

    public TelProfesori() {
    }

    public TelProfesori(Integer nrTelefonitID) {
        this.nrTelefonitID = nrTelefonitID;
    }

    public TelProfesori(Integer nrTelefonitID, String nrtel) {
        this.nrTelefonitID = nrTelefonitID;
        this.nrtel = nrtel;
    }

    public Integer getNrTelefonitID() {
        return nrTelefonitID;
    }

    public void setNrTelefonitID(Integer nrTelefonitID) {
        this.nrTelefonitID = nrTelefonitID;
    }

    public String getNrtel() {
        return nrtel;
    }

    public void setNrtel(String nrtel) {
        this.nrtel = nrtel;
    }

    public Profesori getProfesoriID() {
        return profesoriID;
    }

    public void setProfesoriID(Profesori profesoriID) {
        this.profesoriID = profesoriID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nrTelefonitID != null ? nrTelefonitID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TelProfesori)) {
            return false;
        }
        TelProfesori other = (TelProfesori) object;
        if ((this.nrTelefonitID == null && other.nrTelefonitID != null) || (this.nrTelefonitID != null && !this.nrTelefonitID.equals(other.nrTelefonitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nrtel;
    }
    
}
