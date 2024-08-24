<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>New match</title>
    <style>
        body {
            background-image: url("${pageContext.request.contextPath}img/background_new_match.png");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        input{
            background: none;
            border: 2px solid ghostwhite;
            color: ghostwhite;
            font-weight: bold;
            font-style: italic;
            padding: 5%;
            height: 10%;
            margin-bottom: 5%;
            font-size: 150%;
        }

        input:hover {
            color: darksalmon;
        }

        #player1 {
            position: fixed;
            top: 35%;
            left: 30%;
            transform: translate(-50%, -50%);
        }

        #player2 {
            position: fixed;
            top: 45%;
            left: 70%;
            transform: translate(-50%, -50%);
        }

        #play {
            position: fixed;
            top: 80%;
            left: 45%;
            transform: translate(-50%, -50%);
        }
    </style>
</head>
    <body>
        <form method="POST" action="/new-match">
            <p id="player1"><input type="text" placeholder="Player 1 name" name="player1"><br></p>
            <p id="player2"><input type="text" placeholder="Player 2 name" name="player2"></p>
            <p id="play"><input type="submit" value="Start the game"></p>
        </form>
    </body>
</html>
