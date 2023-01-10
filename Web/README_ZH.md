#远程配置JavaScript SDK CodeLab

##简介

本项目是一个CodeLab，用于使用远程配置JavaScript SDK开发。

##准备环境

-一台计算机可以编译运行Vue项目

##快速入门

1、如果您没有华为开发者账号，您需要【注册账号】（https://developer.huawei.com/consumer/en/doc/start/registration-and-verification-0000001053628148）并通过身份验证。

2、使用您的帐户登录【应用市场连接】（https://developer.huawei.com/consumer/en/doc/development/AppGallery-connect-Guides/agc-get-started），创建项目并添加应用，将应用平台设置为Web。

3、在我的项目中选择您的项目和应用，进入成长>远程配置，启用远程配置服务。

4、选择项目设置，进入管理APIs，启用远程配置服务。

5.转到常规信息，从AppGallery Connect中复制SDK代码段json，并将json粘贴到创建的remoteconfig.vue函数中const **agConnectConfig**的代码段中（）。

6、在演示路径的终端中运行以下代码，演示将启动。

   ```bash
   # install dependencies
    npm install
   
    # run demo
    npm start
   ```

7.关于【远程配置】的更多详细信息（https://developer.huawei.com/consumer/en/doc/development/AppGallery-connect-Guides/agc-remoteconfig-web-getstarted-0000001056501223）。

##问题或问题

如果您对如何使用AppGallery Connect Demos有疑问，请尝试以下选项：

-【堆栈溢出】（https://stackoverflow.com/）是任何编程问题的最佳地点。请务必使用“AppGallery”标记您的问题。
-【华为开发者论坛】（https://forums.developer.huawei.com/forumPortal/en/home）AppGallery模块非常适合一般问题，或寻求建议和意见。

如果您在我们的示例中遇到错误，请向存储库提交【问题】（https://github.com/AppGalleryConnect/agc-demos/issues）。更好的是，您可以提交带有修复程序的【拉取请求】（https://github.com/AppGalleryConnect/agc-demos/pulls）。

##许可证

此演示在【Apache许可证，版本2.0】（http://www.apache.org/licenses/LICENSE-2.0）下获得许可。