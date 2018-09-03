/*
 * To change this template, choose Tools | Template
 * and open the template in the editor.
 */
package es.cvjpy.aplicacion;

/**
 *
 * @author CarlosVJ
 */
public interface ConfiguracionPro {

    Configuracion getConfiguracion() throws Exception;

    void guardarConfiguracion(Configuracion configuracion) throws Exception;

}
