<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 5/4/2019
  Time: 11:09 AM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>

<head>
    <meta name="layout" content="dsdlayout"/>
    <title>Tovar detail, ID: ${tovar?.id}</title>


</head>

<body>


<h1>
    Tovar detail, ID: ${tovar?.id}
</h1>
<g:form controller="tovar" action="doEdit" params='[id: "${tovar?.id}"]'>
<table>
    <tbody>
        <tr>
            <td>
                ID
            </td>
            <td>
                ${tovar?.id}
            </td>
        </tr>
        <tr>
            <td>
                Nazov
            </td>
            <td>
                <g:textField name="nazov" value="${tovar?.nazov}"/>
            </td>
        </tr>
        <tr>
            <td>
                Vyrobca
            </td>
            <td>
                <g:textField name="vyrobca" value="${tovar?.vyrobca}"/>
            </td>
        </tr>
        <tr>
            <td>
                Popis
            </td>
            <td>
                <g:textField name="popis" value="${tovar?.popis}"/>
            </td>
        </tr>
        <tr>
            <td>
                Farba
            </td>
            <td>
                <g:textField name="farba" value="${tovar?.farba}"/>
            </td>
        </tr>
        <tr>
            <td>
                Cena
            </td>
            <td>
                <g:textField name="cena" value="${tovar?.cena}"/>
            </td>
        </tr>
        <tr>
            <td>
                Kod
            </td>
            <td>
                <g:textField name="kod" value="${tovar?.kod}"/>
            </td>
            <td>
                last local updare
            </td>
            <td>
                ${tovar?.lastLocalUpdate}
            </td>
        </tr>
    </tbody>
</table>
    <br>
    <button type="submit">Save</button>
</g:form>

<br>
<br>

</body>
</html>