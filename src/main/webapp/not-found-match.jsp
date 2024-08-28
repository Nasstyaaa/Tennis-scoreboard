<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Not found</title>
    <style>
        body {
            background-color: darkslategray;
        }

        section {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 60%;
            text-align: center;

            border: 3px solid lightgrey;
            color: ghostwhite;
            background-color: rgba(151, 81, 74, 0.97);
            font-style: italic;
            font-weight: bold;
            font-size: 160%;
        }

        a {
            color: rgba(94, 48, 43, 0.97);
        }

        span {
            color: rgba(200, 128, 121, 0.97);
        }
    </style>
</head>
  <body>
    <section>
        <h1>ERROR</h1>
        <div>Match <span>${uuid}</span> not found or already played</div>
        <div>try searching for it in <a href="completed-matches.jsp">completed matches</a></div>
        <div>or create a <a href="new-match.jsp">new match</a></div>
    </section>
  </body>
</html>
