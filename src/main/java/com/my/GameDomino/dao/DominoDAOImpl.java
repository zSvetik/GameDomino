package com.my.GameDomino.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.util.StringUtil;

import com.my.GameDomino.config.Configuration;
import com.my.GameDomino.entity.*;
import com.my.GameDomino.utils.Utils;

public class DominoDAOImpl implements DominoDAO {

    public DominoDAOImpl() {
    }

    @Override
    public boolean saveChains(ChainDomino chainDomino) {
        boolean result = saveAllChains(chainDomino);
        if (result) {
            Utils.deselectListDomino();
        }
        return result;
    }

    public boolean saveAllChains(ChainDomino chainDomino) {
        try (Connection connection = Configuration.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(createInsert(chainDomino))) {
                preparedStatement.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    @Override
    public ChainDomino getChainsById(String id) {
        ChainDomino result = new ChainDomino();
        String query = "SELECT dc.id_chain, dc.chain, dch.id_chainhistory, dch.chainhistory \n" + "FROM DominoChain AS dc \n"
                + "INNER JOIN DominoChainHistory AS dch ON dc.id_chain = dch.id_chain \n" + "AND dc.id_chain = ?";
        try (Connection connection = Configuration.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                preparedStatement.setString(1, id);
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ChainDominoHistory chainDominoHistory = new ChainDominoHistory();
                        chainDominoHistory.setId(resultSet.getString("id_chainhistory"));
                        chainDominoHistory.setStringChainHistory(resultSet.getString("chainhistory"));

                        result.setId(resultSet.getString("id_chain"));
                        result.setStringListDomino(resultSet.getString("chain"));
                        result.getListChainDominoHistory().add(chainDominoHistory);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ChainDomino> getAllChains() {
        List<ChainDomino> result = new ArrayList<>();
        ChainDomino chainDomino = new ChainDomino();
        String query = "SELECT dc.id_chain, dc.chain, dch.id_chainhistory, dch.chainhistory \n" + "FROM DominoChain AS dc \n"
                + "INNER JOIN DominoChainHistory AS dch ON dc.id_chain = dch.id_chain ";
        try (Connection connection = Configuration.getInstance().getConnection()) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    while (resultSet.next()) {
                        ChainDominoHistory chainDominoHistory = new ChainDominoHistory();
                        chainDominoHistory.setId(resultSet.getString("id_chainhistory"));
                        chainDominoHistory.setStringChainHistory(resultSet.getString("chainhistory"));

                        chainDomino.setId(resultSet.getString("id_chain"));
                        chainDomino.setStringListDomino(resultSet.getString("chain"));
                        chainDomino.getListChainDominoHistory().add(chainDominoHistory);
                        result.add(chainDomino);
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private String createInsert(ChainDomino chainDomino) {
        String id = makeId();
        int idChainDominoHistory = 0;
        StringBuilder insert = new StringBuilder(
                String.format("INSERT INTO DominoChain (id_chain, chain) values (%1, %2); ", id, chainDomino.getStringListDomino()));

        boolean isFirst = true;
        for (ChainDominoHistory chainDominoHistory : chainDomino.getListChainDominoHistory()) {
            if (isFirst) {
                insert.append(String.format("INSERT INTO DominoChainHistory (id_chain, id_chainhistory, chainhistory) values (%1, %2, %3)",
                        id, idChainDominoHistory++, chainDominoHistory.getStringChainHistory()));
                isFirst = false;
            } else {
                insert.append(String.format(", (%1, %2, %3)", id, idChainDominoHistory++, chainDominoHistory.getStringChainHistory()));
            }
        }
        return insert.toString();
    }

    private String makeId() {
        String id = Utils.randomString();
        while (true) {
            if (StringUtil.isBlank(getChainsById(id).getId())) {
                return id;
            }
            id = Utils.randomString();
        }
    }
}
