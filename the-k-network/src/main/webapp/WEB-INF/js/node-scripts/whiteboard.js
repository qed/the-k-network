
function AppendActivity(activity) {
    var obj = jQuery.parseJSON(activity);
    if (obj.type == "Pixel") {
        DrawPoints(activity);
    }
    else if (obj.type == "Chat") {
        $(messageBoard).append("<br />" + obj.user + " : " + obj.data);
    }
    else if (obj.type == "System") {
        $(messageBoard).append("<br />" + obj.type + " : " + obj.user + " " + obj.data);
    }
    else if (obj.type = "Clear") {
        clearCanvas();
    }
    else;
}

function InitializeWhiteBoard(messageBox,messageBoard,drawingCanvas,drawingDiv,info,modeImage,clearLink) {
    SetDrawingColor();
    $(drawingDiv).draw();
    $(drawingCanvas).bind("draw", function (event, xy) {
        $(info).html("");
        //alert(xy);
        sendPixelMessage(xy);
    });

    var currentIndex = 0;

    $(messageBox).keypress(function (event) {
        if (event.keyCode == '13') {
            return sendChatMessage(messageBox)
        }
    });

    $(drawingCanvas).bind("drawmultiple", function (event, xy) {
        $(info).html("");
        currentIndex = 0;

        //alert(pixels[currentIndex]);
        sendPixelMessage(pixels[currentIndex]);
    });
    $(drawingCanvas).bind("message", function (event, data) {
        //alert("From server: " + data);
        $(info).html("");
        DrawPoints(data);
        if (currentIndex < pixels.length - 1) {
            currentIndex++;
            sendPixelMessage(pixels[currentIndex]);
        }
        else {
            currentIndex = 0;
            pixels = new Array();
        }
    });
    $(info).bind("message", function (event, data) {
        var obj = jQuery.parseJSON(data);
        if (obj.data == "") {
            $(info).html("");
        }
        else {
            $(info).html(obj.user + " " + obj.data);
        }
    });
    $(messageBox).focusin(function () {
        sendInfoMessage("is typing some text...");
    });
    $(messageBox).focusout(function () {
        sendInfoMessage("");
    });


    $(messageBox).bind("message", function (event, data) {
        var obj = jQuery.parseJSON(data);
        //alert(data);
        if (obj.type == "Chat") {
            $(messageBoard).append("<br />" + obj.user + " : " + obj.data);
            $(info).html("");
        }
        else if (obj.type == "System") {
            $(messageBoard).append("<br />" + obj.type + " : " + obj.user + " " + obj.data);
            $(info).html("");
        }
        else if (obj.type == "Clear") {
            clearCanvas();
        }

    });

    $(modeImage).click(function () {
        if ($(this).attr("src") == "Images/pencil.jpg") {
            $(this).attr("src", "Images/eraser.gif");
            $(this).attr("title", "Click to select Pencil");
            setEraser();
        }
        else {
            $(this).attr("src", "Images/pencil.jpg");
            $(this).attr("title", "Click to select Eraser");
            setInk();
        }

    });

    $(clearLink).click(function () {
        sendInfoMessage("cleared the whiteboard.");
        clearCanvas();
        sendClearMessage();
    });
}