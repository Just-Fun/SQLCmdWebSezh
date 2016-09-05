<script>
    function dropTable(tableName) {
        window.location.hash = '/database/drop/table/' + tableName;
    }
</script>

<div id="drop-table-list">
    <h1>Drop table</h1>
    <p>Select table for drop</p>

    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
                    <button onclick="dropTable('{{= $data}}')">{{= $data}}</button>
                </td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="drop-table-back">Back to menu</button>
            </td>
        </tr>
        <tr>
            <td>
                <span id="drop-table-message"></span>
            </td>
        </tr>
    </table>
</div>

<div id="drop-table-confirm">
    <h1>Confirm</h1>
    <p>You really want drop table?</p>
    <table>
        <tr>
            <td>
                <button id="drop-table-yes">Yes</button>
            </td>
            <td>
                <button id="drop-table-no">No</button>
            </td>
        </tr>
    </table>
</div>


