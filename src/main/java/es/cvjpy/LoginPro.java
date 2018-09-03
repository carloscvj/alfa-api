/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;

/**
 *
 * @author CarlosVJ
 */
public interface LoginPro {

    String getUsuarioRemoto() throws Exception;

    void setUsuarioRemoto(String usuarioRemoto) throws Exception;

    Object getUsuario(String id) throws Exception;

    Object getMenuNodo(String id) throws Exception;

    void fin() throws Exception;
}
