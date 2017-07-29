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
@Table(name = "Studenti")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Studenti.findAll", query = "SELECT s FROM Studenti s")
    , @NamedQuery(name = "Studenti.findByStudentiID", query = "SELECT s FROM Studenti s WHERE s.studentiID = :studentiID")
    , @NamedQuery(name = "Studenti.findByEmri", query = "SELECT s FROM Studenti s WHERE s.emri = :emri")
    , @NamedQuery(name = "Studenti.findByMbiemri", query = "SELECT s FROM Studenti s WHERE s.mbiemri = :mbiemri")
    , @NamedQuery(name = "Studenti.findByKombesia", query = "SELECT s FROM Studenti s WHERE s.kombesia = :kombesia")
    , @NamedQuery(name = "Studenti.findByDitelindja", query = "SELECT s FROM Studenti s WHERE s.ditelindja = :ditelindja")
    , @NamedQuery(name = "Studenti.findByEmail", query = "SELECT s FROM Studenti s WHERE s.email = :email")
    , @NamedQuery(name = "Studenti.findByStatusi", query = "SELECT s FROM Studenti s WHERE s.statusi = :statusi")
    , @NamedQuery(name = "Studenti.findByNrPersonal", query = "SELECT s FROM Studenti s WHERE s.nrPersonal = :nrPersonal")
    , @NamedQuery(name = "Studenti.findByRruga", query = "SELECT s FROM Studenti s WHERE s.rruga = :rruga")
    , @NamedQuery(name = "Studenti.findByRoli", query = "SELECT s FROM Studenti s WHERE s.roli = :roli")})
public class Studenti implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "StudentiID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer studentiID;
    @Basic(optional = false)
    @Column(name = "Emri")
    private String emri;
    @Basic(optional = false)
    @Column(name = "Mbiemri")
    private String mbiemri;
    @Basic(optional = false)
    @Column(name = "Kombesia")
    private String kombesia;
    @Basic(optional = false)
    @Column(name = "Ditelindja")
    @Temporal(TemporalType.DATE)
    private Date ditelindja;
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
    @JoinColumn(name = "GrupID", referencedColumnName = "Grupi_ID")
    @ManyToOne(optional = false)
    private Grupi grupID;
    @JoinColumn(name = "Qyteti_id", referencedColumnName = "Qyteti_ID")
    @ManyToOne(optional = false)
    private Qyteti qytetiid;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "studentiID")
    private Collection<TelStudenti> telStudentiCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "studentiID")
    private Collection<Nota> notaCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "studentiID")
    private Collection<Pagesa> pagesaCollection;

    public Studenti() {
    }

    public Studenti(Integer studentiID) {
        this.studentiID = studentiID;
    }

    public Studenti(Integer studentiID, String emri, String mbiemri, String kombesia, Date ditelindja, String email, boolean statusi, long nrPersonal, String rruga, String roli) {
        this.studentiID = studentiID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.kombesia = kombesia;
        this.ditelindja = ditelindja;
        this.email = email;
        this.statusi = statusi;
        this.nrPersonal = nrPersonal;
        this.rruga = rruga;
        this.roli = roli;
    }

    public Integer getStudentiID() {
        return studentiID;
    }

    public void setStudentiID(Integer studentiID) {
        this.studentiID = studentiID;
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

    public String getKombesia() {
        return kombesia;
    }

    public void setKombesia(String kombesia) {
        this.kombesia = kombesia;
    }

    public Date getDitelindja() {
        return ditelindja;
    }

    public void setDitelindja(Date ditelindja) {
        this.ditelindja = ditelindja;
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

    public Grupi getGrupID() {
        return grupID;
    }

    public void setGrupID(Grupi grupID) {
        this.grupID = grupID;
    }

    public Qyteti getQytetiid() {
        return qytetiid;
    }

    public void setQytetiid(Qyteti qytetiid) {
        this.qytetiid = qytetiid;
    }

    @XmlTransient
    public Collection<TelStudenti> getTelStudentiCollection() {
        return telStudentiCollection;
    }

    public void setTelStudentiCollection(Collection<TelStudenti> telStudentiCollection) {
        this.telStudentiCollection = telStudentiCollection;
    }

    @XmlTransient
    public Collection<Nota> getNotaCollection() {
        return notaCollection;
    }

    public void setNotaCollection(Collection<Nota> notaCollection) {
        this.notaCollection = notaCollection;
    }

    @XmlTransient
    public Collection<Pagesa> getPagesaCollection() {
        return pagesaCollection;
    }

    public void setPagesaCollection(Collection<Pagesa> pagesaCollection) {
        this.pagesaCollection = pagesaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentiID != null ? studentiID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Studenti)) {
            return false;
        }
        Studenti other = (Studenti) object;
        if ((this.studentiID == null && other.studentiID != null) || (this.studentiID != null && !this.studentiID.equals(other.studentiID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Studenti[ studentiID=" + studentiID + " ]";
    }
    
    
    public String toString1() {
        return studentiID + " " + emri + " " +mbiemri;
    }
    
    public String toString2() {
        return  emri + " " +mbiemri +" "+ studentiID;
    }
}
