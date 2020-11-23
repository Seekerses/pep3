<%--
  Created by IntelliJ IDEA.
  User: seeke
  Date: 10.11.2020
  Time: 2:40
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Popov D.M. Lab01 P3213</title>
    <link rel="stylesheet" type="text/css" href="style.css">
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/script.js"></script>
</head>

<body>
<header>
    ${ (!"dot".equals(pageContext.request.getParameter("RequestType")) ? pageContext.session.removeAttribute("Results") : "") }
    <table class="studInfo">
        <tr>
            <td>Popov Daniil Michailovich</td>
            <td>Group P3213</td>
            <td>Variant 2419</td>
        </tr>
    </table>
</header>
<main>
    <table>
        <tr>
            <td>
                <canvas width="400" height="400" id="graph"></canvas>
            </td>
        </tr>
        <tr>
            <td>
                <table>
                    <tr>
                        <td>
                            <form name="data" onsubmit="return false">
                                <table class="contentTable">
                                    <tr>
                                        <td>
                                            Insert X:
                                            <input type="text" id="checkX" name="X" placeholder="Input X here...">
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Insert Y:
                                            <select id="checkY" name="Y">
                                                <option>-5</option>
                                                <option>-4</option>
                                                <option>-3</option>
                                                <option>-2</option>
                                                <option>-1</option>
                                                <option>0</option>
                                                <option>1</option>
                                                <option>2</option>
                                                <option>3</option>
                                            </select>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            Insert R:
                                            <input type="radio" id="checkR_1" name="R" value="1"> <label for="checkR_1">1</label>
                                            <input type="radio" id="checkR_2" name="R" value="2"> <label for="checkR_2">2</label>
                                            <input type="radio" id="checkR_3" name="R" value="3"> <label for="checkR_3">3</label>
                                            <input type="radio" id="checkR_4" name="R" value="4"> <label for="checkR_4">4</label>
                                            <input type="radio" id="checkR_5" name="R" value="5"> <label for="checkR_5">5</label>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td>
                                            <input type ="submit" onclick="validate()">
                                            <input type="submit" id="clear" value="Очистить" onclick="clearTable()">
                                        </td>
                                    </tr>
                                </table>
                            </form>
                        </td>
                        <td>
                            <table class="contentTable" id="res">
                                <tbody>
                                <tr>
                                    <td>X</td>
                                    <td>Y</td>
                                    <td>R</td>
                                    <td>Result</td>
                                </tr>
                                </tbody>
                            </table>
                        </td>
                    </tr>
                </table>
            </td>
        </tr>
    </table>
</main>
</body>
</html>
