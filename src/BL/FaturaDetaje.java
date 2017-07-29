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
@Table(name = "FaturaDetaje")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FaturaDetaje.findAll", query = "SELECT f FROM FaturaDetaje f")
    , @NamedQuery(name = "FaturaDetaje.findByFdId", query = "SELECT f FROM FaturaDetaje f WHERE f.fdId = :fdId")
    , @NamedQuery(name = "FaturaDetaje.findByShuma", query = "SELECT f FROM FaturaDetaje f WHERE f.shuma = :shuma")
    , @NamedQuery(name = "FaturaDetaje.findBySasia", query = "SELECT f FROM FaturaDetaje f WHERE f.sasia = :sasia")})
public class FaturaDetaje implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "FD_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer fdId;
    @Basic(optional = false)
    @Column(name = "Shuma")
    private double shuma;
    @Basic(optional = false)
    @Column(name = "Sasia")
    private int sasia;
    @JoinColumn(name = "FaturaID", referencedColumnName = "FaturaID")
    @ManyToOne(optional = false)
    private Fatura faturaID;
    @JoinColumn(name = "LendaID", referencedColumnName = "LendaID")
    @ManyToOne(optional = false)
    private Lenda lendaID;

    public FaturaDetaje() {
    }

    public FaturaDetaje(Integer fdId) {
        this.fdId = fdId;
    }

    public FaturaDetaje(Integer fdId, double shuma, int sasia) {
        this.fdId = fdId;
        this.shuma = shuma;
        this.sasia = sasia;
    }

    public Integer getFdId() {
        return fdId;
    }

    public void setFdId(Integer fdId) {
        this.fdId = fdId;
    }

    public double getShuma() {
        return shuma;
    }

    public void setShuma(double shuma) {
        this.shuma = shuma;
    }

    public int getSasia() {
        return sasia;
    }

    public void setSasia(int sasia) {
        this.sasia = sasia;
    }

    public Fatura getFaturaID() {
        return faturaID;
    }

    public void setFaturaID(Fatura faturaID) {
        this.faturaID = faturaID;
    }

    public Lenda getLendaID() {
        return lendaID;
    }

    public void setLendaID(Lenda lendaID) {
        this.lendaID = lendaID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fdId != null ? fdId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FaturaDetaje)) {
            return false;
        }
        FaturaDetaje other = (FaturaDetaje) object;
        if ((this.fdId == null && other.fdId != null) || (this.fdId != null && !this.fdId.equals(other.fdId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.FaturaDetaje[ fdId=" + fdId + " ]";
    }
    
}
