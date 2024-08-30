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

        #input-form {
            position: fixed;
            top: 23%;
            left: 25%;
            transform: translate(-50%, -50%);
            width: 20%;

            display: flex;
            align-items: center;
        }

        #form {
            position: fixed;
            top: 23%;
            left: 45%;
            transform: translate(-50%, -50%);
            width: 20%;
        }

        input[type="text"] {
            width: 90%;
            height: 20%;
            font-size: 100%;
            padding: 10%;

            font-style: italic;
            background-color: lightgrey;
            color: rgba(120, 48, 43, 0.97);
            border: none;
            border-radius: 5px 0 0 5px;
        }

        #submit-input {
            width: 30%;
            height: 20%;
            font-size: 100%;
            padding: 10%;

            background-color: rgba(120, 48, 43, 0.97);
            color: white;
            border: none;
            cursor: pointer;
        }

        #submit-input:hover, #submit:hover{
            background-color: rgba(120, 48, 43, 0.85);
        }

        #submit {
            width: 30%;
            height: 20%;
            font-size: 100%;
            padding: 10%;

            background-color: rgba(120, 48, 43, 0.97);
            color: white;
            border: none;
            border-radius: 0 5px 5px 0;
            cursor: pointer;
        }

        a {
            color: ghostwhite;
            position: fixed;
            top: 10%;
            left: 100%;
            transform: translate(-50%, -50%);
            width: 20%;
            font-style: italic;
            font-size: 150%;
        }

        .pagination {
            position: fixed;
            top: 90%;
            left: 50%;
            transform: translate(-50%, -50%);
            display: flex;
            justify-content: space-between;
            width: 70%;
        }

        .pagination-button {
            background-color: rgba(120, 48, 43, 0.97);
            color: white;
            border: none;
            padding: 10px 15px;
            font-size: 150%;
            cursor: pointer;
        }

        .pagination-button:hover {
            background-color: rgba(120, 48, 43, 0.85);
        }

        .pagination-info {
            color: ghostwhite;
            font-size: 120%;
            margin: 0 20px;
        }
    </style>
</head>
<body>
<section id="table">

    <form action="/matches?filter_by_player_name=${namePlayer}" method="GET" id="input-form">
        <input type="hidden" name="page" value="${page_number}">
        <input type="text" placeholder="Player's name" name="filter_by_player_name" required>
        <input type="submit" value="Find" id="submit-input">
    </form>

    <form action="/matches" method="GET" id="form">
        <input type="hidden" name="page" value="1">
        <input type="submit" value="Reset" id="submit">
    </form>

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

    <div class="pagination">
        <form action="/matches?filter_by_player_name=${namePlayer}" method="GET" id="prevForm">
            <input type="hidden" name="page" value="${page_number - 1}">

            <c:if test="${filter_by_player_name != null && !param.filter_by_player_name.isEmpty()}">
                <input type="hidden" name="filter_by_player_name" value="${namePlayer}">
            </c:if>

            <button form="prevForm" class="pagination-button" ${page_number == 1 ? 'disabled' : ''}>&#8592;</button>
        </form>

        <span class="pagination-info">Page ${page_number != null ? page_number : 1  } of ${total_pages}</span>

        <form action="/matches?filter_by_player_name=${namePlayer}" method="GET" id="nextForm">
            <input type="hidden" name="page" value="${page_number + 1}">

            <c:if test="${filter_by_player_name != null && !param.filter_by_player_name.isEmpty()}">
                <input type="hidden" name="filter_by_player_name" value="${namePlayer}">
            </c:if>

            <button form="nextForm" class="pagination-button" ${page_number == total_pages ? 'disabled' : ''}>&#8594;</button>
        </form>
    </div>

    <a href="index.jsp">Home</a>
</section>
</body>
</html>
