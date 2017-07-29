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
public class QytetiException extends Exception {

    /**
     * Creates a new instance of <code>QytetiException</code> without detail
     * message.
     */
    public QytetiException() {
    }

    /**
     * Constructs an instance of <code>QytetiException</code> with the specified
     * detail message.
     *
     * @param msg the detail message.
     */
    public QytetiException(String msg) {
        super(msg);
    }
}
