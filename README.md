# UiDo

## 项目介绍
  本项目是ui测试的一个逻辑规范框架,代码说明是基于 [NetEase/Dagger](https://github.com/NetEase/Dagger) 的一个实现。
  
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
