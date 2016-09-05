<c:set var="ctx" value="${pageContext.request.contextPath}"/>
<script>
    function connectToDatabase(database) {
        $.get('${ctx}/server/connect/' + database, function() {
            window.location.hash = '/database/menu';
        });
    };
</script>

<div id="databases">
    <h1>Connect to database</h1>
    <p>Select database for connect</p>

    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
                    <button onclick="connectToDatabase('{{= $data}}')">{{= $data}}</button>
                </td>
            </tr>
        </script>
    </table>
    <table>
        <td>
            <button id="connect-database-back">Back to menu</button>
        </td>
    </table>
</div>

