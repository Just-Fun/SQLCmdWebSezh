<div id="actions">
    <h1>Actions</h1>
    <table border="1" class="container">
        <tr>
            <td>Username</td>
            <td>Actions</td>
            <td>Database</td>
            <td>Date</td>
        </tr>
        <script template="row" type="text/x-jquery-tmpl">
              <tr>
                <td>
                    {{= username}}
                </td>
                <td>
                    {{= action}}
                </td>
                <td>
                    {{= database}}
                </td>
                <td>
                    {{= date}}
                </td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="actions-back">Back to menu</button>
            </td>
        </tr>
    </table>
</div>
