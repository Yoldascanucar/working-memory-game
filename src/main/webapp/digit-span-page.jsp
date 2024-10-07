    <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%@ page import="java.util.ArrayList" %>
    <%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


    <html lang="en" xmlns:c="http://java.sun.com/jsp/jstl/core" xmlns:form="http://www.w3.org/1999/html">
    <head>
        <meta charset="UTF-8">
        <title>Digit Span Game</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="preconnect" href="https://fonts.googleapis.com">
        <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
        <link href="https://fonts.googleapis.com/css2?family=Afacad+Flux:wght@100..1000&family=Montserrat:ital,wght@0,100..900;1,100..900&display=swap" rel="stylesheet">

        <style>
            body {
              font-family: Arial, sans-serif;
              display: flex;
              justify-content: center;
              align-items: center;
              height: 100vh;
              margin: 0;
              background-color: #f0f0f0;
          }

          .container {
               background-color: #dec5c5;
               padding: 20px;
               border-radius: 10px;
          }


          .action-group {
               display: flex;
               align-items: center;
               justify-content: space-between;
               margin-top: 40px;
          }

          .indicator {
               display: flex;
               border-radius: 10px;
          }

          .indic {
               width: 45px;
               height: 45px;
               margin-right: 10px;
               border-radius: 10px;
               border-style: double;
               display: flex;
               align-items: center;
               justify-content: center;
               padding: 0;
               box-sizing: border-box;
          }


          .start-btn, .reset-btn {
               font-family: "Montserrat", sans-serif;
               font-size: 18px;
               color: white;
               border-radius: 10px;
               border-style: none;
               padding: 5px 9px;
               box-sizing: border-box;
          }



          .start-btn {
               background-color: #2ba7f0;
          }

          .reset-btn {

               background-color: red;
               padding: 7px 11px;
               box-sizing: border-box;
          }

          .user .check-btn {
               font-family: "Montserrat", sans-serif;
               width: 70px;
               padding-top: 7px;
               padding-bottom: 7px;
               font-size: 18px;
               border-radius: 10px;
               background-color: #5cdb35;
               border-style: none;
               color: white;
               margin-left: 15px;
          }

            .check-btn:hover {
               background-color: #4cbd2a;
               transition: background-color 0.4s ease;
           }


            .user {
              display: flex;
              margin-top: 20px;
            }

            input{
              font-size: 20px;
              height: 40px;
              width: 400px;
              border-radius: 10px;
              border-style: none;
              padding: 10px;
              box-sizing: border-box;
              text-align: center;
            }



           .number-display {
              font-size: 22px;
              display: flex;
              justify-content: center;
              align-items: center;
              background-color: white;
              border-radius: 10px;
              height: 40px;
            }

            .number-display .number {
              position: absolute;
              font-size: 25px;
              font-weight: 200;
              opacity: 0;
              animation: showNumber 1s linear forwards;
            }

            @keyframes showNumber {
                0%, 100% { opacity: 0; }
                10%, 90% { opacity: 1; }
            }

            <c:forEach items="${sessionScope.randomizedSequence}" var="number" varStatus="status">
                .number-display .number:nth-child(${status.index + 1}) {
                    animation-delay: ${status.index}s;
                }
            </c:forEach>

            .number-display.hide-all .number {
                animation: none;
                opacity: 0;
            }


            h1 {
              font-family: "Montserrat", sans-serif;
              font-optical-sizing: auto;
              font-weight: 300;
              font-style: normal;
              text-align: center;
              margin-bottom:50px;
            }

            .action-group .form-select {
               font-family: "Montserrat", sans-serif;
               font-size: 15px;
               text-align:center;
               border-radius: 5px;
               padding: 2px 2px 2px 2px;
               margin-left: 70px;
               margin-top: 8px;
               width: 120px;
               height: 30px;
               font-weight: 500;
            }

            input::placeholder {
                font-family: "Montserrat", sans-serif;
            }



        </style>
    </head>
    <body>

    <%

        if (session.getAttribute("indicatorValue") == null) {
             session.setAttribute("indicatorValue", "1");
        }

        ArrayList<Integer> randomizedSequence = (ArrayList<Integer>) session.getAttribute("randomizedSequence");
        ObjectMapper objMapper = new ObjectMapper();
        String jsonData = objMapper.writeValueAsString(randomizedSequence);
        String indicatorValue = String.valueOf(session.getAttribute("indicatorValue"));
        String mode = String.valueOf(session.getAttribute("mode"));
    %>

        <div class="container">
            <h1>Digit Span</h1>
            <div class="number-display">
                <c:forEach items="${sessionScope.randomizedSequence}" var="number">
                    <div class="number"> ${number}</div>
                </c:forEach>
            </div>

            <form class="user" action="numberCheck" method="post">
                <input class="userInput" type="text" name="userInput" placeholder="Enter the Sequence" oninput="convertToUpperCase(this)" required disabled>
                <input type="hidden" name="mode" value="${param.mode}">
                <button class="check-btn" type="submit">Check</button>
            </form>

            <div class="action-group">
                <form class="indicator" action="sequenceGeneration" method="get">
                    <input class="indic" type="number" name="indicatorValue" value="${empty param.indicatorValue ? sessionScope.indicatorValue : param.indicatorValue}" min="1" max="100" style="font-size: 20px;">
                    <button class="start-btn" type="submit">Start</button>
                    <select name="mode" id="mode" class="form-select">
                        <option value="Forwards" ${empty param.mode ? (sessionScope.mode == 'Forwards' ? 'selected' : '') : (param.mode == 'Forwards' ? 'selected' : '')}>Forwards</option>
                        <option value="Backwards" ${empty param.mode ? (sessionScope.mode == 'Backwards' ? 'selected' : '') : (param.mode == 'Backwards' ? 'selected' : '')}>Backwards</option>
                        <option value="Sequencing" ${empty param.mode ? (sessionScope.mode == 'Sequencing' ? 'selected' : '') : (param.mode == 'Sequencing' ? 'selected' : '')}>Sequencing</option>
                        <option value="LetterNumberSequencing" ${empty param.mode ? (sessionScope.mode == 'LetterNumberSequencing' ? 'selected' : '') : (param.mode == 'LetterNumberSequencing' ? 'selected' : '')}>Letter Number Sequencing</option>
                    </select>
                </form>

                <form action="resetIndicatorValue" method="get">
                    <button class="reset-btn" type="submit">Reset</button>
                </form>
            </div>

        </div>

        <script>
            var randomizedSequence = <%= jsonData %>;
            var displayElement = document.querySelector(".number-display");
            var userInput = document.querySelector(".userInput");
            var checkButton = document.querySelector(".check-btn");
            var startButton = document.querySelector(".start-btn");

            setTimeout(function() {
                displayElement.classList.add("hide-all");
                userInput.disabled = false;
                checkButton.disabled = false;
            }, (randomizedSequence.length + 1) * 1000);
        </script>
        <script>
            function convertToUpperCase(input) {
                input.value = input.value.toUpperCase();
            }
        </script>
    </body>
    </html>
