<div align="center">
<p>
    <img width="200" src="https://raw.githubusercontent.com/CCBlueX/LiquidCloud/master/LiquidBounce/liquidbounceLogo.svg">
</p>

[liquidbounce.net](https://liquidbounce.net) |
[论坛](https://forums.ccbluex.net) |
[Guilded](https://guilded.gg/CCBlueX) |
[油管](https://youtube.com/CCBlueX) |
[推特](https://twitter.com/CCBlueX)
</div>
<div align="center">

[中文水影官网](https://mctest.go176.net)
</div>

水影是一个免费开源并基于Mixin的注入式黑客端, 使用Fabric API. 

## 反馈问题
如果你在使用**水影**的时候遇到任何问题/bug或新增的功能, 你可以在 [这里](https://github.com/CCBlueX/LiquidBounce/issues) 创建一个Issue. 记得使用英文:)

## 许可
此项目遵守[GNU General Public License v3.0](https://www.gnu.org/licenses/gpl-3.0.en.html). 此许可仅仅适用于这个仓库里的源码. 在开发和编译过程中，我们可能使用其他未经授权的源码. 一些代码不在GPL许可的范围之内.

对于那些不熟悉这个开源许可的人, 下面是许可主要内容的一些总结. 这绝不是法律意见, 也不具有法律约束力.

*一些你允许做的事:*

- 使用
- 分享
- 修改

*如果你想要使用此源码中的**任何**代码:*

- **你必须开源你修改后的源码以及你从此项目中获取的源代码。这意味着您不允许在闭源/混淆应用程序中使用此项目的代码(或者部分代码).**
- **你修改过的应用必须遵守GPL开源许可** 

## 编译
水影使用Gradle, 为了确保它安装正确，你可以查看[Gradle官网](https://gradle.org/install/).
1. 使用 `git clone --recurse-submodules https://github.com/CCBlueX/LiquidBounce` 克隆水影仓库. 
2. CD到本地仓库文件夹.
3. 运行 `gradlew genSources`.
4. 在你喜爱的ide中以Gradle项目打开文件夹.
5. 运行客户端.

## 外部依赖库
### Mixins
Mixins can be used to modify classes at runtime before they are loaded. LiquidBounce uses it to inject its code into the Minecraft client. This way, none of Mojang's copyrighted code is shipped. If you want to learn more about it, check out its [Documentation](https://docs.spongepowered.org/5.1.0/en/plugin/internals/mixins.html).

## 贡献
我们感谢贡献. 因此如果你想支持我们, 欢迎修改水影的源码并提交PR.
