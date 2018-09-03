/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.cobol.utilidades;

import es.cvjpy.GenPro;

/**
 *
 * @author carlos
 */
public interface ReconstruirIdxGenPro extends GenPro<String> {
    
    void guardarFiltros(FicheroCobolFiltro filtros);

}
