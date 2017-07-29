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
@Table(name = "Pagesa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pagesa.findAll", query = "SELECT p FROM Pagesa p")
    , @NamedQuery(name = "Pagesa.findByPagesaID", query = "SELECT p FROM Pagesa p WHERE p.pagesaID = :pagesaID")
    , @NamedQuery(name = "Pagesa.findByShumaEPaguar", query = "SELECT p FROM Pagesa p WHERE p.shumaEPaguar = :shumaEPaguar")})
public class Pagesa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PagesaID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer pagesaID;
    @Basic(optional = false)
    @Column(name = "ShumaEPaguar")
    private double shumaEPaguar;
    @JoinColumn(name = "FaturaID", referencedColumnName = "FaturaID")
    @ManyToOne(optional = false)
    private Fatura faturaID;
    @JoinColumn(name = "StudentiID", referencedColumnName = "StudentiID")
    @ManyToOne(optional = false)
    private Studenti studentiID;

    public Pagesa() {
    }

    public Pagesa(Integer pagesaID) {
        this.pagesaID = pagesaID;
    }

    public Pagesa(Integer pagesaID, double shumaEPaguar) {
        this.pagesaID = pagesaID;
        this.shumaEPaguar = shumaEPaguar;
    }

    public Integer getPagesaID() {
        return pagesaID;
    }

    public void setPagesaID(Integer pagesaID) {
        this.pagesaID = pagesaID;
    }

    public double getShumaEPaguar() {
        return shumaEPaguar;
    }

    public void setShumaEPaguar(double shumaEPaguar) {
        this.shumaEPaguar = shumaEPaguar;
    }

    public Fatura getFaturaID() {
        return faturaID;
    }

    public void setFaturaID(Fatura faturaID) {
        this.faturaID = faturaID;
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
        hash += (pagesaID != null ? pagesaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pagesa)) {
            return false;
        }
        Pagesa other = (Pagesa) object;
        if ((this.pagesaID == null && other.pagesaID != null) || (this.pagesaID != null && !this.pagesaID.equals(other.pagesaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Pagesa[ pagesaID=" + pagesaID + " ]";
    }
    
}
