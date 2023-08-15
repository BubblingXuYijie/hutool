#!/bin/bash

#
# Copyright (c) 2023 looly(loolly@aliyun.com)
# Hutool is licensed under Mulan PSL v2.
# You can use this software according to the terms and conditions of the Mulan PSL v2.
# You may obtain a copy of Mulan PSL v2 at:
#          https://license.coscl.org.cn/MulanPSL2
# THIS SOFTWARE IS PROVIDED ON AN "AS IS" BASIS, WITHOUT WARRANTIES OF ANY KIND,
# EITHER EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO NON-INFRINGEMENT,
# MERCHANTABILITY OR FIT FOR A PARTICULAR PURPOSE.
# See the Mulan PSL v2 for more details.
#

#-----------------------------------------------------------
# 此脚本用于每次升级Hutool时替换相应位置的版本号
#-----------------------------------------------------------

set -o errexit

pwd=$(pwd)

echo "当前路径：${pwd}"

if [ -n "$1" ];then
    new_version="$1"
    old_version=$(cat "${pwd}"/bin/version.txt)
    echo "$old_version 替换为新版本 $new_version"
else
    # 参数错误，退出
    echo "ERROR: 请指定新版本！"
    exit
fi

if [ -z "$old_version" ]; then
    echo "ERROR: 旧版本不存在，请确认bin/version.txt中信息正确"
    exit
fi

# 替换README.md中的版本
sed -i "s/${old_version}/${new_version}/g" "$pwd"/README.md
sed -i "s/${old_version}/${new_version}/g" "$pwd"/README-EN.md
# 替换docs/js/version.js中的版本
sed -i "s/${old_version}/${new_version}/g" "$pwd"/docs/js/version.js

# 保留新版本号
echo "$new_version" > "$pwd"/bin/version.txt
