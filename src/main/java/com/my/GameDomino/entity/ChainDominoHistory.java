package com.my.GameDomino.entity;

import java.io.Serializable;

public class ChainDominoHistory implements Serializable {

    private String id;
    private String stringChainHistory = "";

    public ChainDominoHistory() {
    }

    public ChainDominoHistory(String id, String stringChainHistory) {
        this.id = id;
        this.stringChainHistory = stringChainHistory;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getStringChainHistory() {
        return stringChainHistory;
    }

    public void setStringChainHistory(String stringChainHistory) {
        this.stringChainHistory = stringChainHistory;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((id == null) ? 0 : id.hashCode());
        result = prime * result + ((stringChainHistory == null) ? 0 : stringChainHistory.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        ChainDominoHistory other = (ChainDominoHistory) obj;
        if (id == null) {
            if (other.id != null)
                return false;
        } else if (!id.equals(other.id))
            return false;
        if (stringChainHistory == null) {
            if (other.stringChainHistory != null)
                return false;
        } else if (!stringChainHistory.equals(other.stringChainHistory))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return stringChainHistory;
    }

}
