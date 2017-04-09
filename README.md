# RecordMemo
一个记录Android开发通用UI界面的Demo, It is growing

### Git第一次提交技巧 克隆仓库

1.  在根目录文件夹 右键打开命令行或者cd到项目根目录  开始克隆
    git clone https://github.com/wanghoa/ThreeDemo.git 
2、 配置个人信息
      git config --global user.email "你的GitHub绑定的邮箱" 
      git config --global user.name "你的GitHub用户名" 	

3、 git status 
    git add . 
    git commit -m "第一次提交"

4、 git push -u origin master
    根据提示 输入 GitHub用户名  GitHub密码 
    
    
### 以后提交参考，记得勤交代码

首先你要安装git，  然后到你要提交代码的根目录 鼠标右键---git push here
1、输入自己的用户名和邮箱
     git config --global user.email "niudong@xinweitech.cn " 
     git config --global user.name "NiuDong"
2、连接远程仓库
     git remote add origin https://git.oschina.net/NiuDong/SightSing.git
     git clone https://git.oschina.net/NiuDong/SightSing.git
3、记得每次先更新
	 git pull origin master 回车
4、添加到仓库
	 git add .
5、提交说明
	 git commit -m "我是xxx，我改了注册界面"
6、提交Push
	 git push -u origin master     然后根据提示输入用户名和密码 OK！！！


Demo 说明：

1、状态栏参考地址：http://blog.csdn.net/guolin_blog/article/details/51763825
