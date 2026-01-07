var stompClient = null;
var roomId = null;
var username = null;
var targetCode = 'public class HelloWorld { public static void main(String[] args) { System.out.println("Hello"); } }';

document.addEventListener("DOMContentLoaded", function() {
    roomId = document.getElementById("roomId").value;
    username = document.getElementById("username").value;
    connect();

    document.getElementById("codeInput").addEventListener("input", function() {
        calculateProgress();
    });
});

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game/room/' + roomId, function (message) {
            handleMessage(JSON.parse(message.body));
        });
        
        // Notify Enter
        stompClient.send("/app/game/enter", {}, JSON.stringify({
            'roomId': roomId,
            'type': 'ENTER',
            'sender': username
        }));
    });
}

function handleMessage(msg) {
    if (msg.type === 'START') {
        document.getElementById("statusArea").innerText = "Status: PLAYING";
        document.getElementById("gameArea").style.display = "block";
        document.getElementById("codeInput").disabled = false;
        document.getElementById("codeInput").focus();
        document.getElementById("btnStart").style.display = "none";
    } else if (msg.type === 'PROGRESS') {
        if (msg.sender !== username) {
            document.getElementById("oppProgress").innerText = msg.content;
            document.getElementById("oppProgressBar").value = msg.content;
        }
    } else if (msg.type === 'COUNTDOWN') {
         document.getElementById("statusArea").innerText = "Status: FINISHED! Winner: " + msg.sender + ". Ending in " + msg.content + "s...";
         document.getElementById("codeInput").disabled = true;
    } else if (msg.type === 'END') {
         alert("Game Over! " + msg.content);
         window.location.href = "/game/lobby";
    }
}

function startGameRequest() {
    stompClient.send("/app/game/start", {}, JSON.stringify({
        'roomId': roomId,
        'type': 'START',
        'sender': username
    }));
}

function calculateProgress() {
    var input = document.getElementById("codeInput").value;
    var len = targetCode.length;
    var matchLen = 0;
    
    for(var i=0; i<input.length && i<len; i++) {
        if(input[i] === targetCode[i]) {
            matchLen++;
        } else {
            // Stop at first error or continue? Usually strict typing stops or marks red.
            // For simple %:
        }
    }
    
    // Simple Percentage: Length based
    var percent = Math.floor((matchLen / len) * 100);
    
    document.getElementById("myProgress").innerText = percent;
    document.getElementById("myProgressBar").value = percent;
    
    stompClient.send("/app/game/progress", {}, JSON.stringify({
        'roomId': roomId,
        'type': 'PROGRESS',
        'sender': username,
        'content': percent
    }));
}