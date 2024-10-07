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
import java.util.Collections;

@WebServlet("/numberCheck")
public class SequenceCheck extends HttpServlet {

    private DigitSpanUtil digitSpanUtil;

    @Override
    public void init() throws ServletException {
        digitSpanUtil = new DigitSpanUtil();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();

        @SuppressWarnings("unchecked")
        ArrayList<Integer> randomizedSequence = (ArrayList<Integer>) session.getAttribute("randomizedSequence");
        String indicatorValue = (String) session.getAttribute("indicatorValue");

        int currentIndicatorValue = (indicatorValue != null) ? Integer.parseInt(indicatorValue) : 1;


        String userInput = request.getParameter("userInput");

        ArrayList<Integer> userSequence = new ArrayList<>();

        for (char c : userInput.toCharArray()) {
            userSequence.add(Character.getNumericValue(c));
        }

        ArrayList<Character> characterArrayList = new ArrayList<>();
        ArrayList<Integer> integerArrayList = new ArrayList<>();

        for (int i = 0; i < userInput.length(); ++i) {
            char c = userInput.charAt(i);
            if (Character.isLetter(c)) {
                characterArrayList.add(c);
                Collections.sort(characterArrayList);
            } else if (Character.isDigit(c)) {
                integerArrayList.add(Character.getNumericValue(c));
                Collections.sort(integerArrayList);
            }
        }

        ArrayList<Object> userLetterNumberSequence = new ArrayList<>(integerArrayList);
        userLetterNumberSequence.addAll(characterArrayList);


        boolean sequenceMatched = false;

        String mode = request.getParameter("mode");

        switch (mode) {
            case "Forwards":
                sequenceMatched = DigitSpanUtil.checkForwardsSequence(userSequence, randomizedSequence);
                break;
            case "Backwards":
                sequenceMatched = DigitSpanUtil.checkBackwardsSequence(userSequence, randomizedSequence);
                break;
            case "Sequencing":
                sequenceMatched = DigitSpanUtil.checkSequencing(userSequence, randomizedSequence);
                break;
            case "LetterNumberSequencing":
                @SuppressWarnings("unchecked")
                ArrayList<Object> storedRandomizedSequence = (ArrayList<Object>) request.getSession().getAttribute("randomizedSequence");
                sequenceMatched = DigitSpanUtil.checkLetterNumberSequencing(userLetterNumberSequence, storedRandomizedSequence);
                break;
            default:
                break;
        }



        if (sequenceMatched) {
            indicatorValue = digitSpanUtil.getNextIndicatorValue(currentIndicatorValue);
            randomizedSequence = digitSpanUtil.getNextRandomizedSequence(currentIndicatorValue);
        } else {
            indicatorValue = digitSpanUtil.getResetIndicatorValue();
            randomizedSequence = digitSpanUtil.getResetRandomizedSequence();
        }

        session.setAttribute("mode", mode);
        session.setAttribute("indicatorValue", indicatorValue);
        session.setAttribute("randomizedSequence", randomizedSequence);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("digit-span-page.jsp");
        requestDispatcher.forward(request, response);
    }
}