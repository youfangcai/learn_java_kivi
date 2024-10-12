learn_python_crawler
===
入门
---
## part 2. 复杂html解析
### 1. BeautifulSoup的find()和findAll()

借助它们，你可以通 过标签的不同属性轻松地过滤HTML页面，查找需要的标签组或单个标签。
```
findAll(tag, attributes, recursive, text, limit, keywords)
find(tag, attributes, recursive, text, keywords)
```
  注： 自己在 95% 的时间里都只需要使用前两个参数：tag 和 attributes。
### 2. 参数详解：

- 标签参数 tag 前面已经介绍过——你可以传一个标签的名称或多个标签名称组成的 Python 列表做标签参数。
- 属性参数 attributes 是用一个 Python 字典封装一个标签的若干属性和对应的属性值。
- 递归参数 recursive 是一个布尔变量。
- 文本参数 text 有点不同，它是用标签的文本内容去匹配，而不是用标签的属性。
- 范围限制参数 limit，显然只用于 findAll 方法。find 其实等价于 findAll 的 limit 等于 1 时的情形。
- 关键词参数 keyword，可以让你选择那些具有指定属性的标。
### 3. 其他BeautifulSoup对象

- BeautifulSoup 对象
    : 前面代码示例中的 bsObj
- 标签 Tag 对象
    : BeautifulSoup 对象通过 find 和 findAll，或者直接调用子标签获取的一列对象或单个 对象，就像：bsObj.div.h1
- NavigableString 对象
    : 用来表示标签里的文字，不是标签（有些函数可以操作和生成 NavigableString 对象， 而不是标签对象）。
- Comment 对象
    : 用来查找HTML文档的注释标签，<!-- 像这样 -->
### 4. 导航树

通过标签在文档中的位 置来查找标签。
#### 1. 处理子标签和其他后代标签
子标签就是一个父标签的下一级，而后代标签是指一个父标签 下面所有级别的标签。
- 一般情况下，BeautifulSoup 函数总是处理当前标签的后代标签。如：bsObj.div.findAll("img") 。
+ 处理子标签：children() 函数。
+ 处理后代标签：descendants() 函数。
#### 2. 处理兄弟标签
- BeautifulSoup 的 next_siblings() 函数可以让收集表格数据成为简单的事情，尤其是处理 带标题行的表格：
```
for sibling in bsObj.find("table",{"id":"giftList"}).tr.next_siblings: print(sibling)
```
（会打印产品列表里的所有行的产品，第一行表格标题除外。）
- 注：
    1. 对象不能把自己作为兄弟标签。
    2. 只调用后面的兄弟标签。
- previous_siblings 函数: 
    找到一组兄弟标签中的最后一个标签。(返回的是一组标签)
- next_sibling 和 previous_sibling 函数:
    与 next_siblings 和 previous_siblings 的作用类似，只是它们返回的是单个标签，而不是一组标签。
#### 3. 父标签处理

- BeautifulSoup 的父标签查找函数:
    parent 和 parents。
#### 4. 正则表达式

正则表达式，是因为它们可以识别正则字符串（regular string）；也就是说，它们 可以这么定义：“如果你给我的字符串符合规则，我就返回它”，或者是“如果字符串不符 合规则，我就忽略它”。
如：aa*bbbbb(cc)*(d | )
-正则表达式在实际中的一个经典应用是识别邮箱地址。
    完整的正则表达式：

```
[A-Za-z0-9\._+]+@[A-Za-z]+\.(com|org|edu|net)
```
- 正则表达式常用符号:
#### 5. 正则表达式和BeautifulSoup

BeautifulSoup 和正则表达式总是配合使用的。
大多数支 持字符串参数的函数（比如，find(id="aTagIdHere")）都可以用正则表达式实现。

#### 6. 获取属性

在网 络数据采集时你经常不需要查找标签的内容，而是需要查找标签属性。
比如标签 <a> 指向 的 URL 链接包含在 href 属性中，或者**img**标签的图片文件包含在 src 属性中。

- 对于一个标签对象，可以用下面的代码获取它的全部属性：
	**myTag.attrs**
- 要注意这行代码返回的是一个 Python 字典对象，可以获取和操作这些属性。比如要获取图 片的资源位置 src，可以用下面这行代码：
	**myImgTag.attrs["src"]**
#### 7. Lambda表达式
Lambda 表达式本质上就是一个函数，可以作为其他函数的变量使用；也就是说，一个函 数不是定义成 f(x, y)，而是定义成 f(g(x), y)，或 f(g(x), h(x)) 的形式。<br>
1. BeautifulSoup 允许我们把特定函数类型当作 findAll 函数的参数。
2. 唯一的限制条件是这些 函数必须把一个标签作为参数且返回结果是布尔类型。
3. 作用：BeautifulSoup 用这个函数来评估它 遇到的每个标签对象，最后把评估结果为“真”的标签保留，把其他标签剔除。
- 例如，下面的代码就是获取有两个属性的标签：
```
soup.findAll(lambda tag: len(tag.attrs) == 2) 
```

这行代码会找出下面的标签：

```
<div class="body" id="content"></div>
<span style="color:red" class="title"></span>
```
注： 是正 则表达式的完美替代方案。
#### 8. 超越BeautifulSoup
BeautifulSoup它并不 是你唯一的选择。
你可以看看其他的库：
1. lxml
2. HTML parser
---
## part 3. 开始采集
---
## part 4. 使用api
