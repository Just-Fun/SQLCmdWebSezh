<script>
    function table(tableName) {
        window.location.hash = '/database/table/' + tableName;
    }
</script>

<div id="table-list">
    <h1>Tables</h1>
    <p>Select table for view</p>
    <table class="container">
        <script type="text/x-jquery-tmpl">
            <tr>
                <td>
                    <button onclick="table('{{= $data}}')">{{= $data}}</button>
                </td>
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="table-list-back">Back to menu</button>
            </td>
        </tr>
    </table>
</div>

<div id="table-view">
    <h1>Table</h1>
    <table border="1" class="container">
        <script template="row" type="text/x-jquery-tmpl">
            <tr>
                {{each $data}}
                    <td>
                        {{= this}}
                    </td>
                {{/each}}
            </tr>
        </script>
    </table>
    <table>
        <tr>
            <td>
                <button id="table-view-back">Back to table list</button>
            </td>
        </tr>
    </table>
</div>

