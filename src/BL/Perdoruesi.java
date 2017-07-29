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
@Table(name = "Perdoruesi")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Perdoruesi.findAll", query = "SELECT p FROM Perdoruesi p")
    , @NamedQuery(name = "Perdoruesi.findByPerdoruesiID", query = "SELECT p FROM Perdoruesi p WHERE p.perdoruesiID = :perdoruesiID")
    , @NamedQuery(name = "Perdoruesi.findByEmriPerdoruesit", query = "SELECT p FROM Perdoruesi p WHERE p.emriPerdoruesit = :emriPerdoruesit")
    , @NamedQuery(name = "Perdoruesi.findByPasswordiPerdoruesit", query = "SELECT p FROM Perdoruesi p WHERE p.passwordiPerdoruesit = :passwordiPerdoruesit")
    , @NamedQuery(name = "Perdoruesi.findByRoli", query = "SELECT p FROM Perdoruesi p WHERE p.roli = :roli")})
public class Perdoruesi implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Perdoruesi_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer perdoruesiID;
    @Basic(optional = false)
    @Column(name = "Emri_Perdoruesit")
    private String emriPerdoruesit;
    @Basic(optional = false)
    @Column(name = "Passwordi_Perdoruesit")
    private String passwordiPerdoruesit;
    @Basic(optional = false)
    @Column(name = "Roli")
    private String roli;

    public Perdoruesi() {
    }

    public Perdoruesi(Integer perdoruesiID) {
        this.perdoruesiID = perdoruesiID;
    }

    public Perdoruesi(Integer perdoruesiID, String emriPerdoruesit, String passwordiPerdoruesit, String roli) {
        this.perdoruesiID = perdoruesiID;
        this.emriPerdoruesit = emriPerdoruesit;
        this.passwordiPerdoruesit = passwordiPerdoruesit;
        this.roli = roli;
    }

    public Integer getPerdoruesiID() {
        return perdoruesiID;
    }

    public void setPerdoruesiID(Integer perdoruesiID) {
        this.perdoruesiID = perdoruesiID;
    }

    public String getEmriPerdoruesit() {
        return emriPerdoruesit;
    }

    public void setEmriPerdoruesit(String emriPerdoruesit) {
        this.emriPerdoruesit = emriPerdoruesit;
    }

    public String getPasswordiPerdoruesit() {
        return passwordiPerdoruesit;
    }

    public void setPasswordiPerdoruesit(String passwordiPerdoruesit) {
        this.passwordiPerdoruesit = passwordiPerdoruesit;
    }

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (perdoruesiID != null ? perdoruesiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Perdoruesi)) {
            return false;
        }
        Perdoruesi other = (Perdoruesi) object;
        if ((this.perdoruesiID == null && other.perdoruesiID != null) || (this.perdoruesiID != null && !this.perdoruesiID.equals(other.perdoruesiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Perdoruesi[ perdoruesiID=" + perdoruesiID + " ]";
    }
    
}
