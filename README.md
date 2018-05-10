# MVP demo
* 是否引入ButterKnife
* ButterKnife在library和组件化的时候很不方便，这里先不适用ButterKnkife，使用findViewById,后期考虑databinding

## RxManage 对线程管理

## 耗时等待转圈封装
* 网络请求等耗时操作，通过BaseActivity+RxJava完成封装，通过Loadinghelper来处理同时进行多个网络请求的进度条

## BasePresenter的创建使用弱引用，防止内存泄漏
*     protected WeakReference<T> mViewRef;
  
      public void attachView(T view) {
          mViewRef = new WeakReference<>(view);
      }
     
     

## 集成以下功能
* 异常崩溃的捕获
* log日志的本地保存
* 常用的工具类
* 多编译环境buildTypes
