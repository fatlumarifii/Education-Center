/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BL;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Arbër Suka
 */
@Entity
@Table(name = "Adminstrata")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Adminstrata.findAll", query = "SELECT a FROM Adminstrata a")
    , @NamedQuery(name = "Adminstrata.findByAdminstrataID", query = "SELECT a FROM Adminstrata a WHERE a.adminstrataID = :adminstrataID")
    , @NamedQuery(name = "Adminstrata.findByEmri", query = "SELECT a FROM Adminstrata a WHERE a.emri = :emri")
    , @NamedQuery(name = "Adminstrata.findByMbiemri", query = "SELECT a FROM Adminstrata a WHERE a.mbiemri = :mbiemri")
    , @NamedQuery(name = "Adminstrata.findByPozita", query = "SELECT a FROM Adminstrata a WHERE a.pozita = :pozita")
    , @NamedQuery(name = "Adminstrata.findByDateLindja", query = "SELECT a FROM Adminstrata a WHERE a.dateLindja = :dateLindja")
    , @NamedQuery(name = "Adminstrata.findByKombesia", query = "SELECT a FROM Adminstrata a WHERE a.kombesia = :kombesia")
    , @NamedQuery(name = "Adminstrata.findByEmail", query = "SELECT a FROM Adminstrata a WHERE a.email = :email")
    , @NamedQuery(name = "Adminstrata.findByStatusi", query = "SELECT a FROM Adminstrata a WHERE a.statusi = :statusi")
    , @NamedQuery(name = "Adminstrata.findByNrPersonal", query = "SELECT a FROM Adminstrata a WHERE a.nrPersonal = :nrPersonal")
    , @NamedQuery(name = "Adminstrata.findByRruga", query = "SELECT a FROM Adminstrata a WHERE a.rruga = :rruga")
    , @NamedQuery(name = "Adminstrata.findByRoli", query = "SELECT a FROM Adminstrata a WHERE a.roli = :roli")})
public class Adminstrata implements Serializable {//Serializable eshte interface pa asnje metod

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "AdminstrataID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer adminstrataID;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Basic(optional = false)
    @Column(name = "Mbiemri")
    private String mbiemri;
    @Basic(optional = false)
    @Column(name = "Pozita")
    private String pozita;
    @Basic(optional = false)
    @Column(name = "DateLindja")
    @Temporal(TemporalType.DATE)
    private Date dateLindja;
    @Basic(optional = false)
    @Column(name = "Kombesia")
    private String kombesia;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private boolean statusi;
    @Basic(optional = false)
    @Column(name = "NrPersonal")
    private long nrPersonal;
    @Basic(optional = false)
    @Column(name = "Rruga")
    private String rruga;
    @Basic(optional = false)
    @Column(name = "Roli")
    private String roli;
    @JoinColumn(name = "Gjinia_id", referencedColumnName = "Gjinia_ID")
    @ManyToOne(optional = false)
    private Gjinia gjiniaid;
    @JoinColumn(name = "Qyteti_id", referencedColumnName = "Qyteti_ID")
    @ManyToOne(optional = false)
    private Qyteti qytetiid;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "administrataID")
    private Collection<TelAdministrata> telAdministrataCollection;

    public Adminstrata() {
    }

    public Adminstrata(Integer adminstrataID) {
        this.adminstrataID = adminstrataID;
    }

    public Adminstrata(Integer adminstrataID, String emri, String mbiemri, String pozita, Date dateLindja, String kombesia, String email, boolean statusi, long nrPersonal, String rruga, String roli) {
        this.adminstrataID = adminstrataID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.pozita = pozita;
        this.dateLindja = dateLindja;
        this.kombesia = kombesia;
        this.email = email;
        this.statusi = statusi;
        this.nrPersonal = nrPersonal;
        this.rruga = rruga;
        this.roli = roli;
    }

    public Integer getAdminstrataID() {
        return adminstrataID;
    }

    public void setAdminstrataID(Integer adminstrataID) {
        this.adminstrataID = adminstrataID;
    }

    public String getEmri() {
        return emri;
    }

    public void setEmri(String emri) {
        this.emri = emri;
    }

    public String getMbiemri() {
        return mbiemri;
    }

    public void setMbiemri(String mbiemri) {
        this.mbiemri = mbiemri;
    }

    public String getPozita() {
        return pozita;
    }

    public void setPozita(String pozita) {
        this.pozita = pozita;
    }

    public Date getDateLindja() {
        return dateLindja;
    }

    public void setDateLindja(Date dateLindja) {
        this.dateLindja = dateLindja;
    }

    public String getKombesia() {
        return kombesia;
    }

    public void setKombesia(String kombesia) {
        this.kombesia = kombesia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean getStatusi() {
        return statusi;
    }

    public void setStatusi(boolean statusi) {
        this.statusi = statusi;
    }

    public long getNrPersonal() {
        return nrPersonal;
    }

    public void setNrPersonal(long nrPersonal) {
        this.nrPersonal = nrPersonal;
    }

    public String getRruga() {
        return rruga;
    }

    public void setRruga(String rruga) {
        this.rruga = rruga;
    }

    public String getRoli() {
        return roli;
    }

    public void setRoli(String roli) {
        this.roli = roli;
    }

    public Gjinia getGjiniaid() {
        return gjiniaid;
    }

    public void setGjiniaid(Gjinia gjiniaid) {
        this.gjiniaid = gjiniaid;
    }

    public Qyteti getQytetiid() {
        return qytetiid;
    }

    public void setQytetiid(Qyteti qytetiid) {
        this.qytetiid = qytetiid;
    }

    @XmlTransient
    public Collection<TelAdministrata> getTelAdministrataCollection() {
        return telAdministrataCollection;
    }

    public void setTelAdministrataCollection(Collection<TelAdministrata> telAdministrataCollection) {
        this.telAdministrataCollection = telAdministrataCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (adminstrataID != null ? adminstrataID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Adminstrata)) {
            return false;
        }
        Adminstrata other = (Adminstrata) object;
        if ((this.adminstrataID == null && other.adminstrataID != null) || (this.adminstrataID != null && !this.adminstrataID.equals(other.adminstrataID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return emri + " " +mbiemri;
    }
    
}
