#!/bin/bash

# @desc : 用于自动生成mybatis的相关文件
# @date : 2016-05-04
# @author : Pigo.can

java=`which java`

  echo ""
  echo ""
  echo "################################"
  echo "* @author pigo.can "
  echo "* @email rushingpig@163.com"
  echo "* @homepage http://www.pigo.top "
  echo "* @ver V1.0 "
  echo "#################################"
  echo ""
  echo ""
  echo ""
  echo ""
  echo ""


 echo "↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓           开始生成mybatis相关文件，请稍后....              ↓↓↓↓↓↓↓↓↓↓↓↓↓↓"
 echo ""
 script_parent_dir_path=`dirname $0`
 cd ${script_parent_dir_path}/../mybatis-generator
 #	此处是覆盖已有文件
${java} -jar mybatis-generator-core-1.3.2.jar  -configfile generatorConfig.xml -overwrite

echo ""
echo "↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑                      生成完毕 ！           ↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑"

read -p "please press any key to continue....."

