package com.my.GameDomino.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import org.eclipse.jetty.util.StringUtil;

import com.my.GameDomino.config.Configuration;
import com.my.GameDomino.entity.*;
import com.my.GameDomino.utils.Utils;

public class DominoDAOImpl implements DominoDAO {

    private Connection connection;

    public DominoDAOImpl() {
        connection = Configuration.getInstance().getConnection();
    }

    @Override
    public void finalize() {
        try {
            if (connection != null) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
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
    	try (Statement statement = connection.createStatement()) {
    		connection.setAutoCommit(false);
    		createInsert(statement, chainDomino);
    		statement.executeBatch();
    		connection.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        } finally {
        	try {
				connection.setAutoCommit(true);
			} catch (SQLException e) {
				e.printStackTrace();
			}        	
        }
        return true;
    }

    @Override
    public ChainDomino getChainById(String id) {
        ChainDomino result = new ChainDomino();
        String query = "SELECT dc.id_chain, dc.chain, dch.id_chainhistory, dch.chainhistory \n" + "FROM DominoChain AS dc \n"
                + "INNER JOIN DominoChainHistory AS dch ON dc.id_chain = dch.id_chain \n" + "AND dc.id_chain = ?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.setString(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ChainDominoHistory chainDominoHistory = new ChainDominoHistory();
                    chainDominoHistory.setId(resultSet.getString("id_chainhistory"));
                    chainDominoHistory.setStringChainHistory(resultSet.getString("chainhistory"));

                    result.setId(resultSet.getString("id_chain"));
                    result.setStringChainDomino(resultSet.getString("chain"));
                    result.getListChainDominoHistory().add(chainDominoHistory);
                }
            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    @Override
    public List<ChainDomino> getAllChains() {
        List<ChainDomino> result = new ArrayList<>();
        String query = "SELECT dc.id_chain, dc.chain, dch.id_chainhistory, dch.chainhistory \n" + "FROM DominoChain AS dc \n"
                + "INNER JOIN DominoChainHistory AS dch ON dc.id_chain = dch.id_chain ";
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    ChainDominoHistory chainDominoHistory = new ChainDominoHistory();
                    chainDominoHistory.setId(resultSet.getString("id_chainhistory"));
                    chainDominoHistory.setStringChainHistory(resultSet.getString("chainhistory"));

                    ChainDomino chainDomino = new ChainDomino();
                    chainDomino.setId(resultSet.getString("id_chain"));
                    chainDomino.setStringChainDomino(resultSet.getString("chain"));
                    chainDomino.getListChainDominoHistory().add(chainDominoHistory);
                    result.add(chainDomino);
                }

            } catch (SQLException e) {
                throw new SQLException(e);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void createInsert(Statement statement, ChainDomino chainDomino) throws SQLException {
        String id = chainDomino.getId();
        if (StringUtil.isBlank(id)) {
            id = makeId();
        }
        int idChainDominoHistory = 0;
        statement.addBatch(String.format("INSERT INTO DominoChain (id_chain, chain) values ('%1$s', '%2$s')", id,
                chainDomino.getStringChainDomino()));

        for (ChainDominoHistory chainDominoHistory : chainDomino.getListChainDominoHistory()) {
        	statement.addBatch(String.format(
                        "INSERT INTO `DominoChainHistory` (id_chain, id_chainhistory, chainhistory) values ('%1$s', '%2$d', '%3$s')", id,
                        idChainDominoHistory++, chainDominoHistory.getStringChainHistory()));
        }
    }

    private String makeId() {
        String id = Utils.randomString();
        while (true) {
            if (StringUtil.isBlank(getChainById(id).getId())) {
                return id;
            }
            id = Utils.randomString();
        }
    }

    @Override
    public boolean deleteChainsById(String id) {
        String query = String.format("DELETE FROM DominoChain AS dc \n" + "WHERE dc.id_chain = '%1$s'; \n"
                + "DELETE FROM DominoChainHistory AS dch \n" + "WHERE dch.id_chain = '%1$s'", id);
        try (PreparedStatement preparedStatement = connection.prepareStatement(query)) {
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

        return true;
    }
}
