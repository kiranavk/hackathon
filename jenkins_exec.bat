REM javaw -jar ./hackathon-api/target/hackathon-0.0.1-SNAPSHOT.jar &
netstat -ano | find "9090"
taskkill /F /PID 18396
FOR /F "tokens=4 delims= " %P IN ('netstat -a -n -o ^| findstr :9090') DO TaskKill.exe /PID %P
start /b javaw -jar ./hackathon-api/target/hackathon-0.0.1-SNAPSHOT.jar &

wget --no-check-certificate --no-cookies --header "Cookie: oraclelicense=accept-securebackup-cookie" http://download.oracle.com/otn-pub/java/jdk/8u141-b15/336fa29ff2bb4ef291e347e091f7f4a7/jdk-8u141-linux-x64.rpm
sudo yum install -y jdk-8u141-linux-x64.rpm
nohup java -jar hackathon-0.0.1-SNAPSHOT.jar > demo-app.log 2>&1 &

scp hackathon-0.0.1-SNAPSHOT.jar ninjaroot@52.66.211.71:~/
