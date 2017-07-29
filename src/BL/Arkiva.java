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
import javax.persistence.Lob;
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
@Table(name = "Arkiva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Arkiva.findAll", query = "SELECT a FROM Arkiva a")
    , @NamedQuery(name = "Arkiva.findByArkivaID", query = "SELECT a FROM Arkiva a WHERE a.arkivaID = :arkivaID")
    , @NamedQuery(name = "Arkiva.findByEmriFajllit", query = "SELECT a FROM Arkiva a WHERE a.emriFajllit = :emriFajllit")
    , @NamedQuery(name = "Arkiva.findByDataPublikimit", query = "SELECT a FROM Arkiva a WHERE a.dataPublikimit = :dataPublikimit")
    , @NamedQuery(name = "Arkiva.findByMbrapaShtesa", query = "SELECT a FROM Arkiva a WHERE a.mbrapaShtesa = :mbrapaShtesa")})
public class Arkiva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Arkiva_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer arkivaID;
    @Basic(optional = false)
    @Lob
    @Column(name = "Fajlli")
    private byte[] fajlli;
    @Basic(optional = false)
    @Column(name = "EmriFajllit")
    private String emriFajllit;
    @Basic(optional = false)
    @Column(name = "Data_Publikimit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataPublikimit;
    @Basic(optional = false)
    @Column(name = "MbrapaShtesa")
    private String mbrapaShtesa;
    @JoinColumn(name = "ProfesoriID", referencedColumnName = "ProfesoriID")
    @ManyToOne
    private Profesori profesoriID;

    public Arkiva() {
    }

    public Arkiva(Integer arkivaID) {
        this.arkivaID = arkivaID;
    }

    public Arkiva(Integer arkivaID, byte[] fajlli, String emriFajllit, Date dataPublikimit, String mbrapaShtesa) {
        this.arkivaID = arkivaID;
        this.fajlli = fajlli;
        this.emriFajllit = emriFajllit;
        this.dataPublikimit = dataPublikimit;
        this.mbrapaShtesa = mbrapaShtesa;
    }

    public Integer getArkivaID() {
        return arkivaID;
    }

    public void setArkivaID(Integer arkivaID) {
        this.arkivaID = arkivaID;
    }

    public byte[] getFajlli() {
        return fajlli;
    }

    public void setFajlli(byte[] fajlli) {
        this.fajlli = fajlli;
    }

    public String getEmriFajllit() {
        return emriFajllit;
    }

    public void setEmriFajllit(String emriFajllit) {
        this.emriFajllit = emriFajllit;
    }

    public Date getDataPublikimit() {
        return dataPublikimit;
    }

    public void setDataPublikimit(Date dataPublikimit) {
        this.dataPublikimit = dataPublikimit;
    }

    public String getMbrapaShtesa() {
        return mbrapaShtesa;
    }

    public void setMbrapaShtesa(String mbrapaShtesa) {
        this.mbrapaShtesa = mbrapaShtesa;
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
        hash += (arkivaID != null ? arkivaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arkiva)) {
            return false;
        }
        Arkiva other = (Arkiva) object;
        if ((this.arkivaID == null && other.arkivaID != null) || (this.arkivaID != null && !this.arkivaID.equals(other.arkivaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Arkiva[ arkivaID=" + arkivaID + " ]";
    }
    
}
