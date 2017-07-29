/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Arbër Suka
 */
@Entity
@Table(name = "Orari")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Orari.findAll", query = "SELECT o FROM Orari o")
    , @NamedQuery(name = "Orari.findByOrariID", query = "SELECT o FROM Orari o WHERE o.orariID = :orariID")
    , @NamedQuery(name = "Orari.findByOraEFillimit", query = "SELECT o FROM Orari o WHERE o.oraEFillimit = :oraEFillimit")
    , @NamedQuery(name = "Orari.findByOraEMbarimit", query = "SELECT o FROM Orari o WHERE o.oraEMbarimit = :oraEMbarimit")
    , @NamedQuery(name = "Orari.findByDataOrarit", query = "SELECT o FROM Orari o WHERE o.dataOrarit = :dataOrarit")})
public class Orari implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Orari_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer orariID;
    @Basic(optional = false)
    @Column(name = "OraEFillimit")
    @Temporal(TemporalType.TIME)
    private Date oraEFillimit;
    @Basic(optional = false)
    @Column(name = "OraEMbarimit")
    @Temporal(TemporalType.TIME)
    private Date oraEMbarimit;
    @Basic(optional = false)
    @Column(name = "Data_Orarit")
    @Temporal(TemporalType.DATE)
    private Date dataOrarit;
    @JoinColumn(name = "GrupiID", referencedColumnName = "Grupi_ID")
    @ManyToOne(optional = false)
    private Grupi grupiID;
    @JoinColumn(name = "LendaID", referencedColumnName = "LendaID")
    @ManyToOne(optional = false)
    private Lenda lendaID;
    @JoinColumn(name = "ProfesoriID", referencedColumnName = "ProfesoriID")
    @ManyToOne(optional = false)
    private Profesori profesoriID;
    @JoinColumn(name = "SallaID", referencedColumnName = "SallaID")
    @ManyToOne(optional = false)
    private Salla sallaID;

    public Orari() {
    }

    public Orari(Integer orariID) {
        this.orariID = orariID;
    }

    public Orari(Integer orariID, Date oraEFillimit, Date oraEMbarimit, Date dataOrarit) {
        this.orariID = orariID;
        this.oraEFillimit = oraEFillimit;
        this.oraEMbarimit = oraEMbarimit;
        this.dataOrarit = dataOrarit;
    }

    public Integer getOrariID() {
        return orariID;
    }

    public void setOrariID(Integer orariID) {
        this.orariID = orariID;
    }

    public Date getOraEFillimit() {
        return oraEFillimit;
    }

    public void setOraEFillimit(Date oraEFillimit) {
        this.oraEFillimit = oraEFillimit;
    }

    public Date getOraEMbarimit() {
        return oraEMbarimit;
    }

    public void setOraEMbarimit(Date oraEMbarimit) {
        this.oraEMbarimit = oraEMbarimit;
    }

    public Date getDataOrarit() {
        return dataOrarit;
    }

    public void setDataOrarit(Date dataOrarit) {
        this.dataOrarit = dataOrarit;
    }

    public Grupi getGrupiID() {
        return grupiID;
    }

    public void setGrupiID(Grupi grupiID) {
        this.grupiID = grupiID;
    }

    public Lenda getLendaID() {
        return lendaID;
    }

    public void setLendaID(Lenda lendaID) {
        this.lendaID = lendaID;
    }

    public Profesori getProfesoriID() {
        return profesoriID;
    }

    public void setProfesoriID(Profesori profesoriID) {
        this.profesoriID = profesoriID;
    }

    public Salla getSallaID() {
        return sallaID;
    }

    public void setSallaID(Salla sallaID) {
        this.sallaID = sallaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (orariID != null ? orariID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Orari)) {
            return false;
        }
        Orari other = (Orari) object;
        if ((this.orariID == null && other.orariID != null) || (this.orariID != null && !this.orariID.equals(other.orariID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Orari[ orariID=" + orariID + " ]";
    }
    
}
