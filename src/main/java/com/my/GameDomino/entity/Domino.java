package com.my.GameDomino.entity;

public class Domino {

    private int id;
    private int left;
    private int right;
    private boolean isUsed = false;

    public Domino(int id, int left, int right) {
        this.left = left;
        this.right = right;
        this.id = id;
    }

    public Domino(int left, int right) {
        this(0, left, right);
    }
    
    public int getId() {
        return id;
    }

    public int getLeft() {
        return left;
    }

    public void setLeft(int left) {
        this.left = left;
    }

    public int getRight() {
        return right;
    }

    public void setRight(int right) {
        this.right = right;
    }

    public boolean isUsed() {
        return isUsed;
    }

    public void setUsed(boolean isUsed) {
        this.isUsed = isUsed;
    }

    public void turnDomino() {
        if (left > right) {
            int tmp = left;
            left = right;
            right = tmp;
        }
    }

    public boolean isThisElement(Object obj) {
        Domino other = (Domino) obj;
        other.turnDomino();
        return this.equals(other);
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + left;
        result = prime * result + right;
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
        Domino other = (Domino) obj;
        other.turnDomino();
        if (left != other.left)
            return false;
        if (right != other.right)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "[" + left + ":" + right + "]";
    }

}
