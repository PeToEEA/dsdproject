<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 5/4/2019
  Time: 11:01 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta name="layout" content="dsdlayout"/>
    <title>Tovary</title>
</head>

<body>

<h1>Tovary</h1>

<br>

<g:if test="${deleted}">
    <span style="color: red">zaznam zmazany</span>
</g:if>

<table>
    <thead>
        <tr>
            <td>
                ID
            </td>
            <td>
                Nazov
            </td>
            <td>
                Vyrobca
            </td>
            <td>
                Popis
            </td>
            <td>
                Farba
            </td>
            <td>
                Cena
            </td>
            <td>
                Kod
            </td>
            <td>
                Last local update
            </td>
            <td></td>
            <td></td>

        </tr>
    </thead>
    <tbody>
    <g:each in="${tovary}" var="tovar">
        <tr>
            <td>
                ${tovar.id}
            </td>
            <td>
                ${tovar.nazov}
            </td>
            <td>
                ${tovar.vyrobca}
            </td>
            <td>
                ${tovar.popis}
            </td>
            <td>
                ${tovar.farba}
            </td>
            <td>
                ${tovar.cena}
            </td>
            <td>
                ${tovar.kod}
            </td>
            <td>
                ${tovar.lastLocalUpdate}
            </td>
            <td>
                <g:link controller="tovar" action="detail" params='[id: "${tovar.id}"]'>DETAIL</g:link>
            </td>
            <td>
                <g:link controller="tovar" action="delete" params='[id: "${tovar.id}"]'>DELETE</g:link>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>

</body>
</html>