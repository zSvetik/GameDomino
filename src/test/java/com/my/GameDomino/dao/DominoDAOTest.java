package com.my.GameDomino.dao;

import java.sql.SQLException;
import java.util.Arrays;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.my.GameDomino.entity.*;

public class DominoDAOTest {

    DominoDAO dominoDao;

    ChainDomino chainDomino;

    @Before
    public void setUp() throws SQLException {
        dominoDao = new DominoDAOImpl();
        chainDomino = new ChainDomino("", "{[0:1], [1:5], [3:5], [5:5]}",
                Arrays.asList(new ChainDominoHistory("0", "[{[0:1], [1:5], [5:5], [3:5]}]"), new ChainDominoHistory("1", "[{[0:1]}]"),
                        new ChainDominoHistory("2", "[{[1:5], [5:5], [3:5]}]"),
                        new ChainDominoHistory("3", "[{[3:5], [5:5], [1:5], [0:1]}]"),
                        new ChainDominoHistory("4", "[{[5:5], [1:5], [0:1]}]"), new ChainDominoHistory("5", "[{[5:5], [3:5]}]")));
    }

    @Ignore
    @Test
    public void crdChainTest() {
        String id = "0000000000000";
        chainDomino.setId(id);

        boolean resultSave = dominoDao.saveChains(chainDomino);
        Assert.assertEquals(resultSave, true);

        ChainDomino chainDominoResult = dominoDao.getChainById(id);
        Assert.assertEquals(chainDominoResult, chainDomino);

        boolean resultDelete = dominoDao.deleteChainsById(id);
        Assert.assertEquals(resultDelete, true);

    }
}
