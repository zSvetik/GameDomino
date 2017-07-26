package com.my.GameDomino.controller;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import com.my.GameDomino.dao.DominoDAO;
import com.my.GameDomino.dao.DominoDAOImpl;

@WebServlet("/DominoController")
public class DominoController extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	private DominoDAO dominoDAO;
	
	public DominoController() {
		dominoDAO = new DominoDAOImpl();
	}

	/*
    public static final String lIST_STUDENT = "/listStudent.jsp";
    public static final String INSERT_OR_EDIT = "/student.jsp";
 
 
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "";
        String action = request.getParameter( "action" );
 
        if( action.equalsIgnoreCase( "delete" ) ) {
            forward = lIST_STUDENT;
            int studentId = Integer.parseInt( request.getParameter("studentId") );
            dao.deleteStudent(studentId);
            request.setAttribute("students", dao.getAllStudents() );
        }
        else if( action.equalsIgnoreCase( "edit" ) ) {
            forward = INSERT_OR_EDIT;
            int studentId = Integer.parseInt( request.getParameter("studentId") );
            Student student = dao.getStudentById(studentId);
            request.setAttribute("student", student);
        }
        else if( action.equalsIgnoreCase( "insert" ) ) {
            forward = INSERT_OR_EDIT;
        }
        else {
            forward = lIST_STUDENT;
            request.setAttribute("students", dao.getAllStudents() );
        }
        RequestDispatcher view = request.getRequestDispatcher( forward );
        view.forward(request, response);
    }
 
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Student student = new Student();
        student.setFirstName( request.getParameter( "firstName" ) );
        student.setLastName( request.getParameter( "lastName" ) );
        student.setCourse( request.getParameter( "course" ) );
        student.setYear( Integer.parseInt( request.getParameter( "year" ) ) );
        String studentId = request.getParameter("studentId");
 
        if( studentId == null || studentId.isEmpty() )
            dao.addStudent(student);
        else {
            student.setStudentId( Integer.parseInt(studentId) );
            dao.updateStudent(student);
        }
        RequestDispatcher view = request.getRequestDispatcher( lIST_STUDENT );
        request.setAttribute("students", dao.getAllStudents());
        view.forward(request, response);
    }
	 */
}
