# UiDo

## 项目介绍
  本项目是ui测试的一个逻辑规范框架，推崇链式调用,代码说明是基于 [NetEase/Dagger](https://github.com/NetEase/Dagger) 的一个实现。
  
  普通情况下你看到ui测试代码可能是这样的
``` java
  Webdriver wd=new ChromeDriver(...);
  wd.get(...);
  wd.click(By...);
  Assert....;
  wd.quit();
```
  使用Dagger之后可能会这样
``` java
  BrowserEmulator be=new BrowserEmulator();
  be.open(..);
  be.click(...);
  Assert...;
  be.quit();
```
  貌似没什么区别。。结合TestNg，一般成下边这样：
``` java
  private BrowserEmulator be;
  @BeforeTest(alway=True)
  function void setUp()
  {
    be=new BrowserEmulator();
  }
  @AfterTest
  function void tearDown()
  {
    if(be!=null)be.quit();
  }
  @Test
  funtion void myTest()
  {
    be.open(..);
  }
```
  进一步提取基类:
``` java
  class BaseTestClass:
      protected BrowserEmulator be;
      @BeforeTest(alway=True)
      function void setUp()
      {
        be=new BrowserEmulator();
      }
      @AfterTest
      function void tearDown()
      {
        if(be!=null)be.quit();
      }
  class MyTestSuite extends BaseTestClass:
     @Test
     function myTest()
     {
        be.open(..)
     }
```
  代码看起来还是挺凌乱，反过来我们思考下，UI测试时我们都要做什么？
  * 模拟用户操作
    * 一些固定的操作
    * 一些用例多有的操作
  * 数据库操作，获取页面无法感知的数据，或者数据修正
  * 断言
  以下为额外功能：
  * 失败截图
  * 云端支持记录
## 优雅的代码组织
  优雅的代码组织，使排查问题更简单。想想我们排查问题的过程，收到失败报表，定位失败用例名字，定位失败断言描述，定位输出堆栈的代码行数。本项目推崇链式调用，代码缩进。看一个标准的最终效果。
``` java
  start()
    .db().clearLoginRecord(acc)
    .action().login(acc,pwd)
  .then()
    .open(...)
    .test("测试模块A",cssModA)
      .textContains(cssModName,"xxxx")
    .test("测试模块B",cssModB)
      .textContains(cssModName,"xxxx")
    .test("列表",cssList)
      .withTest("卡片模块",cssCard)
        .attrEq(cssCardName,"name","xxx")
        .attrEq(cssCardName,"title","xxx")
      .withTest("footer",cssFooter)
        .domExists(cssFirst)
  .end();
```
