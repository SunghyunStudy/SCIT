// 페이지가 로드되면 실행
document.addEventListener('DOMContentLoaded', function () {
    findAllRooms(); // 최초 1회 방 목록 불러오기

    // 3초마다 방 목록을 갱신 (실시간처럼 보이게)
    setInterval(findAllRooms, 3000);
});

// 1. 방 목록 불러오기 (AJAX GET)
function findAllRooms() {
    fetch('/api/rooms')
        .then(response => response.json())
        .then(rooms => {
            const listElement = document.getElementById("roomList");
            listElement.innerHTML = ""; // 기존 목록 비우기

            if (rooms.length === 0) {
                listElement.innerHTML = "<p>현재 개설된 방이 없습니다.</p>";
                return;
            }

            // 방 하나씩 HTML로 만들기
            rooms.forEach(room => {
                const div = document.createElement("div");
                div.className = "room-card";

                // 비밀번호 방인지 표시
                const lockIcon = room.password ? "🔒 " : "";

                div.innerHTML = `
                    <strong>${lockIcon}${room.title}</strong> 
                    <span style="float:right;">(${room.currentUser}/${room.maxUser})</span>
                `;

                // 방 클릭 시 입장 함수 호출 (아직 구현 전)
                div.onclick = function () {
                    enterRoom(room.roomId);
                };

                listElement.appendChild(div);
            });
        });
}

// 2. 방 만들기 (AJAX POST)
function createRoom() {
    const title = document.getElementById("roomTitle").value;
    const password = document.getElementById("roomPassword").value;

    if (!title) {
        alert("방 제목을 입력해주세요.");
        return;
    }

    fetch('/api/rooms', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ title: title, password: password }),
    })
        .then(response => response.json())
        .then(room => {
            alert(room.title + " 방이 생성되었습니다!");
            findAllRooms(); // 목록 갱신
            // 바로 입장시키려면 아래 주석 해제 (나중에 구현)
            enterRoom(room.roomId);
        })
        .catch(error => {
            console.error('Error:', error);
            alert("방 생성에 실패했습니다.");
        });
}

// 3. 방 입장 처리 (임시)
function enterRoom(roomId) {
    // 나중에 /game/room/roomId 경로로 이동시킬 예정
    alert("입장할 방 ID: " + roomId + "\n(아직 게임 화면을 안 만들어서 이동은 안됩니다!)");
    // window.location.href = "/game/room/" + roomId; 
}