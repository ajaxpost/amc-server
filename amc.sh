AppName=amc-admin-0.0.1.jar

# 在生产环境中,可以执行start,stop等等,需要与jar包同级目录
# 在本地可以通过 sh ./amc.sh rz 打包上传

if [ "$1" = "" ];
then
    echo -e "\033[0;31m 未输入操作名 \033[0m  \033[0;34m {start|stop|restart|status} \033[0m"
    exit 1
fi

if [ "$AppName" = "" ];
then
    echo -e "\033[0;31m 未输入应用名 \033[0m"
    exit 1
fi

# 不建议使用这个
# 还是手动上传好
# 因为之前的java程序可能还在运行,你就给之前的那个删了,可能导致运行了多个
function rz(){
  # 打包
  mvn clean package

  # 上传
  scp ./amc-admin/target/$AppName root@node1:/usr/JavaCode/
  echo "上传成功"
}


function start()
{
    PID=`ps -ef |grep java|grep $AppName`


    if [ x"$PID" != x"" ]; then
        echo "$AppName is running..."
    else
      nohup java -jar $AppName > /dev/null 2>&1 &
      echo "Start $AppName success..."
    fi
}

function stop(){
  echo "Stop $AppName"
  PID=""
  query(){
    PID=`ps -ef |grep java|grep $AppName`
  }
  query
  if [ x"$PID" != x"" ]; then
    kill -9 $PID
    echo "$AppName (pid:$PID) exiting..."
    # do while 循环,直到进程结束
		while [ x"$PID" != x"" ]
		do
		  # 睡眠1秒,防止cpu占用过高
			sleep 1
			query
		done
		echo "$AppName exited."
  else
		echo "$AppName already stopped."
  fi

}

function restart()
{
    stop
    sleep 2
    start
}

function status(){
    PID=`ps -ef |grep java|grep $AppName|wc -l`
    if [ $PID != 0 ];then
        echo "$AppName is running..."
    else
        echo "$AppName is not running..."
    fi
}

# 表示根据第一个参数的值，选择执行哪个函数
# 例如：sh amc.sh start 会执行start函数
# "start)" 是一个分支的开始, start ;; 是一个分支的结束 相当于 break
case $1 in
  "start")
    start
    ;;
  "stop")
    stop
    ;;
  "restart")
    restart
    ;;
  "status")
    status
    ;;
  "rz")
    rz
    ;;
  *)
    ;;
esac
