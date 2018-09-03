/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package es.cvjpy;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 * @param <COSA>
 */
public class Mensajero<COSA> {

    private boolean esperar;
    private long timeOut;
    private final List<COSA> mensajes;

    public Mensajero() {
        this.esperar = true;
        this.timeOut = 60000; //Un minuto.
        this.mensajes = new ArrayList();
    }

    private boolean isEsperar() {
        return esperar;
    }

    private List<COSA> getMensajes() {
        return mensajes;
    }

    public void add(COSA m) {
        getMensajes().add(m);
    }

    @SuppressWarnings("SleepWhileInLoop")
    public COSA remove() {
        COSA ret = null;
        Date entra = new Date();
        while (getMensajes().isEmpty()) {
            if (!isEsperar()) {
                break;
            }
            Date ahora = new Date();
            if ((ahora.getTime() - entra.getTime()) > getTimeOut()) {
                break;
            }
            try {
                Thread.sleep(2000);
            } catch (InterruptedException ex) {
                Logger.getLogger(Mensajero.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (!getMensajes().isEmpty()) {
            ret = getMensajes().remove(0);
        }
        return ret;
    }

    public void setEsperar(boolean esperar) {
        this.esperar = esperar;
    }

    public long getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(long timeOut) {
        this.timeOut = timeOut;
    }
}
