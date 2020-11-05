package com.example.web;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.example.model.*;

public class productServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private productJdbc prJdbc;

    public productServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
    	prJdbc = new productJdbc();
        String barcode = request.getParameter("barcode");
        String name = request.getParameter("name");
        String colour = request.getParameter("colour");
        String description = request.getParameter("description");

        product pr = new product();
        pr.setBarcode(barcode);
        pr.setName(name);
        pr.setColour(colour);
        pr.setDescription(description);
        pr.setResult(0);
        
        
        try {
            int res = prJdbc.registerProduct(pr);
            pr.setResult(res);

    		
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        request.setAttribute("styles", pr);
		RequestDispatcher view = request.getRequestDispatcher("result.jsp");
		view.forward(request, response);
    }

}
