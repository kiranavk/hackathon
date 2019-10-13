REM javaw -jar ./hackathon-api/target/hackathon-0.0.1-SNAPSHOT.jar &
netstat -ano | find "9090"
taskkill /F /PID 18396
FOR /F "tokens=4 delims= " %P IN ('netstat -a -n -o ^| findstr :9090') DO TaskKill.exe /PID %P
start /b javaw -jar ./hackathon-api/target/hackathon-0.0.1-SNAPSHOT.jar &