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
@Table(name = "Profesori")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Profesori.findAll", query = "SELECT p FROM Profesori p")
    , @NamedQuery(name = "Profesori.findByProfesoriID", query = "SELECT p FROM Profesori p WHERE p.profesoriID = :profesoriID")
    , @NamedQuery(name = "Profesori.findByEmri", query = "SELECT p FROM Profesori p WHERE p.emri = :emri")
    , @NamedQuery(name = "Profesori.findByMbiemri", query = "SELECT p FROM Profesori p WHERE p.mbiemri = :mbiemri")
    , @NamedQuery(name = "Profesori.findByKombesia", query = "SELECT p FROM Profesori p WHERE p.kombesia = :kombesia")
    , @NamedQuery(name = "Profesori.findByDateLindja", query = "SELECT p FROM Profesori p WHERE p.dateLindja = :dateLindja")
    , @NamedQuery(name = "Profesori.findByStatusi", query = "SELECT p FROM Profesori p WHERE p.statusi = :statusi")
    , @NamedQuery(name = "Profesori.findByEmail", query = "SELECT p FROM Profesori p WHERE p.email = :email")
    , @NamedQuery(name = "Profesori.findByNrPersonal", query = "SELECT p FROM Profesori p WHERE p.nrPersonal = :nrPersonal")
    , @NamedQuery(name = "Profesori.findByRruga", query = "SELECT p FROM Profesori p WHERE p.rruga = :rruga")
    , @NamedQuery(name = "Profesori.findByRoli", query = "SELECT p FROM Profesori p WHERE p.roli = :roli")})
public class Profesori implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "ProfesoriID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer profesoriID;
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
    @Column(name = "DateLindja")
    @Temporal(TemporalType.DATE)
    private Date dateLindja;
    @Basic(optional = false)
    @Column(name = "Statusi")
    private boolean statusi;
    @Basic(optional = false)
    @Column(name = "Email")
    private String email;
    @Basic(optional = false)
    @Column(name = "NrPersonal")
    private long nrPersonal;
    @Basic(optional = false)
    @Column(name = "Rruga")
    private String rruga;
    @Basic(optional = false)
    @Column(name = "Roli")
    private String roli;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "profesoriID")
    private Collection<Nota> notaCollection;
    @JoinColumn(name = "Gjinia_id", referencedColumnName = "Gjinia_ID")
    @ManyToOne(optional = false)
    private Gjinia gjiniaid;
    @JoinColumn(name = "Qyteti_id", referencedColumnName = "Qyteti_ID")
    @ManyToOne(optional = false)
    private Qyteti qytetiid;
    @OneToMany(mappedBy = "profesoriID")
    private Collection<ProfesorLenda> profesorLendaCollection;
    @OneToMany(mappedBy = "profesoriID")
    private Collection<Arkiva> arkivaCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "profesoriID")
    private Collection<Orari> orariCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "profesoriID")
    private Collection<TelProfesori> telProfesoriCollection;

    public Profesori() {
    }

    public Profesori(Integer profesoriID) {
        this.profesoriID = profesoriID;
    }

    public Profesori(Integer profesoriID, String emri, String mbiemri, String kombesia, Date dateLindja, boolean statusi, String email, long nrPersonal, String rruga, String roli) {
        this.profesoriID = profesoriID;
        this.emri = emri;
        this.mbiemri = mbiemri;
        this.kombesia = kombesia;
        this.dateLindja = dateLindja;
        this.statusi = statusi;
        this.email = email;
        this.nrPersonal = nrPersonal;
        this.rruga = rruga;
        this.roli = roli;
    }

    public Integer getProfesoriID() {
        return profesoriID;
    }

    public void setProfesoriID(Integer profesoriID) {
        this.profesoriID = profesoriID;
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

    public Date getDateLindja() {
        return dateLindja;
    }

    public void setDateLindja(Date dateLindja) {
        this.dateLindja = dateLindja;
    }

    public boolean getStatusi() {
        return statusi;
    }

    public void setStatusi(boolean statusi) {
        this.statusi = statusi;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
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

    @XmlTransient
    public Collection<Nota> getNotaCollection() {
        return notaCollection;
    }

    public void setNotaCollection(Collection<Nota> notaCollection) {
        this.notaCollection = notaCollection;
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
    public Collection<ProfesorLenda> getProfesorLendaCollection() {
        return profesorLendaCollection;
    }

    public void setProfesorLendaCollection(Collection<ProfesorLenda> profesorLendaCollection) {
        this.profesorLendaCollection = profesorLendaCollection;
    }

    @XmlTransient
    public Collection<Arkiva> getArkivaCollection() {
        return arkivaCollection;
    }

    public void setArkivaCollection(Collection<Arkiva> arkivaCollection) {
        this.arkivaCollection = arkivaCollection;
    }

    @XmlTransient
    public Collection<Orari> getOrariCollection() {
        return orariCollection;
    }

    public void setOrariCollection(Collection<Orari> orariCollection) {
        this.orariCollection = orariCollection;
    }

    @XmlTransient
    public Collection<TelProfesori> getTelProfesoriCollection() {
        return telProfesoriCollection;
    }

    public void setTelProfesoriCollection(Collection<TelProfesori> telProfesoriCollection) {
        this.telProfesoriCollection = telProfesoriCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (profesoriID != null ? profesoriID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Profesori)) {
            return false;
        }
        Profesori other = (Profesori) object;
        if ((this.profesoriID == null && other.profesoriID != null) || (this.profesoriID != null && !this.profesoriID.equals(other.profesoriID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return emri + " " + mbiemri;
    }
    
}
