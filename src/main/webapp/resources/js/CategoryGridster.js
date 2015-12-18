/* 
 *  Copyright (C) 2013 Alex Chavez <alex@alexchavez.net>
 *  California State University Long Beach (CSULB) ALL RIGHTS RESERVED
 * 
 *  Use of this software is authorized for CSULB students in Dr. Monge's classes, so long
 *  as this copyright notice remains intact. Students must request permission from Dr. Monge
 *  if the code is to be used in other venues outside of Dr. Monge's classes.
 * 
 *  This program is distributed to CSULB students in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.
 * 
 */

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


});


$(document).on("click", "#gridster .delete-button",
        function () {
            var gridster = $("#gridster ul").gridster().data('gridster');
            gridster.remove_widget($(this).parent());
            return false;
        }
);



$(function () {

    $('#gridsterForm\\:previewTab\\:preview').click(function (event) {

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
                        $(this).dialog("destroy");
                    }
                }
            ],
            close: function (event, ui) {
                $(this).dialog("destroy");
            }
        });
    }); //close click

});


function handleDrop(event, ui) {
    var droppedCar = ui.draggable.text(),
            helper = ui.helper,
            position = ui.position,
            offset = ui.offset;

    var html = '<li>' + droppedCar + '<button class="delete-button" style="float: right;">Delete</button></li>'

    gridster.add_widget(html, 1, 1);

    newHeight = $("#gridster ul").outerHeight();
    newWidth = $("#gridster ul").outerWidth();

}




