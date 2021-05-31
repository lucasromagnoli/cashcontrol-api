package br.com.lucasromagnoli.cashcontrol.dominio.persistencia.common;

import java.io.Serializable;

/**
 * @author github.com/lucasromagnoli
 * @since 05/2021
 */
public abstract class TransferObject<K extends Serializable> implements Serializable, Cloneable {
    public abstract K getKey();

    public boolean isPersistido() {
        return getKey() != null;
    }

    public boolean isNotPersistido() {
        return getKey() == null;
    }

    @Override
    public Object clone() {
        try {
            return super.clone();
        } catch (CloneNotSupportedException ex) {
            return null;
        }
    }
}
