#!/bin/bash
# created_by : pigo
# desc : 重新启动服务

cd `dirname $0`
echo "the server is stopping..."
./stop.sh

sleep 1

echo "the server is starting..."
./start.sh $1