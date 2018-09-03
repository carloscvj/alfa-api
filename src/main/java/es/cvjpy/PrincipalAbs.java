/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;

import es.cvjpy.aplicacion.Configuracion;
import es.cvjpy.aplicacion.Preferencias;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Properties;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 */
public abstract class PrincipalAbs implements PrincipalPro {

    private String base;
    private String usuarioRemoto;
    private Preferencias preferencias;
    private Configuracion configuracion;

    private String getCarpetaTrabajo() {
        String ret = new File("").getAbsolutePath();
        return ret;
    }

    private String getCarpetaUsers() {
        return System.getProperty("user.home", getCarpetaTrabajo()) + "/.alfa/users";
    }

    private String getCarpetaApps() {
        return System.getProperty("user.home", getCarpetaTrabajo()) + "/.alfa/apps";
    }

    private String getTotalFilePreferencias() throws Exception {
        String ret;
        ret = getCarpetaUsers() + "/" + getUsuarioRemoto().trim() + ".properties";
        String padre = new File(ret).getParentFile().getAbsolutePath();
        new File(padre).mkdirs();
        return ret;
    }

    private String getTotalFileConfiguracion() throws Exception {
        String ret;
        //String ver = getConfiguracion().getVersionAlfa().getVersion();
        ret = getCarpetaApps() + "/" + getBase().trim() + ".properties";
        String padre = new File(ret).getParentFile().getAbsolutePath();
        new File(padre).mkdirs();
        return ret;
    }

    private Properties leerPreferencias() throws Exception {
        Properties misprop = new Properties();
        File ficheroprop = new File(getTotalFilePreferencias());
        if (ficheroprop.exists()) {
            misprop.load(new FileInputStream(ficheroprop));
            //Logger.getLogger(PreferenciasCBLAbs.class.getName()).log(Level.INFO, "preferencias de " + getUsuarioRemoto() + " leidas desde:{0}", ficheroprop.getAbsolutePath());
        } else {
            ResourceBundle bunle = ResourceBundle.getBundle("preferencias");
            if (bunle != null) {
                for (String cada : bunle.keySet()) {
                    misprop.setProperty(cada, bunle.getString(cada));
                }
                //Logger.getLogger(PreferenciasCBLAbs.class.getName()).log(Level.INFO, "preferencias de " + getUsuarioRemoto() + " leidas desde resource:{0}", "preferencias");
            }
        }
        return misprop;
    }

    private Properties leerConfiguracion() throws Exception {
        Properties misprop = new Properties();
        File ficheroprop = new File(getTotalFileConfiguracion());
        if (ficheroprop.exists()) {
            misprop.load(new FileInputStream(ficheroprop));
            //Logger.getLogger(ConfiguracionCBLAbs.class.getName()).log(Level.INFO, "configuracion de " + getBase() + " leidas desde:{0}", ficheroprop.getAbsolutePath());
        } else {
            ResourceBundle bunle = ResourceBundle.getBundle("configuracion");
            if (bunle != null) {
                for (String cada : bunle.keySet()) {
                    misprop.setProperty(cada, bunle.getString(cada));
                }
                //Logger.getLogger(ConfiguracionCBLAbs.class.getName()).log(Level.INFO, "configuracion de " + getBase() + " leidas desde resource:{0}", "configuracion");
            }
        }
        return misprop;
    }

    private Preferencias crearPreferencias() throws Exception {
        Preferencias ret = new Preferencias();
        ret.fromProperties(leerPreferencias());
        return ret;
    }

    private Configuracion crearConfiguracion() throws Exception {
        Configuracion ret = new Configuracion(getBase());
        Properties leerConf = leerConfiguracion();
        ret.fromProperties(leerConf);
        if (ret.getEntornoCobol().isLocal()) {
            String baseCOBPATH = dondeEstaElExe();
            ret.getEntornoCobol().getEntornoLocal().setBaseCOBPATH(baseCOBPATH);
        }
        return ret;
    }

    protected abstract String dondeEstaElExe();

    @Override
    public String getBase() {
        return base;
    }

    @Override
    public void setBase(String base) {
        this.base = base;
    }

    @Override
    public String getUsuarioRemoto() {
        if (usuarioRemoto == null) {
            usuarioRemoto = "desconocido";
        }
        return usuarioRemoto.trim();
    }

    @Override
    public void setUsuarioRemoto(String usuarioRemoto) {
        this.usuarioRemoto = usuarioRemoto;
    }

    @Override
    public Preferencias getPreferencias() throws Exception {
        if (preferencias == null) {
            preferencias = crearPreferencias();
        }
        return preferencias;
    }

    @Override
    public void setPreferencias(Preferencias preferencias) throws Exception {
        this.preferencias = preferencias;
    }

    @Override
    public Configuracion getConfiguracion() throws Exception {
        if (configuracion == null) {
            configuracion = crearConfiguracion();
        }
        return configuracion;
    }

    @Override
    public void guardarPreferencias(Preferencias preferencias) throws Exception {
        File ficheroprop = new File(getTotalFilePreferencias());
        FileOutputStream fo = new FileOutputStream(ficheroprop);
        preferencias.toProperties().store(fo, ficheroprop.getName());
    }

    @Override
    public void guardarConfiguracion(Configuracion configuracion) throws Exception {
        File ficheroprop = new File(getTotalFileConfiguracion());
        FileOutputStream fo = new FileOutputStream(ficheroprop);
        configuracion.toProperties().store(fo, ficheroprop.getName());
    }

    @Override
    public void guardarObjetoUser(String nombre, Object objeto) {
        ObjectOutputStream fo = null;
        try {
            String nomFile = getCarpetaUsers() + "/" + getUsuarioRemoto().trim() + "/" + nombre;
            File file = new File(nomFile);
            file.getParentFile().mkdirs();
            fo = new ObjectOutputStream(new FileOutputStream(file));
            fo.writeObject(objeto);
            fo.close();
        } catch (IOException ex) { //Si no se puede escribir en la carpeta del usuario, pa qué queremos más.
            Logger.getLogger(PrincipalAbs.class.getName()).log(Level.WARNING, null, ex);
        } finally {
            try {
                if (fo != null) {
                    fo.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(PrincipalAbs.class.getName()).log(Level.WARNING, null, ex);
            }
        }

    }

    @Override
    public Object leerObjetoUser(String nombre) {
        Object ret = null;
        String nomFile = getCarpetaUsers() + "/" + getUsuarioRemoto().trim() + "/" + nombre;
        File file = new File(nomFile);
        if (file.exists()) {
            ObjectInputStream fi = null;
            try {
                fi = new ObjectInputStream(new FileInputStream(file));
                ret = fi.readObject();
                fi.close();
            } catch (FileNotFoundException ex) { //En cualquier caso de error se devuelve null y el cli ya verá
                Logger.getLogger(PrincipalAbs.class.getName()).log(Level.WARNING, null, ex);
            } catch (IOException | ClassNotFoundException ex) { //En cualquier caso de error se devuelve null y el cli ya verá
                Logger.getLogger(PrincipalAbs.class.getName()).log(Level.WARNING, null, ex);
            } finally {
                try {
                    if (fi != null) {
                        fi.close();
                    }
                } catch (IOException ex) {
                    Logger.getLogger(PrincipalAbs.class.getName()).log(Level.WARNING, null, ex);
                }
            }
        }
        if (ret == null) { //Si hay problemas de versiones lo mejor es borrar el fichero.
            if (file.exists()) {
                file.delete();
            }
        }
        return ret;
    }

    @Override
    public void fin() {
        preferencias = null;
        configuracion = null;
    }

}
