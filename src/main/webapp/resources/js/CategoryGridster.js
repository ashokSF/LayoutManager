
$ = jQuery;
var gridster;
var newHeight;
var newWidth;
$(function () {
    gridster = $("#gridster ul").gridster({
        widget_margins: [2, 2],
        widget_base_dimensions: [140, 80],
        avoid_overlapped_widgets: true,
        helper: 'clone',
        max_cols: 7,
        max_rows: 6,
        shift_larger_widgets_down: false,
        serialize_params: function ($w, wgd) {
            return {
                id: $($w).attr('id'),
                col: wgd.col,
                row: wgd.row,
                size_x: wgd.size_x,
                size_y: wgd.size_y
            };
        },
        resize: {
            enabled: true,
            stop: function (e, ui, $widget) {
                newHeight = $("#gridster ul").outerHeight();
                newWidth = $("#gridster ul").outerWidth();
            }
        },
        draggable: {
            stop: function (e, ui, $widget) {
                newHeight = $("#gridster ul").outerHeight();
                newWidth = $("#gridster ul").outerWidth();
            }
        }
    }).data('gridster');

    //var hidden = document.getElementById("gridsterForm:serializedData");
    try {
        var serialization = JSON.parse($('#serializedData').val());
    } catch (e) {
        serialization = null;
    }

    if (serialization != null) {
        //serialization = Gridster.sort_by_row_and_col(serialization);
        gridster.remove_all_widgets();
        $.each(serialization, function () {
            var html = '<li id="' + this.id + '">' + this.id + '<button class="delete-button" style="float: right;">Delete</button></li>'
            gridster.add_widget(html, this.size_x, this.size_y, this.col, this.row);
        });
    }

});


$(document).on("click", "#gridster .delete-button",
        function () {
            var gridster = $("#gridster ul").gridster().data('gridster');
            gridster.remove_widget($(this).parent());
            return false;
        }
);

function showPreviewDialog() {

    event.preventDefault();

    var prGridster = $("#gridster").clone();
    prGridster.attr("id", "prGridster");

    newHeight = $("#gridster ul").outerHeight();
    newWidth = $("#gridster ul").outerWidth();

    prGridster.dialog({
        width: (newWidth + 30),
        height: (newHeight + 70),
        helper: 'clone',
        modal: false,
        resizable: false,
        title: "Layout preview",
        draggable: false,
        close: function (event, ui) {
            $(this).dialog("destroy");
        }
    });
}


$(function () {

    $('#gridsterForm\\:preview').click(function (event) {

        event.preventDefault();

        var prGridster = $("#gridster").clone();
        prGridster.attr("id", "prGridster");

        prGridster.dialog({
            width: (newWidth + 30),
            height: (newHeight + 130),
            helper: 'clone',
            modal: false,
            resizable: false,
            title: "Layout preview",
            draggable: false,
            buttons: [
                {
                    text: "Save",
                    width: 100,
                    icons: {
                        primary: "ui-icon-disk"
                    },
                    click: function () {
                        var js = gridster.serialize();


                        var hidden = document.getElementById("gridsterForm:serializedData");


                        hidden.value = JSON.stringify(js);

                        $(this).dialog("destroy");
                    }
                }
            ],
            close: function (event, ui) {
                $(this).dialog("destroy");
            }
        });
    }); //close click

}
);
function handleDrop(event, ui) {
    var droppedCat = ui.draggable.text(),
            helper = ui.helper,
            position = ui.position,
            offset = ui.offset;

    var html = '<li id="' + droppedCat + '">' + droppedCat + '<button class="delete-button" style="float: right;">Delete</button></li>'

    gridster.add_widget(html, 1, 1);

    newHeight = $("#gridster").outerHeight();
    newWidth = $("#gridster").outerWidth();

}

function serializeGridster() {

    var js = gridster.serialize();

    //var hidden = document.getElementById("serializedData");

    //alert(JSON.stringify(js.to));

    $('#serializedData').val(JSON.stringify(js));
    $('#layoutLength').val(newHeight.toString());
    $('#layoutWidth').val(newWidth.toString());
}



