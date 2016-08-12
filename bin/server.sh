#!/bin/bash
# created_by : pigo
# desc : 用户管理所有脚本的入口

function usage(){
    echo "usage ::::::   sh|./  $0  { start | restart | stop}"
    exit 1
}

case "$1" in
    start | restart | stop )
        sh $1".sh" $2
    ;;
    *)
        usage
    ;;
esac