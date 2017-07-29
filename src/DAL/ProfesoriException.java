/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author Fatlum
 */
public class ProfesoriException extends Exception {

    /**
     * Creates a new instance of <code>ProfesoriException</code> without detail
     * message.
     */
    public ProfesoriException() {
    }

    /**
     * Constructs an instance of <code>ProfesoriException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public ProfesoriException(String msg) {
        super(msg);
    }
}
