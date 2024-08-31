<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Exception</title>
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
    </style>
</head>
<body>
<section>
    <h1>ERROR</h1>
    <div>Something went wrong...</div>
    <div>Try again <a href="index.jsp">Home</a></div>
</section>
</body>
</html>
