<%@ include file="../../standard-include.jspf" %>


<script type="text/javascript" src="/resources/js/node-scripts/jqdraw.js"></script>
<script type="text/javascript" src="/resources/js/node-scripts/websocket.js"></script>
<script type="text/javascript" src="/resources/js/node-scripts/whiteboard.js"></script>

<div class="container">
    <script type="text/javascript">
        
        $(document).ready(function () {
            Connect("http://theknetwork.org", "4000", "#drawing-canvus", "#messageBox","#info");
            InitializeWhiteBoard("#messageBox","#messageBoard","#drawing-canvus","#draw","#info","#imgMode","#clear");
            LoadPreviousActivity();
        }); 

        function LoadPreviousActivity()
        {
			AppendActivity('dude');
        }


    </script>
    <div style="float: left;">
        <div id="draw">
        <canvas width="700" height="500" id="drawing-canvus"></canvas>
        </div>
        
        <div style="margin-top:5px;">
            <span>Currently selected : <a id="ink" href="javascript:void(0)">
                <img id="imgMode" src="Images/pencil.jpg" title="Click to select Eraser" /></a></span> <span><a
                    id="clear" href="javascript:void(0)">Clear Board</a></span> <span id="info">
                    </span>
        </div>
    </div>
    <div id="messageArea">
        <div>
            <div id="conversation">Conversation</div>
            <div id="messageBoard"></div>
        </div>
        <div>
            <textarea id="messageBox"></textarea>
        </div>
        <div style="text-align: right">
            <input type="button" id="btnSend" value="Send" onclick="sendChatMessage('#messageBox')" />
        </div>
    </div>
</div>