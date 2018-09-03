/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;

/**
 *
 * @author carlos
 */
public abstract class LoginAbs implements LoginPro {

    private String usuarioRemoto;

    @Override
    public String getUsuarioRemoto() {
        return usuarioRemoto;
    }

    @Override
    public void setUsuarioRemoto(String usuarioRemoto) {
        this.usuarioRemoto = usuarioRemoto;
    }

    @Override
    public void fin() throws Exception {
        this.usuarioRemoto = null;
    }
}
