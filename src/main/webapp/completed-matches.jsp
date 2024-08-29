<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>

<html>
<head>
    <title>Matches</title>
    <style>
        body {
            background-color: darkslategray;
        }

        table {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);

            border-collapse: collapse;
            width: 70%;
            border: 3px solid lightgrey;
            color: ghostwhite;
            background-color: rgba(151, 81, 74, 0.97);
            font-style: italic;
            font-weight: bold;
            font-size: 200%;
        }

        th, td {
            border: 1px solid #ddd;
            padding: 8px;
            text-align: center;
        }

        th {
            background-color: lightgrey;
            color: rgba(120, 48, 43, 0.97);
        }

        #winner {
            background-color: rgba(120, 48, 43, 0.97)
        }

    </style>
</head>
<body>
<section id="table">
    <table id="players-table">
        <thead>
            <tr>
                <th>№</th>
                <th>Player 1</th>
                <th>Player 2</th>
                <th>Winner</th>
            </tr>
        </thead>
        <tbody id="table-body">
            <c:forEach items="${matchList}" var="match">
                <tr>
                    <td>${match.id}</td>
                    <td>${match.player1.getName()}</td>
                    <td>${match.player2.getName()}</td>
                    <td id="winner">${match.winner.getName()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
</section>
</body>
</html>
