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
<g:form controller="nodes" action="doEdit">
    <g:if test="${node?.id}">
        <g:hiddenField name="id" value="${node.id}"/>
    </g:if>
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
                Name
            </td>
            <td>
                <g:textField name="name" value="${node?.name}"/>
            </td>
        </tr>
        <tr>
            <td>
                IP adresa
            </td>
            <td>
                <g:textField name="url" value="${node?.url}"/>
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