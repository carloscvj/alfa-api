/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy.cobol.utilidades;

import java.io.Serializable;

/**
 *
 * @author carlos
 */
public class FicheroCobolFiltro implements Serializable {

    private String nombreInterno;
    private String nombreExterno;

    public FicheroCobolFiltro() {
    }

    public String getNombreInterno() {
        if (nombreInterno == null) {
            nombreInterno = "";
        }
        return nombreInterno.trim().toUpperCase();
    }

    public void setNombreInterno(String nombreInterno) {
        this.nombreInterno = nombreInterno;
    }

    public String getNombreExterno() {
        if (nombreExterno == null) {
            nombreExterno = "dir/";
        }
        return nombreExterno.trim();
    }

    public void setNombreExterno(String nombreExterno) {
        this.nombreExterno = nombreExterno;
    }

    public String getProgramaT() {
        String ret = getNombreInterno();
        if (ret.equals("")) {
            ret = "FNADA";
        }
        ret = "T" + ret.substring(1);
        return ret;
    }

    public void toExtern() {
        String extern = "dir/";
        String intern = getNombreInterno();
        if (intern.length() > 2) {
            String sb = intern.substring(1, 3);
            setNombreExterno(extern + sb + "/" + intern);
        }
    }

}
