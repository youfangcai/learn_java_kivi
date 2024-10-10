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
### 8. 删除文件
如果你想删除文件，可以使用以下命令：
`git rm 文件名`

### 9.添加更改
添加修改或删除的文件到暂存区：
`git add 文件名`, 再提交，推送就更改完成

---

### 8. 查看提交日志
  `git log`

### 9. clone 已有仓库

命令格式：
`git clone git@github.com:用户名/仓库名.git`
如：
`git clone git@github.com:youfangcai/learn_java_kivi.git`

### 10. 更新文件工作流程

一般情况下，遵循 `git add` -> `git commit` -> `git pull` -> `git push` 的顺序是个好的实践，尤其是在团队协作时。这样可以确保你的提交是最新的，并且减少潜在的冲突。
备注： 拉取和推送的顺序: 如果你的团队成员在你上次拉取之后对同一分支进行了更改，拉取操作将合并远程的更改到你的本地分支。这样可以确保你在推送之前解决任何潜在的冲突。

---
## part 4. 通过实际操作学习git

### 4.1  基本操作
#### 1. git init——初始化仓库

  要使用 Git 进行版本管理，必须先初始化仓库。Git 是使用 `git init`命令进行初始化的。
  如果初始化成功，执行了git init命令的目录下就会生成 .git 目录。这个 .git 目录里存储着管理当前目录内容所需的仓库数据。
  在 Git 中，我们将这个目录的内容称为“附属于该仓库的工作树”。 文件的编辑等操作在工作树中进行，然后记录到仓库中，以此管理文件 的历史快照。如果想将文件恢复到原先的状态，可以从仓库中调取之前 的快照，在工作树中打开。开发者可以通过这种方式获取以往的文件。
#### 2. 只显示提交信息的第一行

`git log --pretty=short` 这样一来开发人员就能够更轻松地把握多个 提交。
#### 3. 只显示指定目录、文件的日志

只要在git log命令后加上目录名，便会只显示该目录下的日志。 如果加的是文件名，就会只显示与该文件相关的日志。
`git log README.md`
#### 4. 显示文件的改动

可以加上-p参数，文件的前后差 别就会显示在提交信息之后。
`git log -p`
比如，执行下面的命令，就可以只查看 README.md 文件的提交日 志以及提交前后的差别。
`git log -p README.md`
#### 5. git diff——查看更改前后的差别

git diff命令可以查看工作树、暂存区、最新提交之间的差别。
+”号标出的是新添加的行，被删除的行则用“-”号标出。
不妨养成这样一个好习惯：在执行 git commit命令之前先执行 git diff HEAD命令，查看本次提交与上次提交之间有什么差别，等 确认完毕后再进行提交。这里的HEAD是指向当前分支中最新一次提交 的指针。


### 4.2 分支的操作

#### 1. git branch——显示分支一览表

`git branch`命令可以将分支名列表显示，同时可以确认当前所在 分支。
分支左侧标有“*”（星号），表示这是我们当前所 在的分支。

#### 2. git checkout -b——创建、切换分支

  执行下面的命令，创建名为 feature-A 的分支。
  ` git checkout -b feature-a`
  等同于
   ```
  git branch feature-a
  git checkout feature-a
   ```

  `git checkout 分支名`可以切换为其他指定分支。
  切换回上一个分支 `git checkout -` 像上面这样用“-”（连字符）代替分支名，就可以切换至上一个分 支。
#### 3. git merge——合并分支

为了在历史记录中明确记录下本次分支合 并，我们需要创建合并提交。因此，在合并时加上 --no-ff参数。
`git merge --no-ff feature-a`
#### 4. git log --graph——以图表形式查看分支

git log --graph命令可以用图表形式输出提交日志，非常直观。
`git log --graph`

### 4.3 更改提交的操作

#### 1. git reset——回溯历史版本

要让仓库的HEAD、暂存区、当前工作树回溯到指定状态，需要用到 git rest --hard 命令。只要提供目标时间点的哈希值,就可以完全恢复至该时间点的状态。
`git reset --hard fd0cbf0d4a25f747230694d95cac1be72d33441d`
>哈希值在每个环境中各不相同，读者请查看自身当前环境中 Add index 的哈希值， 进行替换。
#### 2. 推进至 feature-A 分支合并后的状态

`git log`命令只能查看以当前状态为终点的历史日志。所以这里要使用 `git reflog`命令，查看当前仓库的操作日志。在日志中找出 回溯历史之前的哈希值，通过`git reset --hard`命令恢复到回溯历 史前的状态。
**注：执行 git reflog 命令，查看当前仓库执行过的操作的日志。可以利用 git reflog命令恢复到原先的状态，所以请各位读者务必牢记本部分。**
#### 3. 消除冲突
不解决冲突就无法完成合并。
查看冲突部分并将其解决。