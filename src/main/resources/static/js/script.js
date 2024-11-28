
        document.addEventListener("DOMContentLoaded", function () {
            const playerCar = document.querySelector(".player-car");
    
            let playerX = 100; 
            let playerY = 50;  
    
            const moveSpeed = 10;
    
            document.addEventListener("keydown", (event) => {
                switch (event.key) {
                    case "ArrowLeft":
                        playerX = Math.max(0, playerX - moveSpeed); 
                        break;
                    case "ArrowRight":
                        playerX = Math.min(740, playerX + moveSpeed); 
                        break;
                }
                updatePlayerPosition();
            });
    
            function updatePlayerPosition() {
                playerCar.style.left = `${playerX}px`;
                console.log("PlayerCar:", playerCar);
                checkCollisions();
            }
    
            function updateVehicles() {
                fetch("/api/vehicles")
                    .then((response) => response.json())
                    .then((vehicles) => {
                        vehicles.forEach((vehicle) => {
                            const vehicleElement = document.querySelector(`[data-id="${vehicle.id}"]`);
                            if (vehicleElement && vehicle.type !== "PlayerCar") {
                                vehicleElement.style.left = `${vehicle.x}px`;
                                vehicleElement.style.bottom = `${vehicle.y}px`;
                            }
                        });
                    })
                    .catch((error) => {
                        console.error("Erreur lors de la mise à jour des véhicules :", error);
                    });
            }
            setInterval(updateVehicles, 100); 
            
            });


        function checkCollisions() {
            fetch("/api/vehicles")
                .then((response) => response.json())
                .then((vehicles) => {
                    const playerCar = vehicles.find(v => v.type === "PlayerCar");
                    const enemies = vehicles.filter(v => v.type !== "PlayerCar");

                    console.log("PlayerCar Position: x=" + playerCar.x + ", y=" + playerCar.y);
                    enemies.forEach(enemy => {
                        console.log(
                            "Enemy Position: " + enemy.type +
                            " x=" + enemy.x + ", y=" + enemy.y
                        );
        
                        const isColliding = Math.abs(playerCar.x - enemy.x) < 50 &&
                                            Math.abs(playerCar.y - enemy.y) < 50;
        
                        if (isColliding) {
                            console.log("Collision détectée avec " + enemy.type);
                            alert("Collision ! Game Over");
                            window.location.reload(); // Recharge la page pour redémarrer le jeu
                        }
                    });
                });
        }
        