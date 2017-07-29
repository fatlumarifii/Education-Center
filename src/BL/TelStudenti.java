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
@Table(name = "Tel_Studenti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TelStudenti.findAll", query = "SELECT t FROM TelStudenti t")
    , @NamedQuery(name = "TelStudenti.findByNrTelefonitID", query = "SELECT t FROM TelStudenti t WHERE t.nrTelefonitID = :nrTelefonitID")
    , @NamedQuery(name = "TelStudenti.findByNrTel", query = "SELECT t FROM TelStudenti t WHERE t.nrTel = :nrTel")})
public class TelStudenti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "NrTelefonit_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer nrTelefonitID;
    @Basic(optional = false)
    @Column(name = "Nr_Tel")
    private String nrTel;
    @JoinColumn(name = "StudentiID", referencedColumnName = "StudentiID")
    @ManyToOne(optional = false)
    private Studenti studentiID;

    public TelStudenti() {
    }

    public TelStudenti(Integer nrTelefonitID) {
        this.nrTelefonitID = nrTelefonitID;
    }

    public TelStudenti(Integer nrTelefonitID, String nrTel) {
        this.nrTelefonitID = nrTelefonitID;
        this.nrTel = nrTel;
    }

    public Integer getNrTelefonitID() {
        return nrTelefonitID;
    }

    public void setNrTelefonitID(Integer nrTelefonitID) {
        this.nrTelefonitID = nrTelefonitID;
    }

    public String getNrTel() {
        return nrTel;
    }

    public void setNrTel(String nrTel) {
        this.nrTel = nrTel;
    }

    public Studenti getStudentiID() {
        return studentiID;
    }

    public void setStudentiID(Studenti studentiID) {
        this.studentiID = studentiID;
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
        if (!(object instanceof TelStudenti)) {
            return false;
        }
        TelStudenti other = (TelStudenti) object;
        if ((this.nrTelefonitID == null && other.nrTelefonitID != null) || (this.nrTelefonitID != null && !this.nrTelefonitID.equals(other.nrTelefonitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.TelStudenti[ nrTelefonitID=" + nrTelefonitID + " ]";
    }
    
}
