learn git
===
## part 3. 使用github的前期准备

### 1. 创建或修改文件
`touch HelloWorld.java`
- - -

### 2. 查看仓库状态
`git status`

- - -
### 3. 将更改添加到暂存区
 - 将新文件或修改的文件添加到 Git 的暂存区。
	`git add HelloWorld.java`
 -  添加所有更改：
	` git add .`
---
### 4. 提交更改
- 将暂存区的更改提交到本地仓库，添加有意义的提交说明。
    `git commit -m "添加 HelloWorld.java 文件"`
---
### 5. 推送更改到远程仓库

- 进行push
	`git push`
- 将本地仓库的更改推送到远程仓库。
	`git push origin main`
---
### 6. 在 Git Bash 中正确导航到 Windows 目录
- 在 Git Bash 中，Windows 的驱动器（如 D:）被映射为 /d/。路径中的反斜杠（\）需要替换为正斜杠（/）。
- Windows 路径：
`D:\java_ij\learn_java\learn_git`
- Git Bash 路径：
`/d/java_ij/learn_java/learn_git`
-  执行更改目录的命令
`cd /d/java_ij/learn_java/learn_git`
- 查看当前目录
`pwd`
---
### 7. 克隆已有的 Git 仓库
  1. 根据您使用的 Git 平台（如 GitHub、GitLab 等），获取仓库的克隆 URL。
  2. 在目标目录中运行 git clone 命令.

  - 使用 SSH URL：
    `git clone git@github.com:用户名/仓库名.git`
---
### 8. 查看提交日志
  `git log`

### 9. clone 已有仓库

命令格式：
`git clone git@github.com:用户名/仓库名.git`
如：
`git clone git@github.com:youfangcai/learn_java_kivi.git`

---
## part 4. 通过实际操作学习git

### 4.1  基本操作
- git init——初始化仓库

  要使用 Git 进行版本管理，必须先初始化仓库。Git 是使用 git
init命令进行初始化的。
  如果初始化成功，执行了git init命令的目录下就会生成 .git 目录。这个 .git 目录里存储着管理当前目录内容所需的仓库数据。
  在 Git 中，我们将这个目录的内容称为“附属于该仓库的工作树”。 文件的编辑等操作在工作树中进行，然后记录到仓库中，以此管理文件 的历史快照。如果想将文件恢复到原先的状态，可以从仓库中调取之前 的快照，在工作树中打开。开发者可以通过这种方式获取以往的文件。