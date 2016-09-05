<script>
    function dropDatabase(databaseName) {
        window.location.hash = '/server/drop/database/' + databaseName;
    }
</script>

<div id="drop-database-list">
    <h1>Drop database</h1>
    <p>Select database for drop</p>

    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
                    <button onclick="dropDatabase('{{= $data}}')">{{= $data}}</button>
                </td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="drop-database-back">Back to menu</button>
            </td>
        </tr>
        <tr>
            <td>
                <span id="drop-database-message"></span>
            </td>
        </tr>
    </table>
</div>

<div id="drop-database-confirm">
    <h1>Confirm</h1>
    <p>You really want drop database?</p>
    <table>
        <tr>
            <td>
                <button id="drop-database-yes">Yes</button>
            </td>
            <td>
                <button id="drop-database-no">No</button>
            </td>
        </tr>
    </table>
</div>

