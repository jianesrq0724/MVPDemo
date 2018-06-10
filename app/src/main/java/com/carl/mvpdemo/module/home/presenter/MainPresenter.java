package com.carl.mvpdemo.module.home.presenter;

import com.carl.mvpdemo.module.home.bean.SmsBean;
import com.carl.mvpdemo.module.home.interfaces.MainI;
import com.carl.mvpdemo.module.home.model.HuLianDataCenter;
import com.carl.mvpdemo.module.home.model.YiMaDataCenter;
import com.carl.mvpdemo.pub.base.BasePresenter;
import com.carl.mvpdemo.pub.utils.RxUtils;
import com.carl.mvpdemo.pub.utils.ToastUtils;
import com.google.gson.JsonObject;

import java.util.concurrent.TimeUnit;

import io.reactivex.Flowable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * @author Carl
 * @version 1.0
 * @since 2018/5/10
 */
public class MainPresenter extends BasePresenter<MainI> {

    /**
     * 模拟网络请求
     */
    public void testLogin() {

        Disposable disposable = Flowable.timer(2 * 1000, TimeUnit.MILLISECONDS)
                .compose(RxUtils.<Long>getScheduler(true, getView()))
                .subscribe(new Consumer<Long>() {
                    @Override
                    public void accept(Long aLong) throws Exception {
                        getView().loginSuccess("登录成功");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mRxManage.add(disposable);
    }

    /**
     * 登录
     */
    public void login() {
        Disposable disposable = YiMaDataCenter.getInstance().login("jianesrq0724", "")
                .compose(RxUtils.<JsonObject>rxScheduler())
                .subscribe(new Consumer<JsonObject>() {
                    @Override
                    public void accept(JsonObject jsonObject) throws Exception {
                        ToastUtils.showShort("Success");
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {
                        throwable.printStackTrace();
                    }
                });
        mRxManage.add(disposable);
    }

    public void getSms(String phone) {
        Disposable subscribe = HuLianDataCenter.getInstance().getSms(phone)
                .compose(RxUtils.<SmsBean>rxScheduler())
                .subscribe(new Consumer<SmsBean>() {
                    @Override
                    public void accept(SmsBean smsBean) throws Exception {
                        ToastUtils.showShort(smsBean.getMsg());
                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mRxManage.add(subscribe);
    }

    public void regist(String phone, String code) {
        Disposable subscribe = HuLianDataCenter.getInstance().register(phone, code)
                .compose(RxUtils.<JsonObject>rxScheduler())
                .subscribe(new Consumer<JsonObject>() {
                    @Override
                    public void accept(JsonObject jsonObject) throws Exception {

                    }
                }, new Consumer<Throwable>() {
                    @Override
                    public void accept(Throwable throwable) throws Exception {

                    }
                });
        mRxManage.add(subscribe);
    }
}
