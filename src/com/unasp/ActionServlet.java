package com.unasp;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.BookDAO;
import entity.Book;

@WebServlet("/moviefun/*")
public class ActionServlet extends HttpServlet {
        private static final long serialVersionUID = -5832176047021911038L;

        @EJB
        private BookDAO bookDAO ;

        @Override
        protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    		    Book b =  new Book("deitel",  "3", 10,  "Alta Vista",  "Java how to program");
				List<Book> lista  = null;
    		    try {
					bookDAO.addBook(b);
					lista= bookDAO.getBooks();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}	
    		    
    		    PrintWriter out = response.getWriter();
    		    for(Book c: lista)
    		    	out.println(c.getTitle());
        	 
        	 }
        	
        }

        
