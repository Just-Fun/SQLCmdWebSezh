function init(ctx) {

    var username;
    var databaseName;
    var tableName;
    var isDrop = false;
    var isClear = false;

    function hideAllScreens() {
        $('#login-page').hide();
        $('#server-menu').hide();
        $('#databases').hide();
        $('#create-database-form').hide();
        $('#drop-database-list').hide();
        $('#drop-database-confirm').hide();
        $('#actions').hide();
        $('#database-menu').hide();
        $('#clear-table-list').hide();
        $('#clear-table-confirm').hide();
        $('#create-entry-list').hide();
        $('#create-entry-form').hide();
        $('#create-table-name').hide();
        $('#create-table-columns').hide();
        $('#drop-table-list').hide();
        $('#drop-table-confirm').hide();
        $('#table-list').hide();
        $('#table-view').hide();
        $('#update-record-list').hide();
        $('#update-record-form').hide();
    }

    var loadPage = function (data) {
        hideAllScreens();
        $('#loading').show();

        var page = data[0];
        if (page == 'server') {
            sqlServer(data);
        } else if (page == 'database') {
            database(data);
        } else if (page == 'login') {
            initLogin();
        } else {
            window.location.hash = '/login';
        }
    };

    var sqlServer = function (data) {
        var page = data[1];
        if (page == 'menu') {
            initServerMenu();
        } else if (page == 'databases') {
            initDatabases();
        } else if (page == 'create') {
            if (data[2] == 'database') {
                initCreateDatabase();
            }
        } else if (page == 'drop') {
            if (data[2] == 'database') {
                if (data[3] != null) {
                    databaseName = data[3];
                    initDropDatabaseConfirm();
                } else {
                    initDropDatabase();
                }
            }
        } else if (page == 'actions') {
            initActions(data[2]);
        }
    };
    
    var database = function (data) {
        var page = data[1];
        if (page == 'menu') {
            initDatabaseMenu();
        } else if (page == 'clear') {
            if (data[2] == 'table') {
                if (data[3] != null) {
                    tableName = data[3];
                    initClearTableConfirm();
                } else {
                    initClearTable();
                }
            }
        } else if (page == 'create') {
            if (data[2] == 'entry') {
                if (data[3] != null) {
                    initCreateEntryForm();
                } else {
                    initCreateEntry();
                }
            } else if (data[2] == 'table') {
                if (data[3] == 'columns') {
                    initCreateTableColumns();
                } else {
                    initCreateTable();
                }
            }
        } else if (page == 'drop') {
            if (data[2] == 'table') {
                if (data[3] != null) {
                    tableName = data[3];
                    initDropTableConfirm();
                } else {
                    initDropTable();
                }
            }
        } else if (page == 'table') {
            if (data[2] != null) {
                initTableView();
            } else {
                initTableList();
            }
        } else if (page == 'update') {
            if (data[2] == 'record') {
                if (data[3] != null) {
                    initUpdateRecordForm();
                } else {
                    initUpdateRecord()
                }
            }
        }
    };
    
    var load = function () {
        var hash = window.location.hash.substring(1);
        var elements = hash.split('/');
        if (elements[0] == '') {
            elements.shift();
        }
        loadPage(elements);
    };

    var show = function(selector) {
        var component = $(selector);
        component.find('.container').children().not(':first').remove();
        component.show();
    };

    $(window).bind('hashchange', function (event) {
        load();
    });

    function toServerMenu() {
        window.location.hash = '/server/menu';
    }
    
    function toDatabaseMenu() {
        window.location.hash = '/database/menu';
    }

    var initLogin = function () {
        $('#username').val("");
        $('#password').val("");
        $('#error').hide();
        $('#error').html("");
        $('#loading').hide(300, function () {
            $('#login-page').show();
        });
    };

    $('#login-button').click(function () {
        username = $('#username').val();

        var connection = {};
        connection.username = $('#username').val();
        connection.password = $('#password').val();
        
        $.ajax({
            url: ctx + '/login',
            data: connection,
            type: 'POST',
            success: function (message) {
                if (message == '' || message == null) {
                    window.location.hash = '/server/menu';
                } else {
                    $('#login-error').html(message);
                    $('#login-error').show();
                }
            }
        });
    });

    var initServerMenu = function () {
        $('#loading').hide(300, function () {
            $('#server-menu').show();
        });
    };

    $('#connect-database').click(function () {
        window.location.hash = '/server/databases';
    });
    
    $('#create-database').click(function () {
        window.location.hash = '/server/create/database';
    });

    $('#drop-database').click(function () {
        window.location.hash = '/server/drop/database';
    });

    $('#actions-for-user').click(function () {
        window.location.hash = '/server/actions/' + username;
    });

    $('#logout').click(function () {
        $.get(ctx + '/logout', function () {
            username = null;
            window.location.hash = '/login';
        });
    });

    var initDatabases = function initDatabases() {
        show('#databases');

        $.get(ctx + '/databases/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#databases script').tmpl(elements).appendTo('#databases .container');
            });
        });
    };

    $('#connect-database-back').click(function () {
       toServerMenu();
    });

    var initCreateDatabase = function () {
        $('#database-name').val('');
        $('#create-database-message').hide();
        $('#create-database-message').html('');
        $('#loading').hide(300, function () {
            $('#create-database-form').show();
        });
    };

    $('#create-database-button').click(function () {
        var database = $('#database-name').val();

        $.post(ctx + '/create/database/' + database, function (message) {
            if (message == '' || message == null) {
                $('#create-database-message').html('Database "' + database + '" successful created!');
                $('#create-database-message').show();
            } else {
                $('#create-database-message').html(message);
                $('#create-database-message').show();
            }
        });
    });

    $('#create-database-back').click(function () {
        toServerMenu();
    });
    
    var initDropDatabase = function () {
        if (!isDrop) {
            $('#drop-database-message').hide();
            $('#drop-database-message').html('');
        }
        isDrop = false;
        
        show('#drop-database-list');       
        $.get(ctx + '/databases/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#drop-database-list script').tmpl(elements).appendTo('#drop-database-list .container');
            });
        });
    };

    $('#drop-database-back').click(function () {
        toServerMenu();
    });

    var initDropDatabaseConfirm = function () {
        $('#loading').hide(300, function () {
            $('#drop-database-confirm').show();
        });
    };

    $('#drop-database-yes').click(function () {
        $.get(ctx + '/server/drop/database/' + databaseName, function () {
            window.location.hash = '/server/drop/database';
            isDrop = true;
            $('#drop-database-message').html('Database "' + databaseName + '" successful drop!');
            $('#drop-database-message').show();
            databaseName = null;
        });
    });

    $('#drop-database-no').click(function () {
        window.location.hash = '/server/drop/database';
    });

    var initActions = function (username) {
        show('#actions');
        $.get(ctx + '/actions/' + username + '/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#actions script[template="row"]').tmpl(elements).appendTo('#actions .container');
            });
        });
    };

    $('#actions-back').click(function () {
        toServerMenu();
    });
    
    var initDatabaseMenu = function () {
        $('#loading').hide(300, function () {
            $('#database-menu').show();
        });
    };

    $('#clear-table').click(function () {
        window.location.hash = '/database/clear/table';
    });

    $('#create-entry').click(function () {
        window.location.hash = '/database/create/entry';
    });

    $('#create-table').click(function () {
        window.location.hash = '/database/create/table';
    });

    $('#drop-table').click(function () {
        window.location.hash = '/database/drop/table';
    });

    $('#table').click(function () {
        window.location.hash = '/database/table';
    });

    $('#update-record').click(function () {
        window.location.hash = '/database/update/record';
    });

    $('#disconnect').click(function () {
        $.get(ctx + '/database/disconnect');
        toServerMenu();
    });

    var initClearTable = function () {
        if (!isClear) {
            $('#clear-table-message').hide();
            $('#clear-table-message').html('');
        }
        isClear = false;

        show('#clear-table-list');
        $.get(ctx + '/tables/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#clear-table-list script').tmpl(elements).appendTo('#clear-table-list .container');
            });
        });
    };

    $('#clear-table-back').click(function () {
        toDatabaseMenu()
    });

    var initClearTableConfirm = function () {
        $('#loading').hide(300, function () {
            $('#clear-table-confirm').show();
        });
    };

    $('#clear-table-yes').click(function () {
        $.get(ctx + '/database/clear/table/' + tableName, function () {
            window.location.hash = '/database/clear/table';
            isClear = true;
            $('#clear-table-message').html('Table "' + tableName + '" successful cleared!');
            $('#clear-table-message').show();
            tableName = null;
        });
    });

    $('#clear-table-no').click(function () {
        window.location.hash = '/database/clear/table';
    });

    var initCreateEntry = function () {
        show('#create-entry-list');
        $.get(ctx + '/tables/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#create-entry-list script').tmpl(elements).appendTo('#create-entry-list .container');
            });
        });
    };

    $('#create-entry-back').click(function () {
        toDatabaseMenu();
    });

    var initCreateEntryForm = function () {
        $('#create-entry-message').hide();
        $('#create-entry-message').html('');

        var url = window.location.hash;
        var parts = url.split('/');
        var tableName = parts[parts.length - 1];

        show('#create-entry-form');
        $.get(ctx + '/table/'+ tableName +'/columns/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#create-entry-form script').tmpl(elements).appendTo('#create-entry-form .container');
            });
        });
    };

    $('#create-entry-create').click(function () {
        var values = $('.entry-values').map(function () {
            return this.value;
        }).get();

        var url = window.location.hash;
        var parts = url.split('/');
        var tableName = parts[parts.length - 1];
        $.ajax({
            url: ctx + '/database/create/entry/' + tableName,
            type: 'POST',
            data: {'values': values},
            success: function (message) {
                if (message == '' || message == null) {
                    $('#create-entry-message').html('Entry successful created!');
                    $('#create-entry-message').show();
                } else {
                    $('#create-entry-message').html(message);
                    $('#create-entry-message').show();
                }
            }
        });
    });

    $('#create-entry-form-back').click(function () {
        window.location.hash = '/database/create/entry';
    });

    var initCreateTable = function () {
        $('#table-name').val('');
        $('#number-columns').val('');

        $('#loading').hide(300, function() {
            show('#create-table-name');
        });
    };

    $('#create-table-next').click(function () {
        window.location.hash = '/database/create/table/columns'
    });

    $('#create-table-back').click(function () {
        toDatabaseMenu();
    });

    var initCreateTableColumns = function () {
        var columns = $('#number-columns').val();
        show('#create-table-columns');
        $('#loading').hide(300, function() {
            for (var i = 0; i < columns; i++) {
                $('#create-table-columns script').tmpl().appendTo('#create-table-columns .container');
            }
        });
    };

    $('#create-table-create').click(function () {
        var columnNames = $('.column-names').map(function () {
            return this.value;
        }).get();
        var columnTypes = $('.column-types').map(function () {
            return this.value;
        }).get();
        
        var query = $('#table-name').val() + ' (';
        for (var i = 0; i < columnNames.length; i++) {
            if (i != columnNames.length - 1) {
                query += columnNames[i] + ' ' + columnTypes[i] + ', ';
            } else {
                query += columnNames[i] + ' ' + columnTypes[i];
            }
        }
        query += ')';

        $.ajax({
            url: ctx + '/database/create/table',
            type: 'POST',
            data:  {'query': query},
            success: function (message) {
                if (message == '' || message == null) {
                    $('#create-table-message').html('Table successful created!');
                    $('#create-table-message').show();
                } else {
                    $('#create-table-message').html(message);
                    $('#create-table-message').show();
                }
            }
        })
    });

    $('#create-table-columns-back').click(function () {
        window.location.hash = '/database/create/table';
    });

    var initDropTable = function () {
        if (!isDrop) {
            $('#drop-table-message').hide();
            $('#drop-table-message').html('');
        }
        isDrop = false;

        show('#drop-table-list');
        $.get(ctx + '/tables/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#drop-table-list script').tmpl(elements).appendTo('#drop-table-list .container');
            });
        });
    };

    $('#drop-table-back').click(function () {
        toDatabaseMenu();
    });

    var initDropTableConfirm = function () {
        $('#loading').hide(300, function () {
            $('#drop-table-confirm').show();
        });
    };

    $('#drop-table-yes').click(function () {
        $.get(ctx + '/database/drop/table/' + tableName, function () {
            window.location.hash = '/database/drop/table';
            isDrop = true;
            $('#drop-table-message').html('Table "' + tableName + '" successful drop!');
            $('#drop-table-message').show();
            tableName = null;
        });
    });

    $('#drop-table-no').click(function () {
        window.location.hash = '/database/drop/table';
    });

    var initTableList = function () {
        show('#table-list');
        $.get(ctx + '/tables/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#table-list script').tmpl(elements).appendTo('#table-list .container');
            });
        });
    };
    
    $('#table-list-back').click(function () {
        toDatabaseMenu();
    });

    var initTableView = function () {
        var url = window.location.hash;
        var parts = url.split('/');
        var tableName = parts[parts.length - 1];

        show('#table-view');
        $.get(ctx + '/database/table/' + tableName + '/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#table-view script[template="row"]').tmpl(elements).appendTo('#table-view .container');
            });
        });
    };

    $('#table-view-back').click(function () {
        window.location.hash = '/database/table';
    });

    var initUpdateRecord = function () {
        show('#update-record-list');
        $.get(ctx + '/tables/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#update-record-list script').tmpl(elements).appendTo('#update-record-list .container');
            });
        });
    };

    $('#update-list-back').click(function () {
        toDatabaseMenu();
    });

    var initUpdateRecordForm = function () {
        $('#update-record-message').hide();
        $('#update-record-message').html('');

        var url = window.location.hash;
        var parts = url.split('/');
        var tableName = parts[parts.length - 1];

        show('#update-record-form');
        $.get(ctx + '/table/'+ tableName +'/columns/content', function(elements) {
            $('#loading').hide(300, function() {
                $('#update-record-form script').tmpl(elements).appendTo('#update-record-form .container');
            });
        });
    };

    $('#update-record-update').click(function () {
        var values = $('.update-values').map(function () {
            return this.value;
        }).get();

        var url = window.location.hash;
        var parts = url.split('/');
        var tableName = parts[parts.length - 1];
        $.ajax({
            url: ctx + '/database/update/record/' + tableName,
            type: 'POST',
            data: {'values': values},
            success: function (message) {
                if (message == '' || message == null) {
                    $('#update-record-message').html('Record successful updated!');
                    $('#update-record-message').show();
                } else {
                    $('#update-record-message').html(message);
                    $('#update-record-message').show();
                }
            }
        });
    });

    $('#update-record-form-back').click(function () {
        window.location.hash = '/database/update/record';
    });

    load();
}