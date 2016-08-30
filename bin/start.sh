#!/bin/bash

# 更新项目内容
function repository_update(){
	GIT=`which git`
	git stash
	git pull --rebase
	git stash pop	
	if [ $? -gt 0 ]; then
		echo "when to update the repository failed..."
		exit 1
	fi
}
# 项目编译打包
function build(){
	GRADLE=`which gradle`
	gradle clean build -xtest
	if [ $? -gt 0 ]; then
		echo "when to build the architecture failed..."
		exit 1
	fi
}

cd `dirname $0`
BIN_DIR=`pwd`

cd ..
DEPLOY_DIR=`pwd`

repository_update
build

cd build/libs
JAR_IDR=`pwd`


SERVER_NAME="puff-blissmall"
SERVER_PORT=8087
PROFILE="production"

if [ -z "$SERVER_NAME" ]; then
	SERVER_NAME=`hostname`"-puff"
fi

PIDS=`ps -ef | grep java | grep "${SERVER_NAME}*" | awk '{print $2}'`
if [ -n "$PIDS" ]; then
	echo "ERROR: THE ${SERVER_NAME} already started!"
	echo "PID: ${PIDS}"
	exit 1
fi

if [ -n "$SERVER_PORT" ]; then
	SERVER_PORT_COUNT=`netstat -tln | grep ${SERVER_PORT} | wc -l`
	if [ ${SERVER_PORT_COUNT} -gt 0 ]; then
		echo "ERROR: The $SERVER_NAME port $SERVER_PORT already used !"
		exit 1
	fi
fi


if [ "$1" = "qa" ]; then
	PROFILE="qa"	
elif [ "$1" = "production" ];then
	PROFILE="production"
else
	PROFILE="dev"
fi

echo "the profile is : ${PROFILE}"

JAVA_OPTS=" -Dspring.profiles.active=${PROFILE}"
JAVA_MEM_OPTS=""
BITS=`java -version 2>&1 | grep -i 64-bit`
if [ -n "$BITS" ]; then
    JAVA_MEM_OPTS=" -Xmx4096m -Xms4096m -Xss512k -XX:+DisableExplicitGC -XX:+UseConcMarkSweepGC -XX:+CMSParallelRemarkEnabled -XX:+UseCMSCompactAtFullCollection -XX:LargePageSizeInBytes=128m -XX:+UseFastAccessorMethods -XX:+UseCMSInitiatingOccupancyOnly -XX:CMSInitiatingOccupancyFraction=140"
else
    JAVA_MEM_OPTS=" -Xms1g -Xmx1g -XX:PermSize=128m -XX:SurvivorRatio=2 -XX:+UseParallelGC "
fi


echo -e "Starting the $SERVER_NAME ... \c"

nohup java -jar ${JAVA_OPTS} ${JAVA_MEM_OPTS} puff-1.0.jar &

COUNT=0
while [ $COUNT -lt 1 ]; do    
    echo -e ".\c"
    sleep 1 
	COUNT=`netstat -tln | grep ${SERVER_PORT} | wc -l`
	if [ ${COUNT} -gt 0 ]; then
		break
	fi
done

echo "OK..."
echo "the server started with port ${SERVER_PORT}"