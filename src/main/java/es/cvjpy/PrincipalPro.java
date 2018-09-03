/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;

import es.cvjpy.aplicacion.Configuracion;
import es.cvjpy.aplicacion.ConfiguracionPro;
import es.cvjpy.aplicacion.Preferencias;
import es.cvjpy.aplicacion.PreferenciasPro;
import es.cvjpy.aplicacion.SalirPro;
import es.cvjpy.cobol.utilidades.ReconstruirIdxGenPro;
import es.cvjpy.cobol.utilidades.ReconstruirIdxPro;

/**
 *
 * @author CarlosVJ
 */
public interface PrincipalPro {

    String getBase();

    void setBase(String base);

    String getUsuarioRemoto();

    void setUsuarioRemoto(String userName);

    ConfiguracionPro getConfiguracionPro();

    PreferenciasPro getPreferenciasPro();

    SalirPro getSalirPro();

    TextoPlanoPro getTextoPlanoPro();

    ;

    ReconstruirIdxPro getReconstruirIdxPro();

    ReconstruirIdxGenPro getReconstruirIdxGenPro();

    ;

    Object getProgramaServidor(Class cachoPro) throws Exception;

    ;
    Preferencias getPreferencias() throws Exception;

    void setPreferencias(Preferencias preferencias) throws Exception;

    Configuracion getConfiguracion() throws Exception;

    void guardarPreferencias(Preferencias preferencias) throws Exception;

    void guardarConfiguracion(Configuracion configuracion) throws Exception;

    void guardarObjetoUser(String nombre, Object objeto) throws Exception;

    Object leerObjetoUser(String nombre) throws Exception;

    void fin();

}
