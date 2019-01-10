package com.acme.jeedemo1.cliente;

import com.acme.jeedemo1.ejb.PersonEJB;
import com.google.gson.Gson;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Created by Ivan on 10/01/2019.
 */
public class GreeterServlet extends HttpServlet {

    private static final long serialVersionUID = -5126830463725015816L;

    @EJB
    private PersonEJB personEJB;

    @Override
    public void service(HttpServletRequest req, HttpServletResponse res) throws IOException, ServletException {
        final PrintWriter out = res.getWriter();

        Gson gson = new Gson();
        String personJson = gson.toJson(personEJB.getPersonById(1L));

        out.println(personJson);
    }
}
