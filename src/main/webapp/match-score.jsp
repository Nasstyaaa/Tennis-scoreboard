<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<html>
<head>
    <title>Match score</title>
    <style>
        body {
            background-image: url("${pageContext.request.contextPath}img/background_new_match.png");
            background-size: cover;
            background-repeat: no-repeat;
            background-attachment: fixed;
        }

        #winner{
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);

            border: 3px solid lightgrey;
            color: ghostwhite;
            background-color: rgba(151, 81, 74, 0.97);
            font-style: italic;
            font-weight: bold;
            font-size: 400%;
        }

        #tableScore {
            position: fixed;
            top: 50%;
            left: 50%;
            transform: translate(-50%, -50%);
            width: 90%;
            text-align: center;

            border: 3px solid lightgrey;
            color: ghostwhite;
            background-color: rgba(151, 81, 74, 0.97);
            font-style: italic;
            font-weight: bold;
            font-size: 160%;
        }

        #matchScore{
            display: flex;
            flex-direction: row;
            gap: 19%;
            margin-left: 10%;
        }

        #points, #games, #sets, #players {
            display: flex;
            flex-direction: column;
            gap: 10%;
        }

        #tieBreakInfo {
            margin-top: 10px;
            font-size: 160%;
        }

        #court {
            margin-bottom: 2%;
            padding: 5% 0;
            border: 5px solid lightgrey;
            font-size: 180%;
            font-style: italic;
            font-weight: bold;
        }

        button {
            margin-top: 30%;
            font-size: 100%;
            font-style: italic;
            font-weight: bold;
            background: none;
            color: ghostwhite;
            border: 2px solid darkgrey;
            background: rgba(185, 128, 121, 0.97);
        }
        button:hover {
            background: rgba(94, 48, 43, 0.97);
        }

        p {
            border: 2px solid darkgrey;
            padding: 5px;
            margin: 2px;
            background: rgba(94, 48, 43, 0.97);
        }
    </style>
</head>
    <body>
    <c:choose>
        <c:when test="${match.getWinner() == null}">
            <div>
                    <div id="tableScore">
                        <div id="court">COURT NO. ${uuid}</div>

                        <div id="matchScore">

                            <form id="players" method="POST" action="/match-score?uuid=${uuid}">
                                <div>PLAYER</div>
                                <button name="idPlayer" value="1">${match.getPlayer1().getName()}</button>
                                <button name="idPlayer" value="2">${match.getPlayer2().getName()}</button>
                            </form>

                            <div id="sets">
                                <div>SETS</div>
                                <p>${match.getMatchScore().getScorePlayer1().getSetsCount()}</p>
                                <p>${match.getMatchScore().getScorePlayer2().getSetsCount()}</p></div>

                            <div id="games">
                                <div>GAMES</div>
                                <p>${match.getMatchScore().getScorePlayer1().getGameCount()}</p>
                                <p>${match.getMatchScore().getScorePlayer2().getGameCount()}</p></div>

                            <div id="points">
                                <div>POINTS</div>
                                <c:choose>
                                    <c:when test="${match.getMatchScore().getScorePlayer1().isHasAdvantage()}">
                                        <p>A</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>${match.getMatchScore().getScorePlayer1().getPointCount()}</p>
                                    </c:otherwise>
                                </c:choose>

                                <c:choose>
                                    <c:when test="${match.getMatchScore().getScorePlayer2().isHasAdvantage()}">
                                        <p>A</p>
                                    </c:when>
                                    <c:otherwise>
                                        <p>${match.getMatchScore().getScorePlayer2().getPointCount()}</p>
                                    </c:otherwise>
                                </c:choose>

                                <div id="tieBreakInfo">
                                    <c:if test="${match.isTieBreak()}">
                                        <span style="color: ghostwhite; font-weight: bold;">Tai-break</span>
                                    </c:if>
                                </div>
                            </div>

                        </div>
                    </div>
            </div>
        </c:when>
        <c:otherwise>
            <p id="winner">WINNER: ${match.getWinner().getName()}</p>
        </c:otherwise>
    </c:choose>
    </body>
</html>
