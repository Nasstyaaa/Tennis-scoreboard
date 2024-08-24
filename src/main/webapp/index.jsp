<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <style>

        #welcome {
            position: fixed;
            top: 45%;
            left: 52%;
            transform: translate(-50%, -50%);
            backdrop-filter: blur(2px);
        }

        body {
            background-image: url("${pageContext.request.contextPath}img/background_index.jpg");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
            color: ghostwhite;
        }

        h1 {
            font-weight: bold;
            font-style: italic;
            font-size: 1000%;
            margin: 2% auto;
        }

        button {
            font-weight: bold;
            font-style: italic;
            border: none;
            font-size: 200%;
            margin-left: 5%;
            background: none;
            color: ghostwhite;
        }
        button:hover {
            color: darkgreen;
        }

    </style>
</head>
<body>
<form id="welcome" method="GET">
    <h1>Welcome</h1>
    <button formaction="/new-match">new match</button>
    <button formaction="/">completed matches</button>
</form>
</body>
</html>
