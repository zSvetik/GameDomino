package com.my.GameDomino.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

// Цепочка из набора доминошек
public class ChainDomino implements Serializable {

	private static final long serialVersionUID = 1L;

	private String id;
	private String stringChainDomino;
	private List<ChainDominoHistory> listChainDominoHistory;

	public ChainDomino() {
		this("", "", new ArrayList<>());
	}

	public ChainDomino(String id, String stringListDomino, List<ChainDominoHistory> listChainDominoHistory) {
		this.id = id;
		this.stringChainDomino = stringListDomino;
		this.listChainDominoHistory = new ArrayList<>(listChainDominoHistory);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getStringChainDomino() {
		return stringChainDomino;
	}

	public void setStringChainDomino(String stringChainDomino) {
		this.stringChainDomino = stringChainDomino;
	}

	public List<ChainDominoHistory> getListChainDominoHistory() {
		return listChainDominoHistory;
	}

	public void setListChainDominoHistory(List<ChainDominoHistory> listChainDominoHistory) {
		this.listChainDominoHistory = new ArrayList<>(listChainDominoHistory);
	}

	public ChainDominoHistory getMaxChain() {
		if (listChainDominoHistory == null || listChainDominoHistory.isEmpty()) {
			return new ChainDominoHistory();
		}
		return listChainDominoHistory.stream().max((s1, s2) -> ((Integer) s1.getStringChainHistory().length())
				.compareTo(s2.getStringChainHistory().length())).get();
	}

	public boolean addChainDominoHistory(ChainDominoHistory chainDominoHistory) {
		return listChainDominoHistory.add(chainDominoHistory);
	}

	public boolean containsChainDominoHistory(ChainDominoHistory chainDominoHistory) {
		return listChainDominoHistory.contains(chainDominoHistory);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((listChainDominoHistory == null) ? 0 : listChainDominoHistory.hashCode());
		result = prime * result + ((stringChainDomino == null) ? 0 : stringChainDomino.hashCode());
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
		ChainDomino other = (ChainDomino) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (listChainDominoHistory == null) {
			if (other.listChainDominoHistory != null)
				return false;
		} else if (!listChainDominoHistory.equals(other.listChainDominoHistory))
			return false;
		if (stringChainDomino == null) {
			if (other.stringChainDomino != null)
				return false;
		} else if (!stringChainDomino.equals(other.stringChainDomino))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return stringChainDomino;
	}

}
