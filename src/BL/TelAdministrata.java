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
@Table(name = "Tel_Administrata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TelAdministrata.findAll", query = "SELECT t FROM TelAdministrata t")
    , @NamedQuery(name = "TelAdministrata.findByNrTelefonitID", query = "SELECT t FROM TelAdministrata t WHERE t.nrTelefonitID = :nrTelefonitID")
    , @NamedQuery(name = "TelAdministrata.findByNrTel", query = "SELECT t FROM TelAdministrata t WHERE t.nrTel = :nrTel")})
public class TelAdministrata implements Serializable {

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
    @JoinColumn(name = "AdministrataID", referencedColumnName = "AdminstrataID")
    @ManyToOne(optional = false)
    private Adminstrata administrataID;

    public TelAdministrata() {
    }

    public TelAdministrata(Integer nrTelefonitID) {
        this.nrTelefonitID = nrTelefonitID;
    }

    public TelAdministrata(Integer nrTelefonitID, String nrTel) {
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

    public Adminstrata getAdministrataID() {
        return administrataID;
    }

    public void setAdministrataID(Adminstrata administrataID) {
        this.administrataID = administrataID;
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
        if (!(object instanceof TelAdministrata)) {
            return false;
        }
        TelAdministrata other = (TelAdministrata) object;
        if ((this.nrTelefonitID == null && other.nrTelefonitID != null) || (this.nrTelefonitID != null && !this.nrTelefonitID.equals(other.nrTelefonitID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return nrTel;
    }
    
}
