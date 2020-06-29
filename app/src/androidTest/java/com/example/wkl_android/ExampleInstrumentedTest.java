package com.example.wkl_android;

import android.content.Context;

import com.example.wkl_android.good.model.GoodsBean;
import com.example.wkl_android.good.model.GoodsListBean;
import com.example.wkl_android.good.model.GoodsModel;
import com.example.wkl_android.http.callback.impl.JsonCallBack;
import com.example.wkl_android.main.category.model.CategoryModel;
import com.example.wkl_android.main.category.model.bean.Category;
import com.example.wkl_android.main.home.model.HomeModel;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import static org.junit.Assert.assertEquals;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
@RunWith(AndroidJUnit4.class)
public class ExampleInstrumentedTest {
    @Test
    public void useAppContext() {
        // Context of the app under test.
        Context appContext = InstrumentationRegistry.getInstrumentation().getTargetContext();

        assertEquals("com.example.wkl_android", appContext.getPackageName());
    }

    /**
     * 先登录拿到token
     */
   /* @Before
    public void testLogin() {
        LoginModel loginModel = new LoginModel();
        final CountDownLatch latch = new CountDownLatch(1);
        loginModel.doLogin("wankelai", "123456", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                try {
                    LoginBean bean = new Gson().fromJson(json, LoginBean.class);
                    if (bean.isState()) {
                        SPUtils.getInstance().putString(SPUtils.KEY_TOKEN, bean.getToken());
                        SPUtils.getInstance().putString(SPUtils.KEY_REFRESH_TOKEN,
                                bean.getRefreshToken());

                    }
                } catch (Exception e) {

                }
            }
        });
        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertTrue(Common.getToken().length() > 0);

    }*/

    /**
     * 测试分类
     */
    @Test
    public void testCategory() {
        CategoryModel model = new CategoryModel();
        final CountDownLatch latch = new CountDownLatch(1);
        final List<Category>[] list = new List[]{new ArrayList<>()};
        model.getFirstCategory(new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                JSONObject object = new JSONObject(json);
                Type type = new TypeToken<List<Category>>() {
                }.getType();
                list[0] = new Gson().fromJson(object.getJSONArray("list").toString(), type);

                //callback方法执行完毕侯，唤醒测试方法执行线程
                latch.countDown();
            }
        });

        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(12, list[0].size());
    }

    /**
     * 测试商品
     */
    @Test
    public void testGoods() {
        HomeModel model = new HomeModel();
        final CountDownLatch latch = new CountDownLatch(1);
        final GoodsListBean[] goodsListBean = {new GoodsListBean()};
        model.getGoodsList(1, new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Gson g = new Gson();
                goodsListBean[0] = g.fromJson(json, GoodsListBean.class);

                //callback方法执行完毕侯，唤醒测试方法执行线程
                latch.countDown();
            }
        });

        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(20, goodsListBean[0].getData());
    }

    @Test
    public void testBanner() {
        HomeModel model = new HomeModel();
        final CountDownLatch latch = new CountDownLatch(1);
        final List<BannerBean>[] list = new List[]{new ArrayList<>()};
        model.getBannerList(new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Type type = new TypeToken<List<BannerBean>>() {
                }.getType();
                JSONObject jsonObject = new JSONObject(json);
                list[0] = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), type);

                //callback方法执行完毕侯，唤醒测试方法执行线程
                latch.countDown();
            }
        });

        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, list[0].size());
    }

    @Test
    public void testMenu() {
        HomeModel model = new HomeModel();
        final CountDownLatch latch = new CountDownLatch(1);
        final List<MenuBean>[] list = new List[]{new ArrayList<>()};
        model.getMenuList(new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                Type type = new TypeToken<List<MenuBean>>() {
                }.getType();
                JSONObject jsonObject = new JSONObject(json);
                list[0] = new Gson().fromJson(jsonObject.getJSONArray("data").toString(), type);

                //callback方法执行完毕侯，唤醒测试方法执行线程
                latch.countDown();
            }
        });

        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, list[0].size());
    }

    @Test
    public void testGetGoodsDetail() {
        GoodsModel model = new GoodsModel();
        final CountDownLatch latch = new CountDownLatch(1);
        final GoodsBean[] goodsBean = {new GoodsBean()};
        model.getDetail("1", "1252397899934228480", new JsonCallBack() {
            @Override
            public void onSuccess(String json) throws Exception {
                goodsBean[0] = new Gson().fromJson(json, GoodsBean.class);
                //callback方法执行完毕侯，唤醒测试方法执行线程
                latch.countDown();
            }
        });

        try {
            //测试方法线程会在这里暂停, 直到loadData()方法执行完毕, 才会被唤醒继续执行
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(0, goodsBean.length);
    }
}
