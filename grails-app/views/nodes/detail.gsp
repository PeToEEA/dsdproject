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
    Node detail, ID: ${node?.id}
</h1>
<g:form controller="node" action="doEdit" params='[id: "${node?.id}"]'>
<table>
    <tbody>
        <tr>
            <td>
                ID
            </td>
            <td>
                ${node?.id}
            </td>
        </tr>
        <tr>
            <td>
                IP adresa
            </td>
            <td>
                <g:textField name="ipAdress" value="${node?.ipAdress}"/>
            </td>
        </tr>
        <tr>
            <td>
                Posledny DB update
            </td>
            <td>
                ${node?.lastDatabaseUpdate}
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