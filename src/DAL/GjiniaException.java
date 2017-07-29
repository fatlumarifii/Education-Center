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
public class GjiniaException extends Exception {

    /**
     * Creates a new instance of <code>GjiniaException</code> without detail
     * message.
     */
    public GjiniaException() {
    }

    /**
     * Constructs an instance of <code>GjiniaException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public GjiniaException(String msg) {
        super(msg);
    }
}
