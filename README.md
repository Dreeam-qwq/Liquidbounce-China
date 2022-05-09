# 中文水影
一个免费基于Mixin的Forge注入式Minecraft黑客端.

官网: https://liquidbounce.net \
论坛: https://forum.ccbluex.net/index.php \
Discord: https://discord.gg/gdQ82He \
油管: https://youtube.com/CCBlueX \
推特: https://twitter.com/CCBlueX

中文水影官网: https://mctest.go176.net

## 反馈&建议
如果你遇到任何bug或缺失的功能, 你可以在 [这里](https://github.com/CCBlueX/LiquidBounce1.8-Issues/issues) 创建一个Issue来让我们知道, 记得使用英文:)

## 许可

此项目遵守 [GNU General Public License v3.0](LICENSE). 此许可仅仅适用于这个仓库里的源码. 在开发和编译过程中，我们可能使用其他未经授权的源码. 一些代码不在GPL许可的范围之内.

对于那些不熟悉这个开源许可的人, 下面是许可主要内容的一些总结. 这绝不是法律意见, 也不具有法律约束力.

一些你允许做的事:
- 使用
- 分享
- 修改

这个项目是完全或部分免费，甚至商业的。但请考虑以下事项:

- **你必须开源你修改后的源码以及你从此项目中获取的源代码。这意味着您不允许在闭源/混淆应用程序中使用此项目的代码(或者部分代码).**
- **你修改过的应用必须也遵守GPL开源许可** 

遵守上面的规则, 并开源你的源码; 就像我们做的一样.

## Setting up a Workspace
水影使用Gradle, 为了确保它安装正确，你可以查看 [Gradle官网](https://gradle.org/install/).
1. 使用 `git clone https://github.com/CCBlueX/LiquidBounce` 克隆仓库. 
2. CD到本地文件夹.
3. Depending on which IDE you are using execute either of the following commands:
    - For IntelliJ: `gradlew --debug setupDevWorkspace idea genIntellijRuns build`
    - For Eclipse: `gradlew --debug setupDevWorkspace eclipse build`
4. Open the folder as a Gradle project in your IDE.
5. Select either the Forge or Vanilla run configuration.

## 外部依赖库
### Mixins
Mixins can be used to modify classes at runtime before they are loaded. LiquidBounce is using it to inject its code into the Minecraft client. This way, we do not have to ship Mojangs copyrighted code. If you want to learn more about it, check out its [Documentation](https://docs.spongepowered.org/5.1.0/en/plugin/internals/mixins.html).

## 贡献

We appreciate contributions. So if you want to support us, feel free to make changes to LiquidBounce's source code and submit a pull request. Currently our main goals are the following:
1. Improve LiquidBounce's performance.
2. Re-work most of the render code.

If you have experience in one or more of these fields, we would highly appreciate your support.

## TODO

/ui

todo in /untils
