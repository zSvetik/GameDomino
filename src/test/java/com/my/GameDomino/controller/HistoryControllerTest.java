package com.my.GameDomino.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.my.GameDomino.dao.DominoDAO;
import com.my.GameDomino.entity.ChainDomino;
import com.my.GameDomino.entity.ChainDominoHistory;

public class HistoryControllerTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @Mock
    DominoDAO dominoDao;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGetMaxNullTest() throws Exception {
        // session.setAttribute("current_set", null);

        when(session.getAttribute("current_set")).thenReturn(null);
        when(request.getParameter("type")).thenReturn("max");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("Result.jsp")).thenReturn(rd);

        new HistoryController().doGet(request, response);

        verify(request).setAttribute("message", "");

        verify(rd).forward(request, response);
    }

    @Test
    public void doGetNotMaxNullTest() throws Exception {
        when(session.getAttribute("current_set")).thenReturn(null);
        when(request.getParameter("type")).thenReturn("min");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("Result.jsp")).thenReturn(rd);

        new HistoryController().doGet(request, response);

        verify(request).setAttribute("message", "");

        verify(rd).forward(request, response);
    }

    @Test
    public void doGetNotMaxNotNullTest() throws Exception {
        ChainDomino chainDomino = new ChainDomino("1", "123",
                Arrays.asList(new ChainDominoHistory("1", "1"), new ChainDominoHistory("12", "12")));

        when(session.getAttribute("current_set")).thenReturn(chainDomino);
        when(request.getParameter("type")).thenReturn("min");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("Result.jsp")).thenReturn(rd);

        new HistoryController().doGet(request, response);

        verify(request).setAttribute("message", chainDomino.getListChainDominoHistory().toString());

        verify(rd).forward(request, response);
    }
}
