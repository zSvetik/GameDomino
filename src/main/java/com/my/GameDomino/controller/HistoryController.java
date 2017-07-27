package com.my.GameDomino.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.my.GameDomino.dao.DominoDAO;
import com.my.GameDomino.dao.DominoDAOImpl;
import com.my.GameDomino.entity.ChainDomino;

@WebServlet("/showResult")
public class HistoryController extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private DominoDAO dominoDAO;
    private ChainDomino chainDomino;

    public HistoryController() {
        dominoDAO = new DominoDAOImpl();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        chainDomino = (ChainDomino) request.getAttribute("current_set");
        String message = "";
        if ("max".equalsIgnoreCase(request.getParameter("type"))) {
            message = chainDomino.getMaxChain().toString();
        } else {
            message = chainDomino.getListChainDominoHistory().toString();
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("Result.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("listChain", dominoDAO.getAllChains());
        request.getRequestDispatcher("Result.jsp").forward(request, response);
    }
}
