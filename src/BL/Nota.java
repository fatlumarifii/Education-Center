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
@Table(name = "Nota")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nota.findAll", query = "SELECT n FROM Nota n")
    , @NamedQuery(name = "Nota.findByNotaID", query = "SELECT n FROM Nota n WHERE n.notaID = :notaID")
    , @NamedQuery(name = "Nota.findByNota", query = "SELECT n FROM Nota n WHERE n.nota = :nota")})
public class Nota implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "Nota_ID")
    @GeneratedValue(generator="InvSeq")
    @SequenceGenerator(name="InvSeq",sequenceName="INV_SEQ", allocationSize=1)
    private Integer notaID;
    @Basic(optional = false)
    @Column(name = "Nota")
    private int nota;
    @JoinColumn(name = "LendaID", referencedColumnName = "LendaID")
    @ManyToOne(optional = false)
    private Lenda lendaID;
    @JoinColumn(name = "ProfesoriID", referencedColumnName = "ProfesoriID")
    @ManyToOne(optional = false)
    private Profesori profesoriID;
    @JoinColumn(name = "StudentiID", referencedColumnName = "StudentiID")
    @ManyToOne(optional = false)
    private Studenti studentiID;

    public Nota() {
    }

    public Nota(Integer notaID) {
        this.notaID = notaID;
    }

    public Nota(Integer notaID, int nota) {
        this.notaID = notaID;
        this.nota = nota;
    }

    public Integer getNotaID() {
        return notaID;
    }

    public void setNotaID(Integer notaID) {
        this.notaID = notaID;
    }

    public int getNota() {
        return nota;
    }

    public void setNota(int nota) {
        this.nota = nota;
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

    public Studenti getStudentiID() {
        return studentiID;
    }

    public void setStudentiID(Studenti studentiID) {
        this.studentiID = studentiID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notaID != null ? notaID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nota)) {
            return false;
        }
        Nota other = (Nota) object;
        if ((this.notaID == null && other.notaID != null) || (this.notaID != null && !this.notaID.equals(other.notaID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "BL.Nota[ notaID=" + notaID + " ]";
    }
    
}
