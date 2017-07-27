package com.my.GameDomino.entity;

import java.util.*;

import org.eclipse.jetty.util.StringUtil;

// Набор доминошек
public class ListDomino {

	private List<Domino> listDomino;

	public ListDomino() {
	}

	public ListDomino(List<Domino> listDomino) {
		this.listDomino = new ArrayList<>(listDomino);
	}

	public List<Domino> getListDomino() {
		return listDomino;
	}

	public void setListDomino(List<Domino> listDomino) {
		this.listDomino = new ArrayList<>(listDomino);
	}

	public void addDomino(Domino domino) {
		if (listDomino == null || listDomino.isEmpty()) {
			listDomino = new ArrayList<>();
		}
		listDomino.add(domino);
	}

	public boolean removeDomino(Domino domino) {
		for (int i = 0; i < listDomino.size(); i++) {
			if (listDomino.get(i).equals(domino)) {
				listDomino.remove(i);
				return true;
			}
		}
		return false;
	}

	public Domino getDomino(int id) {
		return listDomino.get(id);
	}

	public Domino setDomino(int id, Domino domino) {
		return listDomino.set(id, domino);
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((listDomino == null) ? 0 : listDomino.hashCode());
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
		ListDomino other = (ListDomino) obj;
		if (listDomino == null) {
			if (other.listDomino != null)
				return false;
		} else if (!listDomino.equals(other.listDomino))
			return false;
		return true;
	}

	@Override
	public String toString() {
		String result = "";
		for (Domino domino : listDomino) {
			result += domino + ", ";
		}
		if (StringUtil.isNotBlank(result)) {
			result = "{" + result.substring(0, result.length() - 2) + "}";
		}
		return result;
	}
}
