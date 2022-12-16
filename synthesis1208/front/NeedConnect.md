# 需要连接的部分

## 一些命名

书籍在前端是以b开头的，bid，bname，byear，btype，bauthor，bpublisher

论文在前端是以t开头的，tid，tname，tyear，ttype，tauthor，agent

用户没有开头  id，name，type。password。department。tel

借阅记录 多一个lid，returntime，lendtime，，stauts表示是否归还

## Login

    login()函数中，需要调用数据库获得账号密码，原版代码为被注释掉的部分。访问的Mapper是/login

## Register

    register函数中，保持了源代码的写法，要写入数据库。是post（user/register）

## 进入UserHome主页

### 创建页面

    需要从数据库获得更完整的信息，修正created函数

    Updatabook和Updatethesis函数，把新的数据传进数据库。二者查询不同的数据库

### BookWithUser面板

    获取借阅信息的。
    加载面板的时候要获得本用户的借阅信息，this.user获得传进来的用户信息（其实只有账号密码，基本所有函数都有。

### Dashboard面板

    mounted函数，其中有get(/dashboard),用来获取数据库里各类统计信息，会自动化图和更改信息

### Book thesis查询

    两个一样的，和BookWithUser面板一样

### Book thesis的上传

    BookWithUser的save函数

### 个人信息的修改

    Update函数

### 密码也是
