/*
 * To change this template, choose Tools | Template
 * and open the template in the editor.
 */
package es.cvjpy.aplicacion;

/**
 *
 * @author CarlosVJ
 */
public interface PreferenciasPro {

    Preferencias getPreferencias() throws Exception;

    void guardarPreferencias(Preferencias preferencias) throws Exception;

}
