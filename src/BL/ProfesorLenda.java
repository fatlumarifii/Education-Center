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
@Table(name = "Profesor_Lenda")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProfesorLenda.findAll", query = "SELECT p FROM ProfesorLenda p")
    , @NamedQuery(name = "ProfesorLenda.findByPlId", query = "SELECT p FROM ProfesorLenda p WHERE p.plId = :plId")})
public class ProfesorLenda implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "PL_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer plId;
    @JoinColumn(name = "Lenda_ID", referencedColumnName = "LendaID")
    @ManyToOne
    private Lenda lendaID;
    @JoinColumn(name = "Profesori_ID", referencedColumnName = "ProfesoriID")
    @ManyToOne
    private Profesori profesoriID;

    public ProfesorLenda() {
    }

    public ProfesorLenda(Integer plId) {
        this.plId = plId;
    }

    public Integer getPlId() {
        return plId;
    }

    public void setPlId(Integer plId) {
        this.plId = plId;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (plId != null ? plId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProfesorLenda)) {
            return false;
        }
        ProfesorLenda other = (ProfesorLenda) object;
        if ((this.plId == null && other.plId != null) || (this.plId != null && !this.plId.equals(other.plId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.ProfesorLenda[ plId=" + plId + " ]";
    }
    
}
