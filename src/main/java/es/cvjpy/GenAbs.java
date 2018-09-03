/*
 * To change this template, choose Tools | Template
 * and open the template in the editor.
 */
package es.cvjpy;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author carlos
 * @param <COSA>
 */
public abstract class GenAbs<COSA> implements GenPro<COSA> {

    private boolean ejecutando = false;
    private List<COSA> mensajes;

    private void procesando() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                setEjecutando(true);
                procesar();
                setEjecutando(false);
            }
        });
        t.start();
    }

    protected abstract void procesar();

    protected void setEjecutando(boolean ejecutando) {
        this.ejecutando = ejecutando;
    }

    public List<COSA> getMensajes() {
        return mensajes;
    }

    @Override
    public boolean isEjecutando() {
        return ejecutando;
    }

    @Override
    @SuppressWarnings("SleepWhileInLoop")
    public boolean hasNext() {
        if (mensajes == null) {
            mensajes = new ArrayList();
            procesando();
        }
        if (!getMensajes().isEmpty()) {
            return true;
        }
        for (int i = 0; i < 900000; i++) {
            try {
                Thread.sleep(200);
            } catch (InterruptedException ex) {
                Logger.getLogger(GenAbs.class.getName()).log(Level.SEVERE, null, ex);
            }
            if (!getMensajes().isEmpty()) {
                return true;
            }
            if (!isEjecutando()) {
                break;
            }
        }
        return false;
    }

    @Override
    public COSA next() {
        if (!hasNext()) {
            return null;
        }
        COSA men = getMensajes().get(0);
        getMensajes().remove(men);
        return men;
    }

    @Override
    public void remove() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void preparado() {
        setEjecutando(false);
        mensajes = null;
    }
}
