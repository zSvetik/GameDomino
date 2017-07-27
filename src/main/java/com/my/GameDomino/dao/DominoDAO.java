package com.my.GameDomino.dao;

import java.util.List;

import com.my.GameDomino.entity.*;

public interface DominoDAO {

    public boolean saveChains(ChainDomino chainDomino);

    public ChainDomino getChainsById(String id);

    public List<ChainDomino> getAllChains();
}
