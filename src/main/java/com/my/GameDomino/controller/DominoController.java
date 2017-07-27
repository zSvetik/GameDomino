package com.my.GameDomino.controller;

import java.io.IOException;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.my.GameDomino.dao.*;
import com.my.GameDomino.entity.*;
import com.my.GameDomino.utils.*;

@WebServlet("/setDominoList")
public class DominoController extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private DominoDAO dominoDAO;
    ChainDomino chainDomino;

    
    public void init(ServletConfig config) throws ServletException {
    	dominoDAO = new DominoDAOImpl();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String message = "";
        Utils.deselectListDomino();
        if ("set".equalsIgnoreCase(request.getParameter("type"))) {
            if (!"".equalsIgnoreCase(request.getParameter("count"))) {
                int count = Integer.parseInt(request.getParameter("count"));
                chainDomino = Utils.selectedListDomino(Utils.getRandomCount(count));
                Utils.fillAllChain(chainDomino);
                message = "Набор из " + count + " доминошек: " + chainDomino;
            } else {
                message = "Введите количество доминошек!";
            }
        } else {
            chainDomino = Utils.selectedListDomino(Utils.getRandomCount(-1));
            Utils.fillAllChain(chainDomino);
            message = "Набор доминошек: " + chainDomino;
        }

        request.setAttribute("message", message);
        request.getRequestDispatcher("Create.jsp").forward(request, response);
    }

    @Override
    public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        if (chainDomino != null) {
            request.setAttribute("current_set", chainDomino);
        }

        dominoDAO.saveChains(chainDomino);
        request.getRequestDispatcher("Result.jsp").forward(request, response);
    }
}
