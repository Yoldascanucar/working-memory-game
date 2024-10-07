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


@WebServlet("/sequenceGeneration")
public class SequenceGeneration extends HttpServlet {

    DigitSpanUtil digitSpanUtil;

    @Override
    public void init() throws ServletException {
        digitSpanUtil = new DigitSpanUtil();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        String indicatorValue = request.getParameter("indicatorValue");
        int length = Integer.parseInt(indicatorValue);
        String mode = request.getParameter("mode");

        if (session.isNew()) {
            indicatorValue = "1";
            mode = "Forwards";
        }

        if ("LetterNumberSequencing".equals(mode)) {
            ArrayList<Object> randomizedLetterNumberSequence = digitSpanUtil.letterNumberSequenceRandomizer(length);
            session.setAttribute("randomizedSequence", randomizedLetterNumberSequence);
        } else {
            ArrayList<Integer> randomizedSequence = digitSpanUtil.sequenceRandomizer(length);
            session.setAttribute("randomizedSequence", randomizedSequence);
        }

        session.setAttribute("mode", mode);
        session.setAttribute("indicatorValue", indicatorValue);

        RequestDispatcher dispatcher = request.getRequestDispatcher("digit-span-page.jsp");
        dispatcher.forward(request, response);
     }
}
