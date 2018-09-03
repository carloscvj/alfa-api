/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.cobol.utilidades;


/**
 *
 * @author carlos
 */
public interface ReconstruirIdxPro {

    FicheroCobolFiltro crearFicheroCobolFiltro() throws Exception;

    void guardarFicheroCobolFiltro(FicheroCobolFiltro ficheroCobolFiltro) throws Exception;

    void reiniciarSesionCobol() throws Exception;

}
