<script>
    function clearTable(tableName) {
        window.location.hash = '/database/clear/table/' + tableName;
    }
</script>

<div id="clear-table-list">
    <h1>Clear table</h1>
    <p>Select table for clear</p>

    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
                    <button onclick="clearTable('{{= $data}}')">{{= $data}}</button>
                </td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="clear-table-back">Back to menu</button>
            </td>
        </tr>
        <tr>
            <td>
                <span id="clear-table-message"></span>
            </td>
        </tr>
    </table>
</div>

<div id="clear-table-confirm">
    <h1>Confirm</h1>
    <p>You really want clear table?</p>
    <table>
        <tr>
            <td>
                <button id="clear-table-yes">Yes</button>
            </td>
            <td>
                <button id="clear-table-no">No</button>
            </td>
        </tr>
    </table>
</div>