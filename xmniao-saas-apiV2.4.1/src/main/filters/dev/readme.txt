
#==============增加配置项指南==============
###@@@@@@@   开发人员添加新属性时说明  @@@@@@@@@                                                        ###
###  ！！注意！！开发人员在此文件添加新属性时，操作如下 【前三步骤为必须操作，第四步可选】：                          ### 
###  1. 在src/main/resources相应文件添加新的属性以及属性值占位符，如image_max_size=${image_max_size}         ###
###  2. 提供开发时的属性属性值，即在src/main/filters/dev/default/的相应文件中添加属性值，如image_max_size=1    ###
###  3. 提供产品发布打包时的属性值，如在src/main/filters/production的相应文件中添加属性值，如image_max_size=2   ###
###  4. 如果需要，开发人员可根据自己的需求，将属性配置在个人的开发配置文件中（src/main/filters/dev/目录下）         ###


@@@@
如果需要在dev/default目录增加一个新的配置文件，需要在pom的profiles的default profile节点的过滤（filters）增加相应的配置


#=============================开发运行指南============================
各个开发者可以在参考dev目录下建立自己的开发环境配置（参考william.properties），
但是开发者不用各个文件都进行建立，只需要建立与自己本地环境相关的配置项目就可。

运行时使用mvn jetty:run -Pdefault -Dname=william   william是开发者自己建立的配置文件的名称
eclipse运行时不用填写mvn

改变jetty启动端口使用 jetty.port=8081 参数