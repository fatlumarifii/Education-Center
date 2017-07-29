/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAL;

/**
 *
 * @author Arbër Suka
 */
public class FaturaDetajeException extends Exception {

    /**
     * Creates a new instance of <code>FaturaDetajeException</code> without
     * detail message.
     */
    public FaturaDetajeException() {
    }

    /**
     * Constructs an instance of <code>FaturaDetajeException</code> with the
     * specified detail message.
     *
     * @param msg the detail message.
     */
    public FaturaDetajeException(String msg) {
        super(msg);
    }
}
