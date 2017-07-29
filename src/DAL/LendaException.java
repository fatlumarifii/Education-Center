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
public class LendaException extends Exception {

    /**
     * Creates a new instance of <code>LendaException</code> without detail
     * message.
     */
    public LendaException() {
    }

    /**
     * Constructs an instance of <code>LendaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public LendaException(String msg) {
        super(msg);
    }
}
