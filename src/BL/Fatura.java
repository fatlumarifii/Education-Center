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
@Table(name = "Fatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Fatura.findAll", query = "SELECT f FROM Fatura f")
    , @NamedQuery(name = "Fatura.findByFaturaID", query = "SELECT f FROM Fatura f WHERE f.faturaID = :faturaID")
    , @NamedQuery(name = "Fatura.findByNumriFatures", query = "SELECT f FROM Fatura f WHERE f.numriFatures = :numriFatures")
    , @NamedQuery(name = "Fatura.findByDataFaturimit", query = "SELECT f FROM Fatura f WHERE f.dataFaturimit = :dataFaturimit")
    , @NamedQuery(name = "Fatura.findByTotali", query = "SELECT f FROM Fatura f WHERE f.totali = :totali")})
public class Fatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FaturaID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer faturaID;
    @Basic(optional = false)
    @Column(name = "NumriFatures")
    private String numriFatures;
    @Column(name = "DataFaturimit")
    @Temporal(TemporalType.TIMESTAMP)
    private Date dataFaturimit;
    @Basic(optional = false)
    @Column(name = "Totali")
    private double totali;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "faturaID")
    private Collection<FaturaDetaje> faturaDetajeCollection;
    @OneToMany(cascade = CascadeType.MERGE, mappedBy = "faturaID")
    private Collection<Pagesa> pagesaCollection;

    public Fatura() {
    }

    public Fatura(Integer faturaID) {
        this.faturaID = faturaID;
    }

    public Fatura(Integer faturaID, String numriFatures, double totali) {
        this.faturaID = faturaID;
        this.numriFatures = numriFatures;
        this.totali = totali;
    }

    public Integer getFaturaID() {
        return faturaID;
    }

    public void setFaturaID(Integer faturaID) {
        this.faturaID = faturaID;
    }

    public String getNumriFatures() {
        return numriFatures;
    }

    public void setNumriFatures(String numriFatures) {
        this.numriFatures = numriFatures;
    }

    public Date getDataFaturimit() {
        return dataFaturimit;
    }

    public void setDataFaturimit(Date dataFaturimit) {
        this.dataFaturimit = dataFaturimit;
    }

    public double getTotali() {
        return totali;
    }

    public void setTotali(double totali) {
        this.totali = totali;
    }

    @XmlTransient
    public Collection<FaturaDetaje> getFaturaDetajeCollection() {
        return faturaDetajeCollection;
    }

    public void setFaturaDetajeCollection(Collection<FaturaDetaje> faturaDetajeCollection) {
        this.faturaDetajeCollection = faturaDetajeCollection;
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
        hash += (faturaID != null ? faturaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fatura)) {
            return false;
        }
        Fatura other = (Fatura) object;
        if ((this.faturaID == null && other.faturaID != null) || (this.faturaID != null && !this.faturaID.equals(other.faturaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Fatura[ faturaID=" + faturaID + " ]";
    }
    
}
