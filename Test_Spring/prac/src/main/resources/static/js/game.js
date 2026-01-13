// --- Game Scene (Gameplay) ---
class GameScene extends Phaser.Scene {
    constructor() {
        super({ key: 'GameScene' });
    }

    preload() {
        // Asset loading placeholder
        // this.load.image('tiles', '/assets/map/tileset.png'); 
        // this.load.tilemapTiledJSON('map', '/assets/map/scit_map.json');
        // this.load.spritesheet('player', '/assets/sprites/player.png', { frameWidth: 32, frameHeight: 32 });
    }

    create() {
        // Fade In Effect
        this.cameras.main.fadeIn(1000, 0, 0, 0);

        this.isEventProcessing = false;

        // --- MAP & GRAPHICS ---
        const graphics = this.add.graphics();
        
        // Background
        graphics.fillStyle(0x2d2d2d, 1);
        graphics.fillRect(0, 0, 1600, 1200);
        
        // Grid
        graphics.lineStyle(1, 0x444444, 0.5);
        for(let i=0; i<1600; i+=32) {
            graphics.moveTo(i, 0);
            graphics.lineTo(i, 1200);
        }
        for(let i=0; i<1200; i+=32) {
            graphics.moveTo(0, i);
            graphics.lineTo(1600, i);
        }
        graphics.strokePath();

        // Zones
        // Boss Room
        graphics.lineStyle(4, 0xff0000, 1);
        graphics.strokeRect(1000, 800, 200, 200);
        this.add.text(1050, 850, "BOSS ROOM\n(Stage 4)", { color: '#ff0000', align: 'center' });

        // Quiz Zone
        graphics.lineStyle(4, 0x0000ff, 1);
        graphics.strokeRect(200, 200, 100, 100);
        this.add.text(210, 210, "QUIZ\n(Stage 1)", { color: '#0000ff', align: 'center' });

        this.physics.world.setBounds(0, 0, 1600, 1200);

        // --- PLAYER ---
        this.player = this.add.rectangle(100, 100, 32, 32, 0x00ff00);
        this.physics.add.existing(this.player);
        this.player.body.setCollideWorldBounds(true);

        // --- CAMERA ---
        this.cameras.main.setBounds(0, 0, 1600, 1200);
        this.cameras.main.startFollow(this.player, true, 0.09, 0.09);

        // --- INPUT ---
        this.cursors = this.input.keyboard.createCursorKeys();
        this.input.keyboard.on('keydown-SPACE', () => {
            this.checkEvent(this.player.x, this.player.y);
        });

        // UI Text
        this.add.text(10, 10, "Arrows: Move | SPACE: Interact", { 
            font: "16px Arial", fill: "#ffffff", backgroundColor: "#000000" 
        }).setScrollFactor(0);
    }

    update() {
        if (this.isEventProcessing) {
            this.player.body.velocity.x = 0;
            this.player.body.velocity.y = 0;
            return;
        }

        const speed = 200;
        this.player.body.velocity.x = 0;
        this.player.body.velocity.y = 0;

        if (this.cursors.left.isDown) this.player.body.velocity.x = -speed;
        else if (this.cursors.right.isDown) this.player.body.velocity.x = speed;

        if (this.cursors.up.isDown) this.player.body.velocity.y = -speed;
        else if (this.cursors.down.isDown) this.player.body.velocity.y = speed;

        this.player.body.velocity.normalize().scale(speed);
    }

    checkEvent(x, y) {
        if (this.isEventProcessing) return;

        // Visual Feedback
        const feedback = this.add.circle(x, y, 30, 0xffffff, 0.3);
        this.tweens.add({
            targets: feedback, scale: 2, alpha: 0, duration: 500,
            onComplete: () => feedback.destroy()
        });

        console.log(`Checking event at x:${Math.round(x)}, y:${Math.round(y)}`);

        fetch('/api/game/check-event', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ userId: 'student_1', x: Math.round(x), y: Math.round(y) })
        })
        .then(response => response.json())
        .then(data => {
            if (data.event !== 'none') {
                alert(`[System] ${data.message}`);
                this.isEventProcessing = true;
                setTimeout(() => { this.isEventProcessing = false; }, 1000);
            }
        })
        .catch(err => {
            console.error(err);
        });
    }
}

// --- Configuration ---
const config = {
    type: Phaser.AUTO,
    width: 800,
    height: 600,
    parent: 'game-container',
    physics: {
        default: 'arcade',
        arcade: { gravity: { y: 0 }, debug: false }
    },
    scene: [GameScene]
};

const game = new Phaser.Game(config);

// Start paused? No, let it run in background but covered by UI.
// Or we can pause it immediately.
game.events.on('ready', () => {
    // Optional: Pause logic if needed
});
