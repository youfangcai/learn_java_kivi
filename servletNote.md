java servlet&jsp笔记
===
# part one

# part two

# part three:

# part four: 作为Servlet —— 请求和响应

## 1. **Servlet 生命周期**

### **主要方法：**
- **`init()`**：初始化Servlet时调用，用于在Servlet实例化时执行一次性设置或初始化资源。
  
  ```java
  public void init() throws ServletException {
      // 初始化代码
  }
  ```

- **`service()`**：每次收到客户端请求时都会调用。`service()`方法会根据请求类型自动调用`doGet()`或`doPost()`等方法。

  ```java
  public void service(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
      // 处理请求逻辑
  }
  ```

- **`destroy()`**：当Servlet被卸载时调用，通常用于清理资源。

  ```java
  public void destroy() {
      // 资源清理代码
  }
  ```

## 2. **Servlet 请求处理**

### **`doGet()` 方法**：处理HTTP GET请求，常用于获取数据。

```java
protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 处理 GET 请求的代码
}
```

### **`doPost()` 方法**：处理HTTP POST请求，适合处理表单提交或数据修改。

```java
protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    // 处理 POST 请求的代码
}
```

### **`HttpServletRequest`**：表示客户端的请求。

- **`getParameter(String name)`**：从请求中获取表单参数。
  
  ```java
  String value = request.getParameter("paramName");
  ```

- **`getAttribute(String name)`**：从请求范围中获取属性。

  ```java
  Object attribute = request.getAttribute("attributeName");
  ```

- **`getSession()`**：获取与请求关联的会话。

  ```java
  HttpSession session = request.getSession();
  ```

### **`HttpServletResponse`**：表示响应对象，用于返回结果给客户端。

- **`setContentType(String type)`**：设置响应的内容类型，例如HTML、JSON等。

  ```java
  response.setContentType("text/html");
  ```

- **`getWriter()`**：获取用于输出内容的`PrintWriter`对象。

  ```java
  PrintWriter out = response.getWriter();
  out.println("<html><body>Hello, World!</body></html>");
  ```

- **`sendRedirect(String location)`**：重定向到另一个URL。

  ```java
  response.sendRedirect("http://example.com");
  ```

## 3. **Servlet 中的会话管理**

### **`HttpSession`**：用于管理用户会话。

- **`setAttribute(String name, Object value)`**：在会话中存储数据。
  
  ```java
  session.setAttribute("username", "JohnDoe");
  ```

- **`getAttribute(String name)`**：从会话中获取数据。

  ```java
  String username = (String) session.getAttribute("username");
  ```

- **`invalidate()`**：使当前会话无效，通常用于用户注销。

  ```java
  session.invalidate();
  ```

## 4. **ServletContext 与 ServletConfig**

### **`ServletContext`**：提供Servlet上下文，允许在整个应用范围内共享数据。

- **`getInitParameter(String name)`**：获取全局初始化参数。

  ```java
  String dbDriver = getServletContext().getInitParameter("DB_DRIVER");
  ```

- **`setAttribute(String name, Object value)`**：在上下文范围内共享数据。

  ```java
  getServletContext().setAttribute("appName", "MyApp");
  ```

### **`ServletConfig`**：用于获取Servlet的初始化配置。

- **`getInitParameter(String name)`**：获取Servlet初始化参数。

  ```java
  String param = getServletConfig().getInitParameter("configParam");
  ```

## 5. **错误处理**

- **`sendError(int sc, String msg)`**：发送指定的HTTP错误状态码和错误信息。

  ```java
  response.sendError(HttpServletResponse.SC_NOT_FOUND, "Resource not found");
  ```

- **`setStatus(int sc)`**：手动设置响应状态码。

  ```java
  response.setStatus(HttpServletResponse.SC_OK);
  ```

---


# part five:作为Web应用 —— 属性和监听者

## 1. **什么是属性？**
属性（Attributes）是Servlet中的一种机制，用于在不同组件之间传递数据。这些组件包括Servlet、JSP和Filter。属性有不同的作用范围：
- **请求范围（Request Scope）**：用于同一请求中的数据传递。
- **会话范围（Session Scope）**：用于同一个用户会话中的数据传递。
- **应用范围（Application Scope）**：用于在整个Web应用中共享数据。

### **属性的常用方法**

#### **设置属性**
```java
request.setAttribute("key", value);  // 请求范围
session.setAttribute("key", value);  // 会话范围
getServletContext().setAttribute("key", value);  // 应用范围
```

#### **获取属性**
```java
Object value = request.getAttribute("key");  // 请求范围
Object value = session.getAttribute("key");  // 会话范围
Object value = getServletContext().getAttribute("key");  // 应用范围
```

#### **移除属性**
```java
request.removeAttribute("key");  // 请求范围
session.removeAttribute("key");  // 会话范围
getServletContext().removeAttribute("key");  // 应用范围
```

## 2. **请求（Request）属性**

请求属性在一次请求的生命周期内有效。它通常用于在请求链中传递数据，例如在Servlet转发或重定向中传递数据。以下是一个例子：

```java
// 在Servlet1中设置属性
request.setAttribute("username", "JohnDoe");

// 转发请求到Servlet2
RequestDispatcher dispatcher = request.getRequestDispatcher("servlet2");
dispatcher.forward(request, response);

// 在Servlet2中获取属性
String username = (String) request.getAttribute("username");
```

> **注意**：请求属性只在一次请求内有效，跨请求无法访问这些数据。

## 3. **会话（Session）属性**

会话属性允许在多个请求之间共享数据，通常用于存储用户相关的信息，如登录状态或购物车内容。以下是一个会话属性的例子：

```java
// 设置会话属性
HttpSession session = request.getSession();
session.setAttribute("user", "JohnDoe");

// 获取会话属性
String user = (String) session.getAttribute("user");

// 使会话失效（例如用户登出）
session.invalidate();
```

> **注意**：会话属性的生命周期与用户的会话周期一致，直到会话失效或超时。

## 4. **应用（Application）属性**

应用属性在整个应用程序中有效，所有用户共享这些属性。它适用于全局数据，例如配置信息。

```java
// 设置应用属性
ServletContext context = getServletContext();
context.setAttribute("appName", "MyApp");

// 获取应用属性
String appName = (String) context.getAttribute("appName");
```

> **注意**：应用属性在Web应用启动时初始化，并在应用关闭时销毁。

---

## 5. **监听器（Listeners）**

监听器用于监控Web应用中的特定事件，如会话创建、销毁、属性变化等。Servlet API中有多种监听器，主要用于管理请求、会话和应用中的属性或事件。

### **常见监听器**

1. **ServletContextListener**
   - 用于监听Web应用的启动和关闭。
   - 主要方法：
     - `contextInitialized(ServletContextEvent sce)`：应用启动时调用。
     - `contextDestroyed(ServletContextEvent sce)`：应用关闭时调用。

   ```java
   public class MyAppListener implements ServletContextListener {
       public void contextInitialized(ServletContextEvent sce) {
           // 应用启动时执行的代码
       }
       public void contextDestroyed(ServletContextEvent sce) {
           // 应用关闭时执行的代码
       }
   }
   ```

2. **HttpSessionListener**
   - 用于监听会话的创建和销毁。
   - 主要方法：
     - `sessionCreated(HttpSessionEvent se)`：会话创建时调用。
     - `sessionDestroyed(HttpSessionEvent se)`：会话销毁时调用。

   ```java
   public class MySessionListener implements HttpSessionListener {
       public void sessionCreated(HttpSessionEvent se) {
           // 会话创建时执行的代码
       }
       public void sessionDestroyed(HttpSessionEvent se) {
           // 会话销毁时执行的代码
       }
   }
   ```

3. **ServletRequestListener**
   - 用于监听请求的创建和销毁。
   - 主要方法：
     - `requestInitialized(ServletRequestEvent sre)`：请求创建时调用。
     - `requestDestroyed(ServletRequestEvent sre)`：请求销毁时调用。

   ```java
   public class MyRequestListener implements ServletRequestListener {
       public void requestInitialized(ServletRequestEvent sre) {
           // 请求初始化时执行的代码
       }
       public void requestDestroyed(ServletRequestEvent sre) {
           // 请求销毁时执行的代码
       }
   }
   ```

---

## 6. **属性监听器**

属性监听器用于监控属性的添加、修改和移除事件。可以通过实现相应的接口来监听特定作用域（如请求、会话、应用）中属性的变化。

### **常用属性监听器：**

1. **ServletContextAttributeListener**
   - 监听应用范围属性的变化。
   - 主要方法：
     - `attributeAdded(ServletContextAttributeEvent scae)`：属性添加时调用。
     - `attributeRemoved(ServletContextAttributeEvent scae)`：属性移除时调用。
     - `attributeReplaced(ServletContextAttributeEvent scae)`：属性替换时调用。

2. **HttpSessionAttributeListener**
   - 监听会话范围属性的变化。

3. **ServletRequestAttributeListener**
   - 监听请求范围属性的变化。

---

## 7. **总结与图示**

- **属性**是用于在不同范围内共享数据的机制，范围包括请求、会话和应用。
- **监听器**是一种事件驱动的编程模型，用于在特定事件发生时执行相应的代码逻辑。

