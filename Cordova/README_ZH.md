# AGConnect科尔多瓦远程配置演示

`在根目录下，执行以下操作：`

`步骤1：执行以下命令安装NPM依赖`
'$npm安装'

`第二步：执行以下命令创建对应的平台代码`
`$科尔多瓦平台添加ios/android`

`步骤3：执行以下命令下载安装AGC remoteconfig插件`
`$科尔多瓦插件添加@科尔多瓦插件-agconnect/remoteconfig--save`

`步骤4：将相应的agconnect-services.json/agconnect-services.plist放入相应的目录`

`步骤5：将config.xml文件中的包名修改为agconnect-services.json/agconnect-services.plist文件中的包名`

`步骤6：将agcp-pl添加到相应的bulid.gradle中（仅限Android）`

'应用插件：'com.huawei.agconnect'''
