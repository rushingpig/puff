#!/bin/bash
# created_by : pigo
# desc : 停止应用程序脚本

cd `dirname $0`
BIN_DIR=`pwd`
cd ..

SERVER_NAME="puff"

if [ -z "${SERVER_NAME}" ]; then
    SERVER_NAME=`hostname`"-toast"
fi

PIDS=`ps -ef | grep java | grep "${SERVER_NAME}*" | awk '{print $2}'`

echo -e "Stopping the ${SERVER_NAME} ...\c"

for PID in ${PIDS}; do
    kill ${PID} > /dev/null 2>&1
done

COUNT=0

while [ ${COUNT} -lt 1 ]; do
    echo -e ".\c"
    sleep 1
    COUNT=1
    for PID in ${PIDS} ; do
        PID_EXIST=`ps -f -p ${PID} | grep java`
        if [ -n "${PID_EXIST}" ]; then
            COUNT=0
            break
        fi
    done
done

echo " < finished >!"