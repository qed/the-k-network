/*
* Jquery HitTest plugin V1.0
* Written by Ejder Yurdakul 03/01/2011
* http://freelancephp.be
* Description This plugin gives user ability to draw in a DOM element.
* params:
* options.color: color of the line
* options.thickness: width of thel ine 
* returns void
*/
//debug script

var pixels = new Array();

function SetDrawingColor() {
    $.fn.draw.defaults = {
        color: "#000000",
        thickness: 2.0
    };
}

function clearCanvas() {
	canvas=document.getElementById("drawing-canvus");
	c=canvas.getContext("2d");
	c.clearRect(0,0,canvas.width,canvas.height);
}

var isInk = 1;
function setInk() {
    isInk = 1;
    var canvas = document.getElementById("drawing-canvus");
    var context = canvas.getContext("2d");
    context.strokeStyle = "#000000";
    context.lineWidth = 1;
}

function setEraser() {
    isInk = 0;
    var canvas = document.getElementById("drawing-canvus");
    var context = canvas.getContext("2d");
    context.strokeStyle = "#ffffff";
    context.lineWidth = 20;
}

(function ($) {
    $.fn.draw = function (options) {
        
            var opts = $.extend({}, $.fn.draw.defaults, options);
            var position = this.offset();

            var canvas = document.getElementById("drawing-canvus");
            var context = canvas.getContext("2d");
            context.strokeStyle = opts.color;

            var startDraw = false;
            var count = 0;
            $("#drawing-canvus").mousedown(function () {
                
                if(isInk == 1)
                {
                    sendInfoMessage("is drawing something...");
                }
                else
                {
                    sendInfoMessage("is erasing something...");
                }

                startDraw = true;
                coOordinates = "" + isInk;

                context.beginPath();

            });

            //reset it on mouseup
            $("#drawing-canvus").mouseup(function () {

                startDraw = false;
                context.beginPath();
                
                if(coOordinates.length > 500)
                {
                    
                    splitPoints = coOordinates.split('#');
                    newCoordinates = "" + isInk;
                    for(i=1;i<splitPoints.length;i++)
                    {
                        newCoordinates += "#" +splitPoints[i];
                        if(newCoordinates.length > 50)
                        {
                            newCoordinates =  newCoordinates;

                            pixels.push(newCoordinates.toString());
                            newCoordinates = "" + isInk;
                            i-=2;
                        }
                    }
                    if(i >= splitPoints.length)
                    {
                        newCoordinates =  newCoordinates;

                        pixels.push(newCoordinates.toString());
                        newCoordinates = "" + isInk;
                    }
                    $("#drawing-canvus").trigger("drawmultiple", pixels);
                }
                else
                {
                    coOordinates =  coOordinates;
                    $("#drawing-canvus").trigger("draw", coOordinates);
                    coOordinates = "" + isInk;
                }
            });

            $("#drawing-canvus").mousemove(function (e) {

                if (e.target.getAttribute("id") == "drawing-canvus") {

                    if (startDraw) {

                        context.lineTo((e.clientX - position.left), (e.clientY - position.top));
                        coOordinates += "#" + (e.clientX - position.left) + "," + (e.clientY - position.top);
                        context.stroke();
                    }
                }
               
            });
        
    };
})(jQuery);



function DrawPoints(jsondata) {

    var obj = jQuery.parseJSON(jsondata);
    var user = obj.user;
    var type = obj.type;
    var data = obj.data;

    var canvas = document.getElementById("drawing-canvus");
    var context = canvas.getContext("2d");
    context.beginPath();

    var allxy = data.split('#');
    if(allxy.length > 1)
    {
        var ink = allxy[0];
        if(ink == "1")
        {
            setInk();
            $(this).attr("src", "Images/pencil.jpg");
            $(this).attr("title", "Click to select Eraser");
        }
        else
        {
            setEraser();
            $(this).attr("src", "Images/eraser.gif");
            $(this).attr("title", "Click to select Pencil");
        }
        for (i = 1; i < allxy.length; i++) {
        
            xy = allxy[i];
            if (xy != "") {
                DrawPoint(xy);
            }
        }
    }
}

function DrawPoint(data) {

    var canvas = document.getElementById("drawing-canvus");
    var context = canvas.getContext("2d");

    xy = data.split(",");
    x = xy[0];
    y = xy[1];

    context.lineTo((x), (y));
    context.stroke();

}