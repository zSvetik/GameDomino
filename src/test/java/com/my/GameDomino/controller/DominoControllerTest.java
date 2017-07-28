package com.my.GameDomino.controller;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.my.GameDomino.utils.Utils;

public class DominoControllerTest {

    @Mock
    HttpServletRequest request;
    @Mock
    HttpServletResponse response;
    @Mock
    HttpSession session;

    @Mock
    RequestDispatcher rd;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void doGetSetEmptyTest() throws Exception {
        String count = "";
        when(request.getParameter("type")).thenReturn("set");
        when(request.getParameter("count")).thenReturn(count);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("Create.jsp")).thenReturn(rd);

        new DominoController().doGet(request, response);

        verify(session).setAttribute("current_set", null);
        verify(request).setAttribute("message", "Введите количество доминошек!");

        verify(rd).forward(request, response);
    }

    @Test
    public void doGetSetNotEmptyTest() throws Exception {
        String count = "4";
        when(request.getParameter("type")).thenReturn("set");
        when(request.getParameter("count")).thenReturn(count);
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("Create.jsp")).thenReturn(rd);

        new DominoController().doGet(request, response);

        verify(session).setAttribute("current_set", Utils.getUsedDomino());
        verify(request).setAttribute("message", "Набор из " + count + " доминошек: " + Utils.getUsedDomino());

        verify(rd).forward(request, response);
    }

    @Test
    public void doGetNotSetTest() throws Exception {
        when(request.getParameter("type")).thenReturn("");
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("Create.jsp")).thenReturn(rd);

        new DominoController().doGet(request, response);

        verify(session).setAttribute("current_set", Utils.getUsedDomino());
        verify(request).setAttribute("message", "Набор доминошек: " + Utils.getUsedDomino());

        verify(rd).forward(request, response);
    }

    @Test
    public void doPostTest() throws Exception {
        when(request.getSession()).thenReturn(session);
        when(request.getRequestDispatcher("Result.jsp")).thenReturn(rd);

        new DominoController().doPost(request, response);

        verify(rd).forward(request, response);
    }

}
