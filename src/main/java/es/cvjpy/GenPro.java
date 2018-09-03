/*
 * To change this template, choose Tools | Template
 * and open the template in the editor.
 */
package es.cvjpy;

import java.util.Iterator;

/**
 *
 * @author carlos
 * @param <COSA>
 */
public interface GenPro<COSA> extends Iterator<COSA> {

    void preparado();

    boolean isEjecutando();
}
