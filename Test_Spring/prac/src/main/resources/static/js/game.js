var stompClient = null;
var roomId = null;
var username = null;
var currentLineIndex = 0;
var isGameRunning = false;

var codeLines = [
    "public class HelloWorld {",
    "    public static void main(String[] args) {",
    "        System.out.println(\"Hello, World!\");",
    "        for (int i = 0; i < 5; i++) {",
    "            System.out.println(\"Count: \" + i);",
    "        }",
    "    }",
    "}"
];

document.addEventListener("DOMContentLoaded", function() {
    roomId = document.getElementById("roomId").value;
    username = document.getElementById("username").value;
    
    // Initial render (disabled state)
    renderCode();
    connect();
});

function connect() {
    var socket = new SockJS('/ws');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/game/room/' + roomId, function (message) {
            handleMessage(JSON.parse(message.body));
        });
        
        stompClient.send("/app/game/enter", {}, JSON.stringify({
            'roomId': roomId,
            'type': 'ENTER',
            'sender': username
        }));
    });
}

function handleMessage(msg) {
    if (msg.type === 'START') {
        startGame();
    } else if (msg.type === 'PROGRESS') {
        if (msg.sender !== username) {
            document.getElementById("oppProgress").innerText = msg.content;
            document.getElementById("oppProgressBar").value = msg.content;
        }
    } else if (msg.type === 'COUNTDOWN') {
         // Optionally show countdown overlay
    } else if (msg.type === 'END') {
         alert("Game Over! " + msg.content); // Simple alert for now
         isGameRunning = false;
         document.getElementById("startOverlay").style.display = "flex";
         document.getElementById("statusArea").innerText = "Winner: " + msg.content;
         document.getElementById("btnStart").innerText = "Play Again";
    } else if (msg.type === 'ERROR') {
        alert("Error: " + msg.content);
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

function startGame() {
    isGameRunning = true;
    currentLineIndex = 0;
    document.getElementById("startOverlay").style.display = "none";
    
    // Reset UI
    renderCode();
    focusCurrentLine();
}

function renderCode() {
    var gameArea = document.getElementById("gameArea");
    var lineNumbers = document.getElementById("lineNumbers");
    
    gameArea.innerHTML = "";
    lineNumbers.innerHTML = "";
    
    codeLines.forEach(function(lineText, index) {
        // 1. Line Number
        var numDiv = document.createElement("div");
        numDiv.innerText = (index + 1);
        lineNumbers.appendChild(numDiv);
        
        // 2. Code Line Container
        var lineDiv = document.createElement("div");
        lineDiv.className = "code-line";
        lineDiv.id = "line-" + index;
        
        // 3. Reference Text (Ghost)
        var refDiv = document.createElement("div");
        refDiv.className = "code-ref";
        refDiv.innerHTML = syntaxHighlight(lineText);
        lineDiv.appendChild(refDiv);
        
        // 4. Input Container
        var inputContainer = document.createElement("div");
        inputContainer.className = "input-line-container";
        
        var input = document.createElement("input");
        input.type = "text";
        input.className = "code-input";
        input.id = "input-" + index;
        input.setAttribute("autocomplete", "off");
        input.setAttribute("spellcheck", "false");
        
        // Event Listeners
        input.addEventListener("keydown", function(e) {
            handleKeyDown(e, index);
        });
        
        input.addEventListener("input", function(e) {
            validateInputRealtime(e, index);
        });
        
        inputContainer.appendChild(input);
        lineDiv.appendChild(inputContainer);
        
        gameArea.appendChild(lineDiv);
    });
    
    updateActiveLine();
}

function updateActiveLine() {
    // Remove active class from all
    var allLines = document.querySelectorAll(".code-line");
    allLines.forEach(l => l.classList.remove("active"));
    
    if (currentLineIndex < codeLines.length) {
        var activeLine = document.getElementById("line-" + currentLineIndex);
        if(activeLine) {
            activeLine.classList.add("active");
            // Scroll into view if needed
            activeLine.scrollIntoView({behavior: "smooth", block: "center"});
        }
    }
}

function focusCurrentLine() {
    if(!isGameRunning) return;
    var input = document.getElementById("input-" + currentLineIndex);
    if(input) {
        input.focus();
    }
}

function handleKeyDown(e, index) {
    if(e.key === 'Enter') {
        e.preventDefault();
        checkLine(index);
    } else if (e.key === 'Tab') {
        e.preventDefault();
        var input = e.target;
        var start = input.selectionStart;
        var end = input.selectionEnd;

        // Insert 4 spaces
        input.value = input.value.substring(0, start) + "    " + input.value.substring(end);

        // Move cursor
        input.selectionStart = input.selectionEnd = start + 4;
        
        // Trigger validation
        var event = new Event('input', {
            bubbles: true,
            cancelable: true,
        });
        input.dispatchEvent(event);
    }
}

function validateInputRealtime(e, index) {
    var input = e.target;
    var currentVal = input.value;
    var targetVal = codeLines[index];
    
    // Check if the current typed prefix matches the target
    // We can be strict:
    if(!targetVal.startsWith(currentVal)) {
        input.classList.add("error");
    } else {
        input.classList.remove("error");
    }
}

function checkLine(index) {
    var input = document.getElementById("input-" + index);
    var val = input.value;
    var target = codeLines[index];
    
    // Trim both for fairness? Or require exact whitespace?
    // "IDE style" usually indents for you, but here we are typing.
    // Let's require exact match including spaces for "typing practice" feel.
    // However, if the user pressed TAB, browsers might switch focus. 
    // We should handle TAB to insert spaces.
    
    if(val === target) {
        // Success
        markLineComplete(index);
        currentLineIndex++;
        
        calculateProgress();
        
        if(currentLineIndex >= codeLines.length) {
            finishGame();
        } else {
            updateActiveLine();
            focusCurrentLine();
        }
    } else {
        // Error
        input.classList.add("error");
        // Shake animation could be added here
    }
}

function markLineComplete(index) {
    var line = document.getElementById("line-" + index);
    line.classList.add("completed");
    line.classList.remove("active");
    var input = document.getElementById("input-" + index);
    input.disabled = true;
}

function calculateProgress() {
    // Progress based on lines completed
    var percent = Math.floor((currentLineIndex / codeLines.length) * 100);
    
    document.getElementById("myProgress").innerText = percent;
    document.getElementById("myProgressBar").value = percent;
    
    stompClient.send("/app/game/progress", {}, JSON.stringify({
        'roomId': roomId,
        'type': 'PROGRESS',
        'sender': username,
        'content': percent
    }));
}

function finishGame() {
    isGameRunning = false;
    stompClient.send("/app/game/end", {}, JSON.stringify({
        'roomId': roomId,
        'type': 'END',
        'sender': username,
        'content': "FINISHED"
    }));
}

// Simple Syntax Highlighter
function syntaxHighlight(code) {
    // Very basic regex replacement for demo
    // Order matters
    var html = code
        .replace(/&/g, "&amp;").replace(/</g, "&lt;").replace(/>/g, "&gt;") // Escape HTML
        .replace(/(".*?")/g, '<span class="str">$1</span>') // Strings
        .replace(/\b(public|class|static|void|int|for|if|else|return|new)\b/g, '<span class="kwd">$1</span>') // Keywords
        .replace(/\b(System|String)\b/g, '<span class="typ">$1</span>') // Types
        .replace(/\b(out|println|main)\b/g, '<span class="mtd">$1</span>') // Methods
        .replace(/\b(\d+)\b/g, '<span class="num">$1</span>'); // Numbers
        
    return html;
}
