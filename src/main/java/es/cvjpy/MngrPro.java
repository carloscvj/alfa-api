/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;

import java.util.List;

/**
 *
 * @author CarlosVJ
 * @param <COSA>
 */
public interface MngrPro<COSA> {

    List<Clasificado> crearClasificaciones();

    List<COSA> paginaPrimera() throws Exception;

    List<COSA> paginaAnterior() throws Exception;

    List<COSA> paginaSiguiente() throws Exception;

    List<COSA> paginaUltima() throws Exception;

    void relista(Clasificado clasificarPorSelected, String buscar) throws Exception;

    ;
    COSA getEntidad(Object id) throws Exception;

    void grabarEntidad(COSA cosa) throws Exception;

    void borrarEntidad(COSA cosa) throws Exception;

    void confirmar() throws Exception;

    ;

    COSA primero() throws Exception;

    COSA anterior() throws Exception;

    COSA siguiente() throws Exception;

    COSA ultimo() throws Exception;

    COSA ponteEnEste(COSA cosa) throws Exception;

    COSA nuevo() throws Exception;

    boolean isNuevo();

    void setNuevo(boolean sino);

    ;
    void grabarEnCobol(COSA cosa) throws Exception;

    void borrarEnCobol(COSA cosa) throws Exception;

    ;
    void grabarAmbos(COSA cosa) throws Exception;

    void borrarAmbos(COSA cosa) throws Exception;
}
