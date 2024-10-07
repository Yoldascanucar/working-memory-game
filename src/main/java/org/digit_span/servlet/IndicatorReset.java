package org.digit_span.servlet;

import org.digit_span.digit_span_util.DigitSpanUtil;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet("/resetIndicatorValue")
public class IndicatorReset extends HttpServlet {

    DigitSpanUtil digitSpanUtil;

    @Override
    public void init() throws ServletException {
        digitSpanUtil = new DigitSpanUtil();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession();
        String newIndicatorValue = "1";

        ArrayList<Integer> newRandomizedSequence = digitSpanUtil.sequenceRandomizer(Integer.parseInt(newIndicatorValue));
        session.setAttribute("indicatorValue", newIndicatorValue);
        session.setAttribute("randomizedSequence",newRandomizedSequence );

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("digit-span-page.jsp");
        requestDispatcher.forward(request, response);
    }
}
