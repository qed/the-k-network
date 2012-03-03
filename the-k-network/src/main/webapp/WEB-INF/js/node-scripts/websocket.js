var ws;
var noSupportMessage = "Sorry, Web socket not supported in your browser.";
var URI, PORT, MESSAGE_UPDATE_ELEMENT, MESSAGEBOX, INFOAREA;

function Connect(uri, port, messageUpdateElement, messageBox, infoArea) {
    URI = uri;
    PORT = port;
    MESSAGE_UPDATE_ELEMENT = messageUpdateElement;
    MESSAGEBOX = messageBox;
    INFOAREA = infoArea;

    //setup  websocket
    if (!("WebSocket" in window)) {
        alert(noSupportMessage);
        return;
    }
    var url = "ws://" + uri + ":" + port;

    ws = new WebSocket(url);

    if (messageUpdateElement != null) {
        // when data is comming from the server, this metod is called
        ws.onmessage = function (evt) {
            var obj = jQuery.parseJSON(evt.data);
            if (obj.type == "Pixel") {
                $(messageUpdateElement).trigger("message", evt.data);
            }
            else if (obj.type == "Chat" || obj.type == "System" || obj.type == "Clear") {
                $(messageBox).trigger("message", evt.data);
            }
            else if (obj.type == "Info") {
            
                $(infoArea).trigger("message", evt.data);
            }

        };
    }

    ws.onopen = function () {
        //alert("connected");
    };

    ws.onerror = function (error) {
        alert(error);
    };

    ws.onclose = function () {
        //alert("disconnected");
    };
}

function getJsonMessage(action, message) {
    var jsonMessage = '{"Action":"' + action + '","Message":"' + message + '"}';
    return jsonMessage;
}

function sendPixelMessage(message) {

    if (ws) {
        if (ws.readyState == 3) {
            connect(URI, PORT, MESSAGE_UPDATE_ELEMENT, MESSAGEBOX, INFOAREA);
        }
        var jsonMessage = getJsonMessage("Pixel", message);
        ws.send(jsonMessage);
    } else {
        alert(noSupportMessage);
    }
}

function sendChatMessage(textArea) {
    if (ws) {
        if (ws.readyState == 3) {
            connect(URI, PORT, MESSAGE_UPDATE_ELEMENT, MESSAGEBOX, INFOAREA);
        }
        var message = $(textArea).val();
        if (message == "") return false;
        $(textArea).val("");
        var jsonMessage = getJsonMessage("Chat", message);
        ws.send(jsonMessage);
    } else {
        alert(noSupportMessage);
    }
}

function sendInfoMessage(message) {
    if (ws) {
        if (ws.readyState == 3) {
            connect(URI, PORT, MESSAGE_UPDATE_ELEMENT, MESSAGEBOX, INFOAREA);
        }
        var jsonMessage = getJsonMessage("Info", message);
        ws.send(jsonMessage);
    } else {
        alert(noSupportMessage);
    }
}

function sendClearMessage() {
    if (ws) {
        if (ws.readyState == 3) {
            connect(URI, PORT, MESSAGE_UPDATE_ELEMENT, MESSAGEBOX, INFOAREA);
        }
        var jsonMessage = getJsonMessage("Clear", "");
        ws.send(jsonMessage);
    } else {
        alert(noSupportMessage);
    }
}