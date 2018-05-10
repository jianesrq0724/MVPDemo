# MVP demo
* 是否引入ButterKnife
* ButterKnife在library和组件化的时候很不方便，这里先不适用ButterKnkife，使用findViewById,后期考虑databinding

## RxManage 对线程管理

## LoadingDialog封装，网络请求快速调用加载等待，通过Loadinghelper来处理同时进行多个网络请求的进度条

## BasePresenter的创建使用弱引用，防止内存泄漏
*     protected WeakReference<T> mViewRef;
  
      public void attachView(T view) {
          mViewRef = new WeakReference<>(view);
      }
     
     


