<%--
  Created by IntelliJ IDEA.
  User: peter
  Date: 5/4/2019
  Time: 12:07 PM
--%>

<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <meta name="layout" content="dsdlayout"/>
    <title>Nodes</title>
</head>

<body>
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
            IP
        </td>
        <td>
            Last DB update
        </td>
        <td></td>
        <td></td>

    </tr>
    </thead>
    <tbody>
    <g:each in="${nodes}" var="node">
        <tr>
            <td>
                ${node.id}
            </td>
            <td>
                ${node.name}
            </td>
            <td>
                ${node.url}
            </td>
            <td>
                ${node.lastDatabaseUpdate}
            </td>
            <td>
                <g:link controller="nodes" action="detail" params='[id: "${node.id}"]'>DETAIL</g:link>
            </td>
            <td>
                <g:link controller="nodes" action="delete" params='[id: "${node.id}"]'>DELETE</g:link>
            </td>
        </tr>
    </g:each>
    </tbody>
</table>



</body>
</html>