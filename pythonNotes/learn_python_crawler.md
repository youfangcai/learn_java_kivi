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
- 关键词参数 keyword，可以让你选择那些具有指定属性的标签。
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
备注： .descendants 是一个针对单个 BeautifulSoup 对象的方法。也就是说先遍历 findAll 返回的每个元素，再对每个元素调用 .descendants。
例如：
```
for tr in bsObject.find_all("tr", class_="gift"):
    for child in tr.descendants:
        print(child)
```

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

<img src="C:\Users\Administrator\Pictures\Screenshots\屏幕截图 2024-10-09 201533.png" alt="屏幕截图 2024-10-09 201533" style="zoom:80%;" />

#### 5. 正则表达式和BeautifulSoup

BeautifulSoup 和正则表达式总是配合使用的。
大多数支 持字符串参数的函数（比如，find(id="aTagIdHere")）都可以用正则表达式实现。
例如：
```
images = bsObject.findAll("img,{"src":re.compile(r"\.\./img/gifts/img.*\.jpg")})
for image in images: print(image["src"])
```
备注：
>re.compile()函数：将正则表达式模式转换为一个内部表示形式，使得匹配操作更高效。
>re.compile(pattern, flags=0)
>pattern：要编译的正则表达式模式，可以是字符串或字节串。
>flags：用于控制正则表达式匹配方式的标志（可选），如 re.IGNORECASE（忽略大小写）、re.MULTILINE（多行模式）等。

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
注： 是正则表达式的完美替代方案。

由于 Lambda 函数可以是任意返回 True 或者 False 值的函数，你甚至可以结合使用 Lambda 函数与正则表达式，来查找匹配特定字符串模式的属性的标签。

#### 8. 超越BeautifulSoup

BeautifulSoup它并不 是你唯一的选择。
你可以看看其他的库：
1. lxml
2. HTML parser

---

## part 3. 编写网络爬虫

### 3.1 遍历单个域名

创建一个项目来实现“维基百科六度分隔理论”的查找方法。
这段程序更像下面的形式。
- 一个函数 getLinks，可以用维基百科词条 /wiki/< 词条名称 > 形式的URL链接作为参数， 然后以同样的形式返回一个列表，里面包含所有的词条 URL 链接。
- 一个主函数，以某个起始词条为参数调用 getLinks，再从返回的 URL 列表里随机选择 一个词条链接，再调用 getLinks，直到我们主动停止，或者在新的页面上没有词条链接 了，程序才停止运行。

```
def getLinks(url):
    html = urlopen("http://en.wikipedia.org" + url)
    soup = BeautifulSoup(html, "html.parser")
    # 提取页面链接
    # 用到正则表达式的 ?! 与 ^$
    return (soup.find("div", {"id":"bodyContent"})
            .find_all("a", href = re.compile("^(/wiki/)((?!:).)*$")))

url = "/wiki/Kevin_Bacon"
links = getLinks(url)
i = 0
while len(links) > 0:
    i+=1
    newArticle = links[random.randint(0, len(links)-1)].attrs["href"]
    print(newArticle)
    links = getLinks(newArticle)
    if i == 6:
        break
```
备注：简单地构建一个从一个页面到另一个页面的爬虫。
  但是要真正成 为自动化产品代码，还需要增加更多的异常处理。

### 3.2 采集整个网站

尤其是处理大型网站时，最合适的 工具就是用一个数据库来储存采集的资源。
虽然这些网站采集并不费劲，但是它们需要爬虫有足够的深度（我们有 意收集数据的网站不多）。于是我就创建了一个爬虫递归地遍历每个网站，只收集那些 网站页面上的数据。<br><br>
一个常用的费时的网站采集方法就是从顶级页面开始（比如主页），然后搜索页面上的所 有链接，形成列表。再去采集这些链接的每一个页面，然后把在每个页面上找到的链接形 成新的列表，重复执行下一轮采集。

```
pages = set()

def getLinks(pageUrl):
    global pages
    try:
        html = urlopen("http://en.wikipedia.org" + pageUrl)
        # 在这里处理 html 内容，例如提取链接等
    except HTTPError as e:
        print(f"HTTP Error: {e.code} for URL: {pageUrl}")
    except Exception as e:
        print(f"An error occurred: {e}")

    soup = BeautifulSoup(html, "html.parser")

    try:
        print(soup.h1.get_text())
        print(soup.find(id = "mw-content-text").find_all("p")[0])
        print(soup.find(id = "ca-edit").find("span").find("a").attrs["href"])
    except AttributeError:
        print("页面缺失一些属性!")

    for link in soup.find_all("a", href = re.compile("^(/wiki/)")):
        if "href" in link.attrs:
            if link.attrs["href"] not in pages:
                # 我们遇到了新页面
                newPage = link.attrs["href"]
                print("----------------\n"+newPage)
                pages.add(newPage)
                getLinks(newPage)

getLinks("")
```

### 3.3 互联网采集

互联网采集:(例子)收集内链和外链

```
import datetime
import re
import random
from urllib.parse import urlparse
from urllib.request import urlopen

from bs4 import BeautifulSoup
"""
演示：收集内链与外链
"""

pages = set()
# 设置随机种子, 一时间为种子
random.seed(datetime.datetime.now().timestamp())

# 获取页面中所有内链的列表
def getInternalLinks(soup, includeUrl):
    internalLinks = []
    # 表示匹配以 / 开头的字符串 或 包含 includeUrl 的任何字符串
    for link in soup.find_all("a", href=re.compile("^(/|.*" + includeUrl + ")")):
        if link.attrs["href"] is not None:
            if link.attrs["href"] not in internalLinks:
                if link.attrs['href'].startswith('/'):
                    internalLinks.append(includeUrl + link.attrs['href'])
                else:
                    internalLinks.append(link.attrs['href'])
    return internalLinks

# 获取页面中所有外链的列表
def getExternalLinks(soup, excluderUrl):
    externalLinks = []
    for link in soup.find_all("a", href=re.compile("^([http|www])((?!" + excluderUrl + ").)*$")):
        if link.attrs["href"] is not None:
            if link.attrs["href"] not in externalLinks:
                externalLinks.append(link.attrs["href"])
    return externalLinks

#
def splitAddress(address):
    addressParts = address.replace("http://", "").split("/")
    return addressParts

# 随机返回一个外链
def getRandomExternalLink(startingPage):
    html = urlopen(startingPage)
    soup = BeautifulSoup(html, 'html.parser')
    # 调用 urlparse(startingPage).netloc 可以从一个 URL 中提取网络位置部分（即域名和端口号）。
    externalLinks = getExternalLinks(soup, urlparse(startingPage).netloc)
    print(externalLinks)
    if len(externalLinks) == 0:
        print('No external links, looking around the site for one')
        # urlparse(startingPage).scheme 用于提取给定 URL 的协议部分（scheme），例如 http、https、ftp 等。
        domain = '{}://{}'.format(urlparse(startingPage).scheme, urlparse(startingPage).netloc)
        internalLinks = getInternalLinks(soup, domain)
        return getRandomExternalLink(internalLinks[random.randint(0, len(internalLinks)-1)])
    else:
        return externalLinks[random.randint(0, len(externalLinks)-1)]

#
def followExternalOnly(startingSite):
    externalLink = getRandomExternalLink(startingSite)
    print('Random external link is: {}'.format(externalLink))
    followExternalOnly(externalLink)

# followExternalOnly('http://oreilly.com')

# 收集内链和外链
allExtLinks = set()
allIntLinks = set()
# 收集该页面的外链和内链
def getAllExternalLinks(siteUrl):
    html = urlopen(siteUrl)
    soup = BeautifulSoup(html, 'html.parser')
    externalLinks = getExternalLinks(soup, urlparse(siteUrl).netloc)
    internalLinks = getInternalLinks(soup, urlparse(siteUrl).netloc)
    for link in externalLinks:
        if link not in allExtLinks:
            allExtLinks.add(link)
            print(link)
    print("\n------------------------\n")
    for link in internalLinks:
        if link not in allIntLinks:
            allIntLinks.add(link)
            print(link)
getAllExternalLinks("https://www.topgoer.com/")
```

---

## part 4. 网络爬虫模型
