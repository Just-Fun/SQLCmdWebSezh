<div id="create-table-name">
    <h1>Create table</h1>
    <table>
        <tr>
            <td>
                <label>Table name:</label>
            </td>
            <td>
                <input type="text" id="table-name" placeholder="Table name">
            </td>
        </tr>
        <tr>
            <td>
                <label>Number of columns</label>
            </td>
            <td>
                <input type="text" id="number-columns" placeholder="Number of columns">
            </td>
        </tr>
        <table>
            <tr>
                <td>
                    <button id="create-table-next">Next</button>
                </td>
                <td>
                    <button id="create-table-back">Back to menu</button>
                </td>
            </tr>
        </table>
    </table>
</div>

<div id="create-table-columns">
    <h1>Create table</h1>

    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
				    <input type="text" class="column-names" value="" placeholder="Column name">
				</td>
				<td>
				    <input type="text" class="column-types" value="" placeholder="Column type">
				</td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="create-table-create">Create</button>
            </td>
            <td>
                <button id="create-table-columns-back">Back</button>
            </td>
        </tr>
    </table>
    <table>
        <tr>
            <td>
                <span id="create-table-message"></span>
            </td>
        </tr>
    </table>
</div>

