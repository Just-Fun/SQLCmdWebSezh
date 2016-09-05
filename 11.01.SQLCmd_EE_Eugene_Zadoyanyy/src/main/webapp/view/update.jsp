<script>
    function updateRecord(tableName) {
        window.location.hash = '/database/update/record/' + tableName;
    }
</script>

<div id="update-record-list">
    <h1>Update record</h1>
    <p>Select table for update record</p>
    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
                    <button onclick="updateRecord('{{= $data}}')">{{= $data}}</button>
                </td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="update-list-back">Back to menu</button>
            </td>
        </tr>
    </table>
</div>

<div id="update-record-form">
    <h1>Update record</h1>

    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
				    <label>{{= $data}}:</label>
				</td>
				<td>
				    <input type="text" class="update-values" value="" placeholder="{{= $data}}">
				</td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="update-record-update">Update</button>
            </td>
            <td>
                <button id="update-record-form-back">Back</button>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>
                <span id="update-record-message"></span>
            </td>
        </tr>
    </table>
</div>
