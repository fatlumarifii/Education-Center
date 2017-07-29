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
public class TelAdministrataException extends Exception {

    /**
     * Creates a new instance of <code>TelAdministrataException</code> without
     * detail message.
     */
    public TelAdministrataException() {
    }

    /**
     * Constructs an instance of <code>TelAdministrataException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public TelAdministrataException(String msg) {
        super(msg);
    }
}
