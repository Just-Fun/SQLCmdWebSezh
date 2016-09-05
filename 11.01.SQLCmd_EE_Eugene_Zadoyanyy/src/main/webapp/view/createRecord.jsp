<script>
    function createEntry(table) {
        window.location.hash = '/database/create/entry/' + table;
    }
</script>

<div id="create-entry-list">
    <h1>Clear entry</h1>
    <p>Select table for creating entry</p>

    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
                    <button onclick="createEntry('{{= $data}}')">{{= $data}}</button>
                </td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="create-entry-back">Back to menu</button>
            </td>
        </tr>
    </table>
</div>

<div id="create-entry-form">
    <h1>Create entry</h1>

    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
				    <label>{{= $data}}:</label>
				</td>
				<td>
				    <input type="text" class="entry-values" value="" placeholder="{{= $data}}">
				</td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="create-entry-create">Create</button>
            </td>
            <td>
                <button id="create-entry-form-back">Back</button>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>
                <span id="create-entry-message"></span>
            </td>
        </tr>
    </table>
</div>
