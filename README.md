# CustomStatusBar
CustomStatusBar 是Android上实现一个自定义状态栏同原生状态栏一致，既然是自定义，那你想显示什么，显示什么位置，显示什么样子都由你说了算。
主要的使用场景，目前觉得用在与硬件设备交互显示实时信息上，其它的使用场景就由你自由发挥。

使用Service+BaseActivity+WindowManager实现，源代码：[https://github.com/canney-chen/CustomStatusBar/tree/master/StatusBar](https://github.com/canney-chen/CustomStatusBar/tree/master/StatusBar)

## 1.  实现CustomStatusBarService 
     1. 需要继承me.kaini.statusbar.AbsStatusBar
  
     2. 实现protected void initStatusBar(StatusBar statusBar)方法初始化状态栏的内容
  
## 2.  配置AndroidManifest.xml
  - 需要应用的AndroidManifest.xml文件中配置一个applicatipn 它的name=me.kaini.statusbar.base.StatusBarApplication
  
  - 当然上面的CustomStatusBarService也得配置了。

## 3. 继承me.kaini.statusbar.base.BaseActivity实现业务Activity

示例代码:[https://github.com/canney-chen/CustomStatusBar/tree/master/Example](https://github.com/canney-chen/CustomStatusBar/tree/master/Example)

通过上面三步就可以实现自定义的状态栏了。

![自定义状态栏效果图](https://github.com/canney-chen/CustomStatusBar/blob/master/Example/statusbar.png)


## 如果到这里还没搞定你可以访问我的博客：**[http://blog.csdn.net/canney_chen](http://blog.csdn.net/canney_chen)**
- 查看[Android实现自定义状态栏](http://blog.csdn.net/canney_chen/article/details/46042323)、[解决自定义状态栏对软键盘及界面内容遮挡问题](http://blog.csdn.net/canney_chen/article/details/48788063)的文章

- MailTo:chen.canney@gmail.com
